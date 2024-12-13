package java_spring_basic.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import java_spring_basic.api.domain.user.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
