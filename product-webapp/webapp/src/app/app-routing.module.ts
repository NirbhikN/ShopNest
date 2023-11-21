import { NgModule } from '@angular/core';

import { LoginComponent } from './login/login.component';
import { SlotComponent } from './slot/slot.component';
import { AddProductComponent } from './add-product/add-product.component';
import { HomeComponent } from './home/home.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { OrderProductComponent } from './order-product/order-product.component';
import {ViewUserComponent} from "./viewuser/viewuser.component";
import {RegistrationComponent} from "./registration/registration.component";
import {EdituserComponent} from "./edituser/edituser.component";
import {authGuard} from "./services/auth.guard";
import {CategoriesComponent} from "./categories/categories.component";
import {EditProductComponent} from "./edit-product/edit-product.component";
import {ChatAppComponent} from "./chat-app/chat-app.component";
import {CartComponent} from "./cart/cart.component";
import {RouterModule, Routes} from "@angular/router";
import {ViewSlotComponent} from "./view-slot/view-slot.component";


const routes: Routes = [

  { path: '', component: HomeComponent,},
  { path: 'slot', component: SlotComponent , canActivate: [authGuard]},
  { path: 'products/:productId/slot', component: SlotComponent , canActivate: [authGuard]},
  { path: 'products/:productId/edit', component: EditProductComponent , canActivate: [authGuard]},
  { path: 'products/:id', component: ProductDetailsComponent},
  { path: 'addproduct', component: AddProductComponent ,canActivate: [authGuard]},
  { path: 'login', component: LoginComponent },
  { path: 'order', component: OrderProductComponent ,canActivate: [authGuard]},
  { path: 'view', component: ViewUserComponent , canActivate: [authGuard]},
  { path: 'view/slots', component: ViewSlotComponent , canActivate: [authGuard]},
  { path: 'register', component: RegistrationComponent },
  { path: 'edit', component: EdituserComponent,canActivate: [authGuard] },
  { path: 'categories/:category', component: CategoriesComponent },
  { path: 'chat', component: ChatAppComponent },
  { path: 'cart', component: CartComponent },

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule,],
    declarations: [

    ]
})
export class AppRoutingModule {}
