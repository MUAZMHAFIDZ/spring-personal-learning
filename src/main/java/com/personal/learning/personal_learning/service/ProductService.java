// service/ProductService.java
package com.personal.learning.personal_learning.service;

import com.personal.learning.personal_learning.model.Product;
import com.personal.learning.personal_learning.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String saveImage(MultipartFile file) throws IOException {
        // Folder penyimpanan relatif ke root project
        // String uploadDir = new File("src/main/resources/static/uploads").getAbsolutePath();
        String uploadDir = "E:/spring-uploads/";
        File uploadFolder = new File(uploadDir);

        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs(); // buat folder jika belum ada
        }
        // Buat nama file unik
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, filename);
        file.transferTo(filePath.toFile());
        return filename;
    }


    private void deleteImage(String imageName) {
        // String uploadDir = new File("src/main/resources/static/uploads").getAbsolutePath();
        String uploadDir = "E:/spring-uploads/";
        if (imageName != null) {
            File file = new File(uploadDir, imageName);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public Product createProduct(String title, String description, MultipartFile image) throws IOException {
        String filename = saveImage(image);
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImage(filename);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, String title, String description, MultipartFile image) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setTitle(title);
        product.setDescription(description);

        if (image != null && !image.isEmpty()) {
            deleteImage(product.getImage());
            String filename = saveImage(image);
            product.setImage(filename);
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        deleteImage(product.getImage());
        productRepository.delete(product);
    }
}
