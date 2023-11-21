import {Component, OnInit} from '@angular/core';
import {Product} from "../model/Product.model";
import {MyRestService} from "../services/my-rest.service";

import {FormBuilder, FormGroup} from "@angular/forms";
import {Observable} from "rxjs";
import {ToastrService} from "ngx-toastr";
import {JwtPayload} from "../model/JwtPayload.model";
import jwtDecode from "jwt-decode";
import {Slot} from "../model/Slot.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-slot',
  templateUrl: './slot.component.html',
  styleUrls: ['./slot.component.css'],
})
export class SlotComponent implements OnInit{
  public href: string = "";
  public productId: string = "";

  productData:Product;

  slotForm:FormGroup;

  constructor(private rs: MyRestService, private router:Router, private formBuilder:FormBuilder,private toaster:ToastrService) {
      this.slotForm = this.formBuilder.group({
          startTime: [''],
          endTime: [''],
          bookingDate: [''],
          buyerId:[''],
          booked:['']
      });

  }

      buyerId:string;
      token: string = localStorage.getItem('token');

      slotData:Slot[]=[]

  ngOnInit() {
    this.href = this.router.url;
    this.productId = this.href.substring(10, this.href.length - 5);

    // Product Details
    this.rs.getProductById(this.productId).subscribe(
        data => {
          this.productData = data;
          console.log(this.productData)
        })

      this.rs.getSlotsByProductId(this.productId).subscribe(
          (data)=>{
            this.slotData=data;
            console.log("slotdata", this.slotData)
          }
      )

      const decodedToken: JwtPayload = jwtDecode(this.token);
    this.buyerId = decodedToken.userId;
      // console.log("BuyerId "+this.buyerId);


  }

    selectedSlot: any = null;

    onChipSelectionChange(slot: any) {
        if (this.selectedSlot !== slot) {
            // Add the buyerId property to the selected slot
            this.selectedSlot = { ...slot, buyerId: this.buyerId };
        } else {
            // If the same slot is selected again, deselect it
            this.selectedSlot = null;
        }

        // Console log the selected slot
        console.log('Selected Slot:', this.selectedSlot);
    }

  bookSlot() {
    console.log("Booked Slot")
    // this.slotForm.buyerId=this.buyerId;
    // this.slotForm.get('buyerId').setValue(this.buyerId);
    // console.log(this.slotForm.value)
    //
    // slot.startTime=this.selectedSlot.startTime;

      const slotBook:Slot={
        startTime:this.selectedSlot.startTime,
        endTime:this.selectedSlot.endTime,
        bookingDate:this.selectedSlot.bookingDate,
        buyerId:this.selectedSlot.buyerId,
        sellerId:this.productData.sellerId,
        productId:this.productData.productId,
        slotId:null,
        booked:null,
      }

    let q: Observable<any> = this.rs.addSlot(this.productId,slotBook);

    q.subscribe({
      next: (v) => console.log("V: ", v),
      error: (e) => {
        console.error(e);
      },
      complete: () => {

          window.location.reload();
          this.toaster.success("Slot Added")
      },
    });


  }
}
