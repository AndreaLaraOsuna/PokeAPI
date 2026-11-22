package lara.carmen.PokeAPI.repository;

import lara.carmen.PokeAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
