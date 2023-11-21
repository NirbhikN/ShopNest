import {Component, OnInit} from '@angular/core';
import {MyRestService} from "../services/my-rest.service";
import {Observable} from "rxjs";
import {JwtPayload} from "../model/JwtPayload.model";
import jwtDecode from "jwt-decode";
import {Order} from "../model/Order.model";
import {Product} from "../model/Product.model";

@Component({
  selector: 'app-order-product',
  templateUrl: './order-product.component.html',
  styleUrls: ['./order-product.component.css']
})
export class OrderProductComponent implements OnInit {
  constructor(private rs: MyRestService) {
  }


  userId: string;

  orderData = new Order();
  token: string = localStorage.getItem('token');

  allOrders: Order[] = []
  boughtProducts: Order[] = []
  soldProducts: Order[] = []

  allProducts:Product[]=[]

  ngOnInit() {

    const decodedToken: JwtPayload = jwtDecode(this.token);
    this.userId = decodedToken.userId;
    this.orderData.buyerId = this.userId

    // Get all orders
    let q: Observable<any> = this.rs.getOrder()
    q.subscribe(
        data => {
          this.allOrders = data;
          this.boughtProducts = this.allOrders.filter(order => order.buyerId === this.userId);
          this.soldProducts = this.allOrders.filter(order => order.buyerId && order.sellerId === this.userId);

          console.log("Bought", this.boughtProducts);
          console.log("Sold", this.soldProducts);
        })

  }
}
