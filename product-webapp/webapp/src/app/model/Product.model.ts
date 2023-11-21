export class Product {
  productId: string;
  sellerId: string;
  sellerName:string;
  productName: string;
  productDesc: string;
  productPrice: number;
  productStatus: string;
  productCondition: string;
  productLocation: string;
  productImgUrl: string;
  productCategory: string;
  slots: Slot[];
}

export class Slot {
  startTime: string;
  endTime: string;
  bookingDate: string;
  booked: boolean | null;
}
