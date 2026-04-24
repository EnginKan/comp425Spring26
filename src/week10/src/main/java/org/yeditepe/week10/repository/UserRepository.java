package org.yeditepe.week10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yeditepe.week10.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
