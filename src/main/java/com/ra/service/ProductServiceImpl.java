package com.ra.service;


import com.ra.dto.request.ProductRequestDTO;
import com.ra.dto.response.ProductResponseDTO;
import com.ra.model.Product;
import com.ra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadService uploadService;
    @Override
    public List<ProductResponseDTO> getAll() {
        List<Product> list = productRepository.findAll();
        return list.stream().map(ProductResponseDTO::new).toList();
    }

    @Override
    public ProductResponseDTO save(ProductRequestDTO product) {
        Product productNew = new Product();
        productNew.setName(product.getName());
        productNew.setPrice(product.getPrice());
        // upload file
        String fileName = uploadService.uploadImage(product.getFile());
        productNew.setImage(fileName);
        // lưu da
        productRepository.save(productNew);
        // convert Product Entity => ProductResponseDTO
        return new ProductResponseDTO(productNew);
    }
}
