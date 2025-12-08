package com.autoCash.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class CarFormData {
  private Long sallerId;
  private Long brandId;
  private Long modelId;
  private Long cityId;
  
  private Double price;
  private Integer year;
  private Integer month;
  private String mileage;
}
