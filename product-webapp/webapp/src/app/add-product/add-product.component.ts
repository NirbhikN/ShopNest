/* eslint-disable prefer-const */
/* eslint-disable @typescript-eslint/no-explicit-any */
import {Component, inject, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MyRestService} from "../services/my-rest.service";
import {Observable} from "rxjs";
import jwtDecode from "jwt-decode";
import {HttpClient, HttpEvent} from "@angular/common/http";
import {JwtPayload} from "../model/JwtPayload.model";
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  secret = 'YourSecretKeyHere';
  productForm: FormGroup;
  slotForm: FormGroup
  selectedImage: string | null = null;
  imagename: string;
  sellerId: string;
  token: string = localStorage.getItem('token');

  selectedDateTimes: { sellerId: string, bookingDate: string, startTime: string, endTime: string }[] = [];

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private rs: MyRestService, private toaster: ToastrService, private router: Router) {
    this.productForm = this.formBuilder.group({
      productName: [''],
      productPrice: [''],
      productCategory: [''],
      productCondition: [''],
      productDesc: [''],
      productImgUrl: [''],
      slots: this.formBuilder.array([])
    });

    this.slotForm = this.formBuilder.group({
      sellerId: [this.sellerId],
    });

    this.addDateTime(); // Initialize the form controls for index 0
  }

  addDateTime() {
    const index = this.selectedDateTimes.length;
    this.selectedDateTimes.push({sellerId: this.sellerId, bookingDate: '', startTime: '', endTime: ''});

    // Add form controls dynamically to the slotForm FormGroup
    this.slotForm.addControl('bookingDate' + index, this.formBuilder.control('', Validators.required));
    this.slotForm.addControl('startTime' + index, this.formBuilder.control('', Validators.required));
    this.slotForm.addControl('endTime' + index, this.formBuilder.control('', Validators.required));
  }

  removeDateTime(index: number) {
    // Remove the form controls and the corresponding entry from selectedDateTimes
    this.slotForm.removeControl('bookingDate' + index);
    this.slotForm.removeControl('startTime' + index);
    this.slotForm.removeControl('endTime' + index);
    this.selectedDateTimes.splice(index, 1);
  }

  bookSlot() {
    // Access the entered slot data through the slotForm FormGroup controls
    const slotData: { sellerId: string, bookingDate: string, startTime: string, endTime: string }[] = [];

    for (let i = 0; i < this.selectedDateTimes.length; i++) {
      const dateControl = this.slotForm.get('bookingDate' + i);
      const startTimeControl = this.slotForm.get('startTime' + i);
      const endTimeControl = this.slotForm.get('endTime' + i);

      if (dateControl && startTimeControl && endTimeControl) {
        slotData.push({
          sellerId: this.sellerId, // Fixed seller ID
          bookingDate: dateControl.value,
          startTime: startTimeControl.value,
          endTime: endTimeControl.value
        });
      }
    }

    // Handle the array of entered slot data
    const slotsArray = this.productForm.get('slots') as FormArray;
    // slotsArray.push(this.formBuilder.group(slotData));
    // console.log("SD", slotData);

    for (let i = slotData.length - 1; i >= 0; i--) {
      slotsArray.insert(0, this.formBuilder.group(slotData[i]));
    }

  }


  ngOnInit() {
    const decodedToken: JwtPayload = jwtDecode(this.token);
    this.sellerId = decodedToken.userId;
  }

  // onImageSelected(event: any) {
  //   // Handle image selection
  //   const file = event.target.files[0];
  //   if (file) {
  //     // Create a URL for the selected file and set it as the selectedImage
  //     this.selectedImage = URL.createObjectURL(file);
  //   }
  // }
  // uploadImage() {
  //   // Handle image upload logic here (if needed)
  //   console.log('Image uploaded:', this.selectedImage);
  // }

  // pushToSlots(data: any) {
  //   const slotsArray = this.productForm.get('slots') as FormArray;
  //   slotsArray.push(this.formBuilder.group(data));
  // }

  responseData: string;

  onSubmit() {
    this.bookSlot();
    const formData = this.productForm.value;
    // formData.productImage = this.selectedImage;
    console.log('Form Data:', formData);
    const decodedToken: JwtPayload = jwtDecode(this.token);
    const userId = decodedToken.userId;
    const name = decodedToken.name;
    formData.sellerId = userId;
    formData.sellerName = name;
    formData.productImgUrl = this.imagename



    // Posting product into db
    let q: Observable<any> = this.rs.addProduct(formData);

    q.subscribe({
      next: (v) => console.log(v),
      error: (e) => {
        console.error(e);
      },
      complete: () => {
        this.toaster.success("Product Added")
        this.router.navigate(['/'])
      },
    });
    // If you want to reset the form after submission, you can do:
    this.productForm.reset();
  }


  // let q: Observable<any> = this.rs.addSlot(this.productId,this.slotForm.value);
  //
  // q.subscribe({
  //   next: (v) => console.log( "V: ",v),
  //   error: (e) => {
  //     console.error(e);
  //   },
  //   complete: () => this.toaster.success("Slot Added"),
  // });

  imageUrl: string | ArrayBuffer | null = null;

  onSelected(event: any) {
    this.imagename = event.target.files[0].name;
  }

}
