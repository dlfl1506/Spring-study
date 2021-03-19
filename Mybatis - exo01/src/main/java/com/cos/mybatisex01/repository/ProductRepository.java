package com.cos.mybatisex01.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cos.mybatisex01.model.Product;



@Mapper  // 인터페이스를 구현한 객체를 띄워주는 어노테이션
public interface ProductRepository {
	public void save(Product product);
	public void deleteById(int id);
	public void update(Product product);
	public List<Product> findAll();
	public Product findById(int id);
}
