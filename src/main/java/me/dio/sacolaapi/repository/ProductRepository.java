package me.dio.sacolaapi.repository;

import me.dio.sacolaapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
