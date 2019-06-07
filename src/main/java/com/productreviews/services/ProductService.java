package com.productreviews.services;

import com.productreviews.converters.ProductConverter;
import com.productreviews.data.ProductData;
import com.productreviews.entities.Product;
import com.productreviews.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductData getById(Integer id) {
        ProductData productData = new ProductData();
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            productConverter.modelToData(productData, optionalProduct.get());
            return productData;
        } else {
            return null;
        }
    }

    public void add(ProductData productData) {
        Product product = new Product();
        productConverter.dataToModel(productData, product);
        productRepository.save(product);
    }

    public void addAll(Iterable<Product> productIterable) {
        productRepository.saveAll(productIterable);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public List<ProductData> getProducts() {
        Iterable<Product> productIterable = productRepository.findAll();

        List<ProductData> productsData = new ArrayList<>();
        for (Product product : productIterable) {
            ProductData productData = new ProductData();
            productConverter.modelToData(productData, product);
            productsData.add(productData);
        }

        return productsData;
    }

    public void editProduct(Integer productId, ProductData productData){
        Product product = productRepository.findById(productId).get();
        product.setName(productData.getName());

        if (!productData.getDescription().equals("")){
            product.setDescription(productData.getDescription());
        }

        if (productData.getPrice()!=null){
            product.setPrice(productData.getPrice());
        }

        productRepository.save(product);
    }

}
