package com.serhat.productservice.repository;

import com.serhat.productservice.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT p FROM ProductEntity p WHERE " +
            "(:category IS NULL OR p.category = :category) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<ProductEntity> searchProducts(@Param("category") String category, @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice);

    @Query(value = "SELECT p FROM ProductEntity p WHERE " +
            "(:availability IS NULL OR p.availability = :availability) AND " +
            "(:color IS NULL OR p.color LIKE %:color%) AND " +
            "(:brand IS NULL OR p.brand LIKE %:brand%)", nativeQuery = true)
        List<ProductEntity> filterProducts(Boolean availability, String color, String brand);

}
