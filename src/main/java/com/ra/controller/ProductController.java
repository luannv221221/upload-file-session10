package com.ra.controller;

import com.ra.dto.request.ProductRequestDTO;
import com.ra.dto.response.ProductResponseDTO;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/products")
    public ResponseEntity<?> save(@ModelAttribute("product") ProductRequestDTO productRequestDTO){
       ProductResponseDTO productResponseDTO = productService.save(productRequestDTO);
       return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }

}
