

export interface Profile {
  _id: {
    $oid: string;
  };
  name: string;
  email: string;
  password: string;
  gender: string;
  address: {
    streetName: string;
    landmark: string;
    state: string;
    city: string;
    pinCode: string;
    additionalField1: string;
    additionalField2: string;
  };
  contactNum: string;
}

