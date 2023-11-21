/* eslint-disable @typescript-eslint/no-unused-vars */
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {SessionStorageService} from "ngx-webstorage";
import { NavbarComponent } from './navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatButtonModule } from '@angular/material/button';
import { AddProductComponent } from './add-product/add-product.component';
import {ToastrModule} from 'ngx-toastr'
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { SlotComponent } from './slot/slot.component';
import { HomeComponent } from './home/home.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { OrderProductComponent } from './order-product/order-product.component';
import {ViewUserComponent} from "./viewuser/viewuser.component";
import {HttpClientModule} from "@angular/common/http";
import {RegistrationComponent} from "./registration/registration.component";
import {EdituserComponent} from "./edituser/edituser.component";
import { DialogboxComponent } from './dialogbox/dialogbox.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {NgOptimizedImage} from "@angular/common";
import {SwiperModule} from "swiper/angular";
import { ForgetpasswordComponent } from './forgetpassword/forgetpassword.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { CategoriesComponent } from './categories/categories.component';
import { NgxWebstorageModule } from 'ngx-webstorage';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import { ChatAppComponent } from './chat-app/chat-app.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatStepperModule} from "@angular/material/stepper";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatTabsModule} from "@angular/material/tabs";
import {CartComponent} from "./cart/cart.component";
import {MatRippleModule} from "@angular/material/core";
import {MatBadgeModule} from "@angular/material/badge";
import {MatPaginatorModule} from "@angular/material/paginator";
import { NgbDropdownModule, NgbModule, NgbSlideEvent } from '@ng-bootstrap/ng-bootstrap';
import { FooterComponent } from './footer/footer.component';
import { TruncatePipe } from './truncate.pipe';
import {MatChipsModule} from '@angular/material/chips';
import { ViewSlotComponent } from './view-slot/view-slot.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    AddProductComponent,
    SlotComponent,
    HomeComponent,
    ProductDetailsComponent,
    OrderProductComponent,
    ViewUserComponent,
    RegistrationComponent,
    EdituserComponent,
    DialogboxComponent,
    ForgetpasswordComponent,
    EditProductComponent,
    CategoriesComponent,
    ChatAppComponent,
    CartComponent,
    FooterComponent,
    TruncatePipe,
    ViewSlotComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    MatDatepickerModule,
    MatCardModule,
    HttpClientModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    ToastrModule.forRoot(),
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatExpansionModule,
    NgOptimizedImage,
    SwiperModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatSidenavModule,
    MatStepperModule,
    MatSnackBarModule,
    MatTabsModule,
    MatRippleModule,
    MatBadgeModule,
      MatChipsModule,
    NgxWebstorageModule.forRoot(),
    MatPaginatorModule,
    NgbDropdownModule,
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
