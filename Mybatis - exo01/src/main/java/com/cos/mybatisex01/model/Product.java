package com.cos.mybatisex01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
	private int id;
	private String name;
	private String code;
}
