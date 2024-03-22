package tech.kimari.userreview.repository;

import org.springframework.data.repository.CrudRepository;
import tech.kimari.userreview.entity.Validation;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Integer> {
    Optional<Validation> findByCode(String code);
}
