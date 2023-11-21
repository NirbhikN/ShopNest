export class Order{
    orderId: {
        $oid: string;
    };
    sellerId: string;
    productId:string;
    buyerId: string;
    productPrice:string;
    productDesc:string;
    productName:string;

    constructor() {
        // this.orderId = { $oid: '' };
        // this.sellerId = '';
        // this.productId = '';
        this.buyerId = '';
    }

}
