package com.c3.ClinicaOdontologica.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    private Odontologo odontologo;
    @Column(nullable = false)
    private LocalDate fecha;

    public Turno() {
    }

    public Turno(Long id, Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Turno(Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }
}
