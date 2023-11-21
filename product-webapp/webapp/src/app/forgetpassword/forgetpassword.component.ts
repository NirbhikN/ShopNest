import { Component } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {AbstractControl, AsyncValidatorFn, FormBuilder, ValidationErrors, Validators} from "@angular/forms";
import {MyRestService} from "../services/my-rest.service";
import {debounceTime, map, Observable, switchMap, take, timer} from "rxjs";
import { MatSnackBar } from '@angular/material/snack-bar';
import {error} from "@angular/compiler-cli/src/transformers/util";
class ForgotPasswordDialogComponent {
}



@Component({
  selector: 'app-forgetpassword',
  templateUrl: './forgetpassword.component.html',
  styleUrls: ['./forgetpassword.component.css']
})
export class ForgetpasswordComponent {

  constructor(private dialogRef: MatDialogRef<ForgotPasswordDialogComponent>,
              private formBuilder: FormBuilder,
              private rs : MyRestService,
              private snackBar: MatSnackBar) {}

  // credentials = { password: '', confirmPassword: '' };
  // [emailVerify(this.rs)]
  firstFormGroup = this.formBuilder.group({
    email: ['',[ Validators.required]],
  });
  secondFormGroup = this.formBuilder.group({
    otp: ['', Validators.required],
  });
  thirdFormGroup = this.formBuilder.group({
    password: ['', Validators.required],
    password2 :['',Validators.minLength(5)]
  });
 isLinear = true;
  otp : number;
  isOTPValid : boolean;
  check : boolean;

  sendOtp(stepper: any) {
    this.rs.sendOTP(this.firstFormGroup.get('email').value).subscribe(
      (data) => {
        this.otp = data;
        console.log(this.otp);
        stepper.next();
      },
      (error) => {
        this.snackBar.open('Invalid Email! Please try again.', 'Close', { duration: 3000 });
      }
    );
  }
  verifyOtp(stepper: any) {

    console.log(this.secondFormGroup.get('otp').value)
    if(this.otp.toString() === this.secondFormGroup.get('otp').value){
      console.log(this.otp)
      this.isOTPValid = true;
    }
    if (this.isOTPValid) {
      stepper.next(); // Move to the next step (password reset)
    } else {
      this.snackBar.open('Invalid OTP. Please try again.', 'Close', { duration: 3000 });
    }
  //   this.rs.verifyOTP(this.firstFormGroup.get('otp').value).subscribe((data) =>{
  //     this.check = data;
  //     console.log(this.otp);
  //   })
  }


  resetPassword() {
    let password = {
      email: this.firstFormGroup.get('email').value,
      password: this.thirdFormGroup.get('password').value,
      password2: this.thirdFormGroup.get('password2').value
    }

    this.rs.resetPassword(password).subscribe((data) => {
      this.check = data;
      console.log(this.check);
    })

    this.dialogRef.close();
  }

}


// function emailVerify(rs : MyRestService): AsyncValidatorFn {
//   return (control: AbstractControl): Observable<ValidationErrors | null> => {
//     const email = control.value;
//
//     // Delay the validation by 300 milliseconds to reduce API requests
//     return timer(1000).pipe(
//       debounceTime(1000),
//       take(1), // Take only the first emitted value to prevent multiple requests
//       switchMap(() => {
//         // Check if the email exists in the database
//         return rs.getEmail(email);
//       }),
//       map((exists: boolean) => {
//         return exists ? null : { emailNotExists: true };
//       })
//     );
//   };
// }
