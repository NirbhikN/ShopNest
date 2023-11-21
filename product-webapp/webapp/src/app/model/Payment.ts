export class Payment{
  name: string;
  currency: 'inr';
  amount: number;
  quantity: number;
  successUrl: string;
  cancelUrl: string;

  constructor(name: string, currency: "inr", amount: number, quantity: number, successUrl: string, cancelUrl: string) {
    this.name = name;
    this.currency = currency;
    this.amount = amount;
    this.quantity = quantity;
    this.successUrl = successUrl;
    this.cancelUrl = cancelUrl;
  }
}


