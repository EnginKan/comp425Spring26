package org.yeditepe.week10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.yeditepe.week10.entity.User;
import org.yeditepe.week10.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository repo;

    @Test
    void shouldSaveUser() {
        User user = repo.save(new User("Ali", "ali@test.com"));
        assertNotNull(user.getId());
    }
}
