package com.training.springJPA_2_DB.model.product;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    private int id;

    private String name;

    private double price;
}