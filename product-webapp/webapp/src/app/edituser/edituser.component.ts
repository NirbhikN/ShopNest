import { Component } from '@angular/core';
import {Profile} from "../model/Profile.model";
import {HttpClient} from "@angular/common/http";
import jwtDecode from "jwt-decode";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {JwtPayload} from "../model/JwtPayload.model";
import {Observable} from "rxjs";
import {MyRestService} from "../services/my-rest.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent {
  token = localStorage.getItem('token');
  decodedToken = jwtDecode(this.token);
  userData: Profile;
  editForm:FormGroup;
  userName:string;
  userEmail:string;
  userId:string;




  // @ts-ignore
 // userEmail = this.decodedToken.sub;
  private apiUrl :string = 'http://localhost:9004/user';
  constructor(private http: HttpClient ,private toaster:ToastrService,private rs: MyRestService, private formBuilder:FormBuilder) {
    this.editForm = this.formBuilder.group({
      name: {value:"", disabled: true},
      email: {value:"", disabled: true},
      gender: [''],
      contactNum:['',[Validators.required, Validators.pattern(/^\d{10}$/)]],
      address:this.formBuilder.group({
        streetName: [''],
        city: [''],
        landmark: [''],
        state: [''],
        pinCode: [''],
        additionalField1: [''],
        additionalField2: ['']


      })


    });
  }

  ngOnInit(){
    const decodedToken: JwtPayload = jwtDecode(this.token);
    this.userName = decodedToken.name;
    this.userEmail = jwtDecode(this.token);
    this.userId=decodedToken.userId;

    this.editForm.get('name').setValue(this.userName);
    this.editForm.get('email').setValue(this.userEmail.sub);

    this.rs.getUserById(this.userId).subscribe(
    data=>{
        this.userData=data;
        this.editForm.patchValue({

          gender:this.userData.gender,
          contactNum:this.userData.contactNum,
          address:this.userData.address,

      });
        console.log(this.userData.gender)
      console.log(this.userData.address)

      }
    )

  }
  clearForm() {      //Clear the form when the details are added.
    this.editForm.get('address').reset();
    this.editForm.get('contactNum').reset();
    this.editForm.get('gender').reset()
  }

  editUser(){                     //Added user details
    console.log(this.editForm)
    let q: Observable<any> = this.rs.editUser(this.userId,this.editForm.value);

    q.subscribe({
      next: (v) => this.toaster.success("UserDetails Added Successfully")

        ,
      error: (e) => {
        console.error(e);
      },
      complete: () => this.clearForm(),
    });
  }

}
