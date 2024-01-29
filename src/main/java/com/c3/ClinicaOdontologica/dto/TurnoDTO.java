package com.c3.ClinicaOdontologica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDTO {
    private Long id;
    private LocalDate fecha;
    private Long pacienteId;
    private Long odontologoId;
}
