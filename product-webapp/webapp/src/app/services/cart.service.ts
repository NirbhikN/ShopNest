import {Injectable} from '@angular/core';
import {Product} from "../model/Product.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Subject} from "rxjs";
import {SessionStorageService} from "ngx-webstorage";

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cartItems: CartItem[] = [];
  cartItemsChanged = new Subject<any[]>();
  totalItemCountChanged = new Subject<number>();
  totalPrice = new Subject<number>();
  constructor(private snackBar: MatSnackBar, private  sS: SessionStorageService) {}
  addToCart(product: Product): void {
    const existingItem = this.cartItems.find((item) => item.product.productId === product.productId);
    if (existingItem) {
      // existingItem.quantity++;
      this.snackBar.open('Product Already Added', 'Close', { duration: 2000 });
    } else {

      this.cartItems.push({ product, quantity: 1 });
      this.sS.store("cartItems" , this.cartItems);
      const storedCartItems = this.sS.retrieve('cartItems');
      this.cartItemsChanged.next(storedCartItems);
      this.totalItemCountChanged.next(storedCartItems.length);
      this.calculateTotalPrice();
    }
  }

  clearCart(): void {
    // Clear the cart items array
    this.cartItems = [];

    // Update session storage to reflect the empty cart
    this.sS.store('cartItems', []);
    // Emit events or perform any other necessary updates
    this.cartItemsChanged.next([]);
    this.totalItemCountChanged.next(0);
    this.calculateTotalPrice();
  }

  removeFromCart(product: Product): void {
    // Retrieve cart items from session storage
    const storedCartItems = this.sS.retrieve('cartItems');

    // Find the index of the cart item based on a condition (e.g., product ID)
    const index = storedCartItems.findIndex((item) => item.product.productId === product.productId);

    // Check if the item was found (index is not -1)
    if (index !== -1) {
      // Check if the quantity of the item is greater than 1
      if (storedCartItems[index].quantity > 1) {
        storedCartItems[index].quantity--;
      } else {
        // Use splice to remove the item at the found index
        storedCartItems.splice(index, 1);
      }

      // Update cartItems in session storage with the modified array
      this.sS.store('cartItems', storedCartItems);

      // Emit events or perform any other necessary updates
      this.cartItemsChanged.next(storedCartItems);
      this.totalItemCountChanged.next(storedCartItems.length);
      this.calculateTotalPrice();
    }
  }


  // // Method to get the cart items
  getCartItems(): CartItem[] {
    return this.sS.retrieve('cartItems');
  }

  private calculateTotalPrice(): void {
    const totalPrice = this.getTotalPrice();
    this.totalPrice.next(totalPrice); // Emit the updated total price
  }
  getTotalPrice(): number {
    const storedCartItems = this.sS.retrieve('cartItems');
    return storedCartItems.reduce((total: number, item: { product: { productPrice: number; }; quantity: number; }) => {
      // Calculate the price of the current item and add it to the total
      const itemPrice = item.product.productPrice * item.quantity;
      return total + itemPrice;
    }, 0);
  }
}

export interface CartItem {
  product: Product;
  quantity: number;
}
