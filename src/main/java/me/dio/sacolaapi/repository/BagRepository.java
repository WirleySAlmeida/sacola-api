package me.dio.sacolaapi.repository;

import me.dio.sacolaapi.model.Bag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagRepository extends JpaRepository<Bag, Long> {
}
