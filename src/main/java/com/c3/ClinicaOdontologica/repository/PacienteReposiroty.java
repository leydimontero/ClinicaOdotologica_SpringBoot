package com.c3.ClinicaOdontologica.repository;

import com.c3.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface    PacienteReposiroty extends JpaRepository<Paciente, Long>{

    Optional<Paciente> findByEmail(String email);
}
