import { Component } from '@angular/core';
import {CartItem, CartService} from "../services/cart.service";
import {forkJoin, Observable} from "rxjs";
import {MyRestService} from "../services/my-rest.service";
import {Order} from "../model/Order.model";
import {JwtPayload} from "../model/JwtPayload.model";
import {AuthService} from "../services/auth.service";
import { SessionStorageService } from 'ngx-webstorage';
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";
import {EmailRequest} from "../model/Email.model";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  cartItems: CartItem[] = [];
  cartNum: number;
  totalPrice: number = 0;
  productIds: string[];
  orderDataArray: Order[] = [];
  userId : JwtPayload;
  auth:boolean;

  constructor(
              public cartService: CartService,
              private rs : MyRestService,
              private  authService: AuthService,
              private sS: SessionStorageService,
              private snackBar: MatSnackBar,
  ) {

  }


  ngOnInit(): void {
      const storedCartItems = this.sS.retrieve('cartItems');
      if (storedCartItems) {
          this.cartItems = storedCartItems;
          this.cartNum = this.cartItems.length
      }
      this.cartService.cartItemsChanged.subscribe(items => {
          this.cartNum = items.length;
      });
      this.totalPrice = this.cartService.getTotalPrice();
      this.cartService.totalPrice.subscribe(price => {
          this.totalPrice = price;
      });
      console.log(this.cartItems)
  }

  async buyNow(): Promise<void> {
      if(this.authService.isAuthenticated()) {
          this.productIds = this.cartItems.map((cartItem) => cartItem.product.productId);
          for (const productId of this.productIds) {
              const orderData = new Order();// Create a copy of the template
              // Customize orderData properties based on productId or other criteria
              orderData.buyerId = this.authService.getJwtData().userId// For example, set productId
              // Add other properties as needed
              this.orderDataArray.push(orderData); // Add the order data object to the array
          }
          this.orderProducts(this.productIds, this.orderDataArray);
          console.log(this.orderDataArray)
          await this.rs.buyNow(this.cartItems);
          this.cartService.clearCart();
      }
      else {
          // If the user is not authenticated, show a pop-up message
          const config = new MatSnackBarConfig();
          config.duration = 5000; // Display duration in milliseconds
          config.panelClass = ['snackbar-warning'];
          this.snackBar.open('Please log in first to proceed with the purchase.', 'Close', config);
      }

  }

  orderProducts(productIds: string[], orderDataArray: any[]): void {
    // Initialize an array to store the observables for each product order
    const observables: Observable<any>[] = [];
    // Iterate through the productIds and orderDataArray to create observables
    for (let i = 0; i < productIds.length; i++) {
      const productId = productIds[i];
      const orderData = orderDataArray[i];

      // Create an observable for each product order and add it to the array
      const orderObservable = this.rs.addOrder(productId, orderData);
      observables.push(orderObservable);
    }

    // Use forkJoin to execute all observables in parallel
    forkJoin(observables).subscribe({
      next: (results) => {
        console.log("Post Data: ", results);
      },
      error: (error) => {
        console.error(error);
      },
      complete: async () => {

          await this.sendEmail();
      },
    });
  }


    emailRequest: EmailRequest = {
        to: '',
        subject: 'Order Confirmed',
        body: 'Booked',
    };

    async sendEmail() {
        this.userId = this.authService.getJwtData()
        this.emailRequest.to = this.userId.email;

        try {
            this.rs.sendEmail(this.emailRequest);
            // Code to execute after sending email successfully
            console.log('Email sent successfully');
        } catch (error) {
            // Handle errors here
            console.error('Email sending failed:', error);
        }
    }
}
