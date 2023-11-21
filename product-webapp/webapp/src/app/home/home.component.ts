/* eslint-disable prefer-const */
/* eslint-disable @typescript-eslint/no-inferrable-types */
import { Component, inject, Input, OnInit, ViewChild } from '@angular/core';
import { MyRestService } from '../services/my-rest.service';
import { Observable } from 'rxjs';
import { Product } from '../model/Product.model';
import { NavbarComponent } from '../navbar/navbar.component';
import { CartService } from '../services/cart.service';
import { SharedService } from '../services/shared.service';
import { PageEvent } from '@angular/material/paginator';
import { HttpClient } from '@angular/common/http';
import { JwtPayload } from '../model/JwtPayload.model';
import jwtDecode from 'jwt-decode';
import { AuthService } from '../services/auth.service';
import {
  NgbCarousel,
  NgbSlideEvent,
  NgbSlideEventSource,
} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  rs: MyRestService = inject(MyRestService);
  selectedCategory: string = '';
  myProducts: Product[] = []; // Assuming this is your product array

  recommendedProducts: Product[] = [];
  latestProducts: Product[] = [];
  tempProducts: Product[] = [];

  private drawer: any;
  userId: string;
  public auth: boolean;
  searchProduct: string = '';
  constructor(
    private cartService: CartService,
    private sharedService: SharedService
  ) {}
  public getAuth(): boolean {
    return this.auth;
  }

  pageSize = 8; // Number of items to display per page
  pageSizeOptions: number[] = [8, 16, 24, 32]; // Options for items per page
  currentPage = 0; // Current page index
  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex;
  }
  getProductsOnCurrentPage(): Product[] {
    const startIndex = this.currentPage * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.myProducts.slice(startIndex, endIndex);
  }

  addToCart(product: Product): void {
    this.cartService.addToCart(product);
  }

  // onCategorySelected(category: string) {
  //   this.selectedCategory = category;
  // }
  // getSelectedProductId(): string | null {
  //   return this.cartService.getSelectedProduct();
  // }
  ngOnInit() {
    this.sharedService.data$.subscribe((data) => {
      console.log(data);
      this.searchProduct = data;
    });
    let z: Observable<Product[]>;
    z = this.rs.getProducts();
    z.subscribe({
      next: (product) => {
        this.myProducts = product;
        console.log("All Products");
        console.log(this.myProducts);

        this.latestProducts = [...this.myProducts].reverse().slice(0, 4);
        console.log("Latest Products");
        console.log(this.latestProducts);

        this.tempProducts = this.myProducts.filter( p => p.productStatus !== "Sold");
        console.log("Temp Products");
        console.log(this.tempProducts);

        const randomIndices: number[] = [];
        while (randomIndices.length < 4) {
          const randomIndex = Math.floor(
            Math.random() * this.tempProducts.length
          );
          if (!randomIndices.includes(randomIndex)) {
            randomIndices.push(randomIndex);
          }
        }

        // Populate recommendedProducts with random products
        this.recommendedProducts = randomIndices.map(
          (index) => this.tempProducts[index]
        );

        console.log( "Recommended Products");
        console.log(this.recommendedProducts);

      },
      error: (e) => console.error(e),
      complete: () => console.info('get complete'),
    });
    // this.getLocation();
  }
  // latitude: number;
  // longitude: number;
  // errorMessage: string;
  // placeName: string;
  // getLocation() {
  //   if ('geolocation' in navigator) {
  //     navigator.geolocation.getCurrentPosition(
  //
  //       (position) => {
  //         this.latitude = position.coords.latitude;
  //         this.longitude = position.coords.longitude;
  //         this.reverseGeocode(this.latitude, this.longitude);
  //       },
  //       (error) => {
  //         this.errorMessage = 'Error: ' + error.message;
  //
  //       }
  //     );
  //   } else {
  //     this.errorMessage = 'Geolocation is not available in your browser.';
  //   }
  // }
  // reverseGeocode(latitude: number, longitude: number) {
  //   this.rs.reverseGeocode(latitude, longitude).subscribe(
  //     (response: any) => {
  //       this.placeName = response.display_name;
  //     },
  //     (error) => {
  //       this.errorMessage = 'Error: ' + error.message;
  //     }
  //   );
  // }

  images: string[] = [
    '../../assets/images/Carousal_Image_1.jpg',
    '../../assets/images/Carousal_Image_2.jpg',
    '../../assets/images/Carousal_Image_3.jpg',
    '../../assets/images/Carousal_Image_4.jpg',
    '../../assets/images/Carousal_Image_5.jpg',
  ];

  pauseOnHover = true;
}
