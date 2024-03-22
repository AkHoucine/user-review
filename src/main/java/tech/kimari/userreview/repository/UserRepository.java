package tech.kimari.userreview.repository;

import org.springframework.data.repository.CrudRepository;
import tech.kimari.userreview.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
