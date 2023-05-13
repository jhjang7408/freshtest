package com.multicampus.hhh.dto;

import lombok.Data;

@Data
public class OrderDTO {

    private int orderid;
    private String userid;
    private int acid;
    private String status;
    private String image;
    private int amount;
    private int price;
    private String productname;
}
