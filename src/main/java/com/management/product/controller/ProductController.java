package com.management.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.product.dto.CreateProductDto;
import com.management.product.service.ProductService;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ProductController {
  @Autowired
  private ProductService service;

  @SneakyThrows(Exception.class)
  @PostMapping(path = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> create(@RequestBody CreateProductDto dto) {
    log.info("/api/v1/product Post is Executed");
    return service.create(dto);
  }

  @SneakyThrows(Exception.class)
  @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getAll() {
    log.info("/api/v1/products Get is Executed");
    return service.getAll();
  }

  @SneakyThrows(Exception.class)
  @DeleteMapping("/product/{id}")
  public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
    log.info("/api/v1/product/{} Delete is Executed", id);
    return service.delete(id);
  }
}
