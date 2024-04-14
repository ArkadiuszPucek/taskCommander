package com.arcadio.domain.user.userDetails.repository;

import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userRole.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findUserById(Long userId);
    List<User> findAll();

    List<User> findAllByRoles(UserRole sales_engineer);
}
