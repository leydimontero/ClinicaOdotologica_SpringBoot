package com.c3.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table (name = "odontologos")
public class Odontologo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String matricula;
    @Column
    private String nombre;
    @Column
    private String apellido;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Odontologo() {
    }

    public Odontologo(Long id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;

    }

    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
