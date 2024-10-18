package org.example.springlab2.repositories;

import org.example.springlab2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query("select distinct u from User u left join fetch u.roles where u.username = ?1")
    User findByUsernameFetchRoles(String username);
}
