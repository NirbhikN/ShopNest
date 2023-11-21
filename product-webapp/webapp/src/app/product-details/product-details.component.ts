import {Component, inject} from '@angular/core';
import {MyRestService} from "../services/my-rest.service";
import {firstValueFrom, forkJoin, Observable} from "rxjs";
import {Router} from "@angular/router";
import {Product} from "../model/Product.model";
import {JwtPayload} from "../model/JwtPayload.model";
import jwtDecode from "jwt-decode";
import {Order} from "../model/Order.model";
import {ToastrService} from "ngx-toastr";
import {loadStripe} from "@stripe/stripe-js";
import {environment} from "../model/environment";
import {HttpClient} from "@angular/common/http";
import {EmailRequest} from "../model/Email.model";
import {Payment} from "../model/Payment";
import {CartItem, CartService} from "../services/cart.service";
import {MatTableDataSource} from "@angular/material/table";
import {Slot} from "../model/Slot.model";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent {
  public href: string = "";
  isBuyingNow: boolean = false;
  private userEmail: string;
// Create an instance of MatTableDataSource

  constructor(private router: Router,
              private cartService: CartService,
             ) {}

  product: Product;
  rs: MyRestService = inject(MyRestService);
  userId: string;
  token: string = localStorage.getItem('token');
  orderData = new Order();
  cartItems: CartItem[] = [];
  status = false;
    slotData:Slot[]=[]

  async ngOnInit() {
    await this.getProductDetails();
    let decodedToken: JwtPayload = jwtDecode(this.token);
    this.userId = decodedToken.userId;
    this.userEmail = decodedToken.sub;
    console.log(this.userEmail)
    this.orderData.buyerId = this.userId
    this.href = this.router.url;
    console.log(this.orderData)

    console.log("size: ", this.product.slots.length)

    this.rs.getSlotsByProductId(this.product.productId).subscribe(
      (data)=>{
        this.slotData=data;
        // console.log("slotdata", this.slotData)
      }
    )

  }

  async buyNow(): Promise<void> {
    try {
      await this.getProductDetails();
      this.orderData.buyerId = this.userId
      this.cartService.addToCart(this.product);
      await this.rs.buyNow(this.cartService.getCartItems());
      await this.orderProduct();
      this.cartService.clearCart();
      this.rs.changeStatus(this.product.productId).subscribe((data)=> this.status = data)
    } catch (error) {
      console.error('Error during buyNow:', error);
    }
  }

  async getProductDetails() {
    return new Promise<void>((resolve, reject) => {
      this.href = this.router.url
      let productDetails: Observable<any> = this.rs.getProductById(this.href.substring(10));
      productDetails.subscribe({
        next: (product) => {
          this.product = product
          resolve();
        },
        error: (e) => {
          console.error(e);
          reject(e);
        },
        complete: () => {
          console.log("Product Displayed")
          console.log(this.product);
        },
      });
    });
  }


  orderProduct() {
    console.log(this.product.productId)
    return new Promise<void>((resolve, reject) => {
      this.rs.addOrder(this.product.productId, this.orderData).subscribe({
        next: (order) => {
          resolve();

          console.log("Post Data: ", order)
        },
        error: (e) => {
          console.error(e);
          reject();
        },
        complete: async () => {
          // this.toaster.success("Product Ordered");
          await this.sendEmail();
        }
      });
    })
  }

  emailRequest: EmailRequest = {
    to: '',
    subject: 'Order Confirmed',
    body: 'Booked',
  };

  async sendEmail() {
    this.emailRequest.to = this.userEmail;

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
