package com.quipux.baasplaylists.adapter.driven.persistence;

import com.quipux.baasplaylists.adapter.driven.persistence.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistJpaRepository extends JpaRepository<PlaylistEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM PLAYLIST WHERE NAME =?1")
    int countByName(String listName);

    @Query(nativeQuery = true, value = "SELECT DESCRIPTION FROM PLAYLIST WHERE NAME =?1")
    Optional<String> findDescriptionByName(String listName);

    Optional<PlaylistEntity> findByName(String listName);
}
