package com.shelfwise.backend.modules.LibraryPolicy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


public interface LibraryPolicyReposiitory extends JpaRepository<LibraryPolicy,Long> {
    @Query("""
        SELECT p FROM LibraryPolicy p
        WHERE p.effectiveFrom <= :now
          AND (p.effectiveTo IS NULL OR p.effectiveTo > :now)
        ORDER BY p.effectiveFrom DESC
    """)
    List<LibraryPolicy> findActive(@Param("now") LocalDateTime now);

    default LibraryPolicy getActiveOrThrow() {
        return findActive(LocalDateTime.now())
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No active library policy"));
    }
}
