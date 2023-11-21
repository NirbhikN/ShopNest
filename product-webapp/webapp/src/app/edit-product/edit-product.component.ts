import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {MyRestService} from "../services/my-rest.service";
import {Profile} from "../model/Profile.model";
import {Product} from "../model/Product.model";

import {JwtPayload} from "../model/JwtPayload.model";
import jwtDecode from "jwt-decode";
import {Observable} from "rxjs";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit{
  updatedProduct:Product;
  public href: string = "";
  public productId: string = "";
  imagename:string

  productData:Product
  productForm: FormGroup;
  selectedImage: string | null = null;
   ngOnInit() {
    this.href = this.router.url;
    this.productId=this.href.substring(10,this.href.length-5);

      this.rs.getProductById(this.productId).subscribe(
        data=>{
          this.productData=data;
          this.productForm.patchValue({
              productName: this.productData.productName,
              productPrice: this.productData.productPrice,
              productCategory: this.productData.productCategory,
              productCondition: this.productData.productCondition,
              productDesc: this.productData.productDesc,
              productImgUrl: this.imagename
            });

        }
      )


  }
  constructor(private formBuilder: FormBuilder,private rs: MyRestService, private router:Router,private toaster:ToastrService) {
    this.productForm = this.formBuilder.group({
      productName: [''],
      productPrice: [''],
      productCategory: [''],
      productCondition: [''],
      productDesc: [''],
      productImgUrl: [''],

    });
  }

    editForm() {
        this.updatedProduct = this.productForm.value;
        this.updatedProduct.productImgUrl = this.imagename
      console.log(this.updatedProduct)

        let q: Observable<any> = this.rs.editProduct(this.productId,this.updatedProduct);
        q.subscribe({
            next: (v) => console.log( v),
            error: (e) => {
                console.error(e);
            },
            complete: () => {
                this.toaster.success("Product Edited")
                this.router.navigate([`/products/${this.productId}`]); // Use an array for navigation
            },
        });

    }


  onImageSelected(event: any) {
    this.imagename = event.target.files[0].name;
    console.log(this.imagename)
  }
  uploadImage() {
    // Handle image upload logic here (if needed)
    console.log('Image uploaded:', this.selectedImage);
  }



}
