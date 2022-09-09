package com.management.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.product.dto.CreateProductDto;
import com.management.product.model.Product;
import com.management.product.repository.ProductRepository;

import lombok.SneakyThrows;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProductService {

  @Autowired
  private ProductRepository repository;

  @SneakyThrows(Exception.class)
  public ResponseEntity<Object> create(CreateProductDto dto) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    Product book = new Product();

    book.setName(dto.getName().trim());
    book.setPrice(dto.getPrice());

    repository.save(book);

    Map<String, Object> res = new HashMap<>();
    res.put("success", true);
    res.put("message", "Input data berhasil");

    return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(res);

  }

  @SneakyThrows(Exception.class)
  public ResponseEntity<Object> getAll() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    List<Product> product = repository.findAll();

    Map<String, Object> res = new HashMap<>();
    res.put("success", true);
    res.put("message", "Get data berhasil");
    res.put("data", product);

    return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
  }

  @SneakyThrows(Exception.class)
  public ResponseEntity<Object> delete(Long id) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    Product product = repository.findById(id).orElse(new Product());

    Map<String, Object> res = new HashMap<>();

    if (product.getId() != null) {
      repository.deleteById(id);
      res.put("success", true);
      res.put("message", "Berhasil Delete Data");

      return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
    } else {
      res.put("success", false);
      res.put("message", "Data tidak ditemukan");

      return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
    }
  }

}
