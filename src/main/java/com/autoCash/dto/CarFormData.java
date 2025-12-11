package com.autoCash.dto;


import lombok.Data;

@Data

public class CarFormData {
  private Long car_id;
  private Long id_seller;
  private Long id_brand;
  private Long id_model;
  private Long yearId;
  private Long monthId;
  
  private Double car_price;
  private Integer mile_age;
  private String city;
}
