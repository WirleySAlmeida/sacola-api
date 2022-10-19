package me.dio.sacolaapi.repository;

import me.dio.sacolaapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
