package com.workspace.shop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shop", schema = "jpa_test_db")
public class ShopEntity {

    private int id;
    private String address;
    private Double customersDensity;
    private String shopName;
    private String owner;


    public ShopEntity() {
        //default
    }

    public ShopEntity(int id, String address, Double customersDensity, String shopName,
        String owner) {
        this(address, customersDensity, shopName, owner);
        this.id = id;
    }


    public ShopEntity(String address, Double customersDensity, String shopName,
        String owner) {
        this.address = address;
        this.customersDensity = customersDensity;
        this.shopName = shopName;
        this.owner = owner;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "address")
    @JsonProperty(value = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "customers_density")
    @JsonProperty(value = "customers_density")
    public Double getCustomersDensity() {
        return customersDensity;
    }

    public void setCustomersDensity(Double customersDensity) {
        this.customersDensity = customersDensity;
    }

    @Basic
    @Column(name = "shop_name")
    @JsonProperty(value = "shop_name")
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Basic
    @Column(name = "owner")
    @JsonProperty(value = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShopEntity that = (ShopEntity) o;
        return id == that.id &&
            Objects.equals(address, that.address) &&
            Objects.equals(customersDensity, that.customersDensity) &&
            Objects.equals(shopName, that.shopName) &&
            Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, address, customersDensity, shopName, owner);
    }
}
