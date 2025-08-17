package com.shelfwise.backend.modules.publisher;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByPublisherName(String publishername);
    boolean existsByPublisherName(String publishername);
}
