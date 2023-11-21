package com.stackroute.userservice.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
public class User{
    @Id
    String id = UUID.randomUUID().toString();
    String name;
    String email;
    String password;
    String password2;
    String gender;
    private Address address;
    String contactNum;


    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public User(String id, String name, String email, String password, String gender, Address address, String contactNum, String password2) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.contactNum = contactNum;
        this.password2 = password2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", address=" + address +
                ", contactNum='" + contactNum + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}

 class Address {
    private String streetName;
    private String landmark;
    private String state;
    private String city;
    private String pinCode;
    private String additionalField1;
    private String additionalField2;

     public String getStreetName() {
         return streetName;
     }

     public void setStreetName(String streetName) {
         this.streetName = streetName;
     }

     public String getLandmark() {
         return landmark;
     }

     public void setLandmark(String landmark) {
         this.landmark = landmark;
     }

     public String getState() {
         return state;
     }

     public void setState(String state) {
         this.state = state;
     }

     public String getCity() {
         return city;
     }

     public void setCity(String city) {
         this.city = city;
     }

     public String getPinCode() {
         return pinCode;
     }

     public void setPinCode(String pinCode) {
         this.pinCode = pinCode;
     }

     public String getAdditionalField1() {
         return additionalField1;
     }

     public void setAdditionalField1(String additionalField1) {
         this.additionalField1 = additionalField1;
     }

     public String getAdditionalField2() {
         return additionalField2;
     }

     public void setAdditionalField2(String additionalField2) {
         this.additionalField2 = additionalField2;
     }

     public Address(String streetName, String landmark, String state, String city, String pinCode, String additionalField1, String additionalField2) {
         this.streetName = streetName;
         this.landmark = landmark;
         this.state = state;
         this.city = city;
         this.pinCode = pinCode;
         this.additionalField1 = additionalField1;
         this.additionalField2 = additionalField2;
     }

     @Override
     public String toString() {
         return "Address{" +
                 "streetName='" + streetName + '\'' +
                 ", landmark='" + landmark + '\'' +
                 ", state='" + state + '\'' +
                 ", city='" + city + '\'' +
                 ", pinCode='" + pinCode + '\'' +
                 ", additionalField1='" + additionalField1 + '\'' +
                 ", additionalField2='" + additionalField2 + '\'' +
                 '}';
     }

     public Address() {
     }
 }