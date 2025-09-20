package com.shelfwise.backend.modules.reservation.repository;

import com.shelfwise.backend.modules.reservation.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
