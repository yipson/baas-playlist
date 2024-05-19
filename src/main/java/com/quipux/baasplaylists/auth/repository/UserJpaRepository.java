package com.quipux.baasplaylists.auth.repository;

import com.quipux.baasplaylists.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM USER WHERE USERNAME =?1")
    int countByUsername(String username);
}
