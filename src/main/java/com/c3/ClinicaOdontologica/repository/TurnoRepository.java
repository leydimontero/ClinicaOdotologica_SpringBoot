package com.c3.ClinicaOdontologica.repository;

import com.c3.ClinicaOdontologica.entity.Turno;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno , Long> {
}
