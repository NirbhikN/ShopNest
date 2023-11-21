import {booleanAttribute, Injectable} from '@angular/core';

import {Subject, Observable, tap, BehaviorSubject} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Profile} from "../model/Profile.model";
import {Product} from "../model/Product.model";
import jwtDecode from "jwt-decode";
import {Slot} from "../model/Slot.model";
import {Order} from "../model/Order.model";
import {EmailRequest} from "../model/Email.model";
import {Payment} from "../model/Payment";
import {loadStripe} from "@stripe/stripe-js";
import {environment} from "../model/environment";



@Injectable({
  providedIn: 'root'
})
export class MyRestService {
  stripePromise = loadStripe(environment.stripe);
  constructor(private http: HttpClient) { }
  private Url = 'http://localhost:8081/user/register';
  private apiUrl :string = 'http://localhost:8081/user';

  private productUrl:string='http://localhost:8084/product/products'


  private slotUrl:string='http://localhost:8085/slots/edit'

  private slotUrl1:string='http://localhost:8085/slots'

  private orderUrl:string='http://localhost:8087/orders/order'

  private editUrl:string='http://localhost:8081/user/edit'


  // reverseGeocode(latitude: number, longitude: number) {
  //   const url = `https://nominatim.openstreetmap.org/reverse?lat=${latitude}&lon=${longitude}&format=json`;
  //   return this.http.get(url, { headers: { 'User-Agent': 'YourApp' } });
  // }


  editUser(id:string,addedUser:Profile){
    return this.http.put(`${this.editUrl}/${id}`,addedUser)

  }
  getUserId(email:string):Observable<string>{
    const url:string = `${this.apiUrl}/user/user/${email}`
    return this.http.get<string>(url)
  }
  getUserById(id:string):Observable<Profile>{
    return this.http.get<Profile>(`${this.apiUrl}/user/id/${id}`);
  }

  // Products

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productUrl);
  }
  addProduct(productData:Product[]){
    return this.http.post(this.productUrl,productData);
  }

  getProductById(id:string):Observable<Product>{
    return this.http.get<Product>(`${this.productUrl}/${id}`);
  }

  getProductsByCategory(category: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.productUrl}/categories/${category}`);
  }
  editProduct(id:string,updatedProduct:Product){
    return this.http.put(`${this.productUrl}/${id}/edit`,updatedProduct)
  }

//   Slots

  getSlots(){
    return this.http.get<Slot[]>(`${this.slotUrl1}/all`);
  }

  addSlot(productId:string,addedSlot:Slot){
    return this.http.put<boolean>(`${this.slotUrl}/${productId}`,addedSlot)
  }

  getSlotsByProductId(productId:string){
    return this.http.get<Slot[]>(`${this.slotUrl1}/productId/${productId}`)
  }

  // Order
  addOrder(productId:string,orderData:Order){
    return this.http.put(`${this.orderUrl}/${productId}`,orderData)
  }
  getOrder(){
    return this.http.get(this.orderUrl)
  }

  sendEmail(emailRequest: EmailRequest): Observable<string> {
    return this.http.post<string>("http://localhost:8083/api/email/send", emailRequest);
  }


  getEmail(email: string): Observable<boolean> {
    return this.http.get<boolean>( `http://localhost:8083/user/check/email/${email}`);
  }

  sendOTP(email: string): Observable<number> {
    return this.http.post<number>("http://localhost:8083/user/sendOtp", email);
  }

  verifyOTP(otp: number): Observable<boolean> {
    return this.http.post<boolean>("http://localhost:8083/user/verifyOtp", otp);
  }

  resetPassword(password: { email : string, password : string,password2 : string }): Observable<boolean> {
    return this.http.post<boolean>("http://localhost:8083/user/reset",password);
  }

  changeStatus(id: string){
    return this.http.put<boolean>(`http://localhost:8084/User/status/${id}`,null)
  }

  async buyNow(cartItems: any[]): Promise<void> {
    const payment: Payment[] = [];
    cartItems.forEach((cartItem) => {
      const paymentItem: Payment = {
        name: cartItem.product.productName,
        currency: 'inr',
        amount: Number(cartItem.product.productPrice) * 100,
        quantity:cartItem.quantity,
        successUrl: 'http://localhost:4200/order',
        cancelUrl:'http://localhost:4200/'
      };
      payment.push(paymentItem);
    });
    const stripe = await this.stripePromise;
    const url ="http://localhost:8086/payments/create-payment-session"
    let pay: Observable<any> = this.http.post(url,payment)
    pay.subscribe({
      next: (data:any) => {
        stripe.redirectToCheckout({
          sessionId: data.id,
        })
      },
      error: (e) => {
        console.error(e);
      },
      complete: () => {
        // this.orderProduct();
      },
    });
  }


}
