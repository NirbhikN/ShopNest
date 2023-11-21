import {Component, OnInit} from '@angular/core';
import {MyRestService} from "../services/my-rest.service";
import {Order} from "../model/Order.model";
import {Product} from "../model/Product.model";
import {JwtPayload} from "../model/JwtPayload.model";
import jwtDecode from "jwt-decode";
import {forkJoin, map, Observable} from "rxjs";
import {Slot} from "../model/Slot.model";
import {Router} from "@angular/router";
import {CartService} from "../services/cart.service";
import {ViewSlotModel} from "../model/ViewSlot.model";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-view-slot',
  templateUrl: './view-slot.component.html',
  styleUrls: ['./view-slot.component.css']
})
export class ViewSlotComponent implements OnInit {
  private product: Product;

  constructor(private rs: MyRestService, private router: Router, private auth: AuthService) {
  }


  userId: string;
  slotData = new Slot();
  allSlots: Slot[] = []
  boughtSlots: ViewSlotModel[] = []
  soldSlots: ViewSlotModel[] = []

  soldDetails: ViewSlotModel[] = []

  async ngOnInit() {
    this.userId = this.auth.getJwtData().userId
    this.slotData.buyerId = this.userId
    this.getBoughtSlot();
    this.getSoldSlot();

    // await this.getProductDetails();
  }




  getBoughtSlot() {
    let allOrders: Observable<any> = this.rs.getSlots();
    allOrders.subscribe(
      async data => {
        this.allSlots = data;
        // Create an array of Observables for fetching product details
        const productRequests: Observable<any>[] = this.allSlots
          .filter(slot => slot.buyerId === this.userId)
          .map(slot => this.rs.getProductById(slot.productId));

        // Use forkJoin to fetch product details in parallel
        const productDetails = await forkJoin(productRequests)
          .toPromise()
          .catch(error => {
            // Handle any errors that occurred while fetching product details
            console.error('Error fetching product details:', error);
            return []; // Return an empty array or handle the error as needed
          });

        // Combine the slot data with the corresponding product details
        this.boughtSlots = this.allSlots
          .filter(slot => slot.buyerId === this.userId)
          .map((slot, index) => ({
            ...slot,
            productId: slot.productId,
            productName: productDetails[index].productName,
            productPrice: productDetails[index].productPrice,
            sellerName: productDetails[index].sellerName,
          }));
        // this.soldSlots = this.allSlots.filter(slot => slot.sellerId === this.userId);
        console.log(this.slotData)
        console.log("Bought", this.boughtSlots);
      });
  }

  getSoldSlot() {
    let allOrders: Observable<any> = this.rs.getSlots();
    allOrders.subscribe(
      async data => {
        this.allSlots = data;
        // Create an array of Observables for fetching product details
        const productRequests: Observable<any>[] = this.allSlots
          .filter(slot => slot.sellerId === this.userId)
          .map(slot => this.rs.getProductById(slot.productId));

        // Use forkJoin to fetch product details in parallel
        const productDetails = await forkJoin(productRequests)
          .toPromise()
          .catch(error => {
            // Handle any errors that occurred while fetching product details
            console.error('Error fetching product details:', error);
            return []; // Return an empty array or handle the error as needed
          });

        // Combine the slot data with the corresponding product details
        this.soldSlots = this.allSlots
          .filter(slot => slot.sellerId === this.userId)
          .map((slot, index) => ({
            ...slot,
            productId: slot.productId,
            productName: productDetails[index].productName,
            productPrice: productDetails[index].productPrice,
            sellerName: productDetails[index].sellerName,
          }));
        // this.soldSlots = this.allSlots.filter(slot => slot.sellerId === this.userId);
        console.log(this.slotData)
        console.log("Bought", this.boughtSlots);
        console.log("Bought", this.soldSlots);
      });
  }
}
