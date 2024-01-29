package com.c3.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column

    private String nombre;
    @Column
    private String apellido;
    @Column(nullable = false)
    private String cedula;
    @Column
    private LocalDate fechaIngreso;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")

    private Domicilio domicilio;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Paciente() {
    }

    public Paciente(Long id,  String nombre,  String apellido,  String cedula,  LocalDate fechaIngreso,  Domicilio domicilio,  String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;

    }

    public Paciente( String nombre,  String apellido,  String cedula,  LocalDate fechaIngreso,  Domicilio domicilio,  String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;

    }


}
