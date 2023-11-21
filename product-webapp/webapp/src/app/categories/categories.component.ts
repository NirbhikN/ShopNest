import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import {MyRestService} from "../services/my-rest.service";
import {ActivatedRoute} from "@angular/router";
import {Product} from "../model/Product.model";
import {SharedService} from "../services/shared.service";
import {PageEvent} from "@angular/material/paginator";
import {CartService} from "../services/cart.service";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements  OnInit{
  products: Product[] = [];
  selectedCategory: string;
  constructor(
    private rs: MyRestService,
    private route: ActivatedRoute,
    private ss :SharedService,
    private cartService : CartService
  ) {}
  @Output() categorySelected = new EventEmitter<string>();
  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      this.selectedCategory = params.get('category');
      console.log(this.selectedCategory)
      this.loadProductsByCategory(this.selectedCategory);
      console.log(this.loadProductsByCategory)
    });

    this.ss.data$.subscribe((data) => {
      console.log(data);
      this.searchProduct = data;
    });
  }

  pageSize = 9; // Number of items to display per page
  pageSizeOptions: number[] = [9, 18, 27, 36]; // Options for items per page
  currentPage = 0; // Current page index
  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex;
  }
  getProductsOnCurrentPage(): Product[] {
    const startIndex = this.currentPage * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.products.slice(startIndex, endIndex);
  }

  addToCart(product: Product): void {
    this.cartService.addToCart(product);
  }

  loadProductsByCategory(category: string) {
    this.rs.getProductsByCategory(category).subscribe((products) => {
      this.products = products;
      console.log(this.products)
    });
  }

  onCategoryClick(category: string) {
    this.categorySelected.emit(category);
  }

  searchProduct :string = ''

}
