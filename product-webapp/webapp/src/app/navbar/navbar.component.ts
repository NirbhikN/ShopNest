import {Component, OnInit, ViewChild} from '@angular/core';
import jwtDecode from "jwt-decode";
import {JwtPayload} from "../model/JwtPayload.model";
import {MatExpansionPanel} from "@angular/material/expansion";
import {MyRestService} from "../services/my-rest.service";
import {CartService} from "../services/cart.service";
import {SharedService} from "../services/shared.service";
import {SessionStorageService} from "ngx-webstorage";



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  tokenExists: boolean;
  cartItemCount: number = 0;
  constructor(
              private rs : MyRestService,
              public cartService : CartService,
              private sc : SharedService,
              private  sS : SessionStorageService){

  }

  categories = [
    'Electronics',
    'Cars',
    'Motorcycles',
    'Personal Loan',
    'Collectible and Art',
    'Sports',
    'Home & Garden'
  ];
  @ViewChild(MatExpansionPanel) expansionPanel!: MatExpansionPanel;
  userName:string;
  userId:string;
  token: string = localStorage.getItem('token');
  ngOnInit() {
    const storedCartItems = this.sS.retrieve('cartItems');
    if (storedCartItems) {
      this.cartItemCount = storedCartItems.length
    }
    this.cartService.cartItemsChanged.subscribe(items => {
      this.cartItemCount = items.length;
    });


      const decodedToken: JwtPayload = jwtDecode(this.token);
      this.userName = decodedToken.name;
      this.userId=decodedToken.userId
  }

  isLoggedIn(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }

  signOut(): void {
    localStorage.removeItem('token');
    sessionStorage.clear();
    window.location.reload();
    window.location.href = '';
  }


  enteredSearchValue: string = '';
  onSearchTextChanged(){
    this.sc.sendData(this.enteredSearchValue);
    console.log(this.enteredSearchValue)
  }

}
