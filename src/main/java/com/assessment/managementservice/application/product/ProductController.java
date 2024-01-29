package com.assessment.managementservice.application.product;

import com.assessment.managementservice.domain.product.Product;
import com.assessment.managementservice.infrastructure.product.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
@RestController
@ResponseBody
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    /**
     * Get all products
     * @return list of products
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        logger.info("Getting all products");
        List<Product> products = productRepository.findAll();
        logger.info("List of products: {}", products.stream().map(Product::toString).toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Get product by id
     * @param id product id
     * @return product
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(Integer id) {
        logger.info("Getting product with id: {}", id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            logger.info("Product with id: {} is {}", id, product.get().toString());
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            logger.info("Product with id: {} not found", id);
            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a new product
     * @param product product
     * @return product
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(Product product) {
        if(product.getBookTitle().isEmpty() || product.getBookPrice() == null || product.getBookQuantity() == null) {
            logger.warn("Book title, book price and book quantity cannot be empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // check for duplicate
        List<Product> products = productRepository.findAll();
        var duplicates = products.stream()
            .filter(p -> p.getBookTitle().equals(product.getBookTitle()))
            .findAny();
        if(duplicates.isPresent()) {
            logger.warn("Product with book title {} already exists", product.getBookTitle());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    /**
     * Update product
     * @param id product id
     * @param product product
     * @return updated product
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct(Integer id, Product product) {
        logger.info("Updating product with id: {}", id);
        var existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            logger.warn("Product with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (product.getBookTitle().isEmpty() || product.getBookPrice() == null || product.getBookQuantity() == null) {
            logger.warn("Book title, book price and book quantity cannot be empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        product.setId(id);
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
    }

    /**
     * Delete product
     * @param id product id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(Integer id) {
        logger.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }
}
