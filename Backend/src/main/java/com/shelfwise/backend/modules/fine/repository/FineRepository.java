package com.shelfwise.backend.modules.fine.repository;

import com.shelfwise.backend.modules.fine.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface FineRepository extends JpaRepository<Fine, Long> {
    int countByUserUserIdAndIsPaid(Long userId,boolean isPaid);
}
