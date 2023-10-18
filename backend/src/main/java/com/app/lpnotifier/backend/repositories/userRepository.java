package com.app.lpnotifier.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.lpnotifier.backend.model.user;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface userRepository extends JpaRepository<user, Integer> {
    void deleteUserById(Integer id);
    Optional<user> findUserById(Integer id);
    Optional<user> findUserByEmail(String email);
    user findByUsername(String username);
    user findByEmail(String email);

    @Query("SELECT u.enable, COUNT(*) AS cantidad FROM user u GROUP BY u.enable ORDER BY cantidad DESC")
    List<String> countuserByEnable();
}
