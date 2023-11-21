import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import {ToastrService} from 'ngx-toastr'
import { MyRestService } from '../services/my-rest.service';
import { Router } from '@angular/router';
import {AuthService} from "../services/auth.service";
import {Observable} from "rxjs";
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  registerForm:any = FormGroup;
  submitted = false;
  jsonadd: any;
  get f() { return this.registerForm.controls; }

  constructor( private formBuilder: FormBuilder ,
               private auth: AuthService,
               private toaster: ToastrService,
               private route: Router
  ){}
  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
        ],
      ],
      password2: ['', [Validators.required]],

    },
      {
        validators: this.passwordMatchValidator('password', 'password2'),
      })

  }

      onSubmit() {
        this.submitted = true;
        if (this.registerForm.invalid) {
          console.log("invalid")
          this.toaster.warning("Form Invalid");
        }
        else {
          console.log(this.registerForm.get('name').value)
          this.jsonadd = {
            "name": this.registerForm.get('name').value,
            "email": this.registerForm.get('email').value,
            "password": this.registerForm.get('password').value,
            "password2": this.registerForm.get('password2').value
          }

          let auth: Observable<any> = this.auth.registerUser(this.jsonadd);
          auth.subscribe({
            next: (value) => {
              this.toaster.success("User Registered Successfully");
              console.log(value)
            },
            error: (e) => {
              console.error(this.toaster.error('User Registration Failed'));
            },
            complete: () => {
              this.registerForm.reset();
              this.registerForm.controls['name'].setErrors(null);
              this.registerForm.controls['email'].setErrors(null);
              this.registerForm.controls['password'].setErrors(null);
              this.registerForm.controls['password2'].setErrors(null);
            },
          });
        }
   }

  passwordMatchValidator(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ passwordMismatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  navigate(){
      this.route.navigate(['/login']); // '/login' is the route to your login page
  }

}


