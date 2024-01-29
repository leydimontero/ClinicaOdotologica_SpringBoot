package com.c3.ClinicaOdontologica.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column

    private String calle;
    @Column
    private Integer numero;
    @Column
    private String localidad;
    @Column
    private String provincia;

    public Domicilio() {
    }

    public Domicilio(String calle, Integer numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Domicilio(Integer id, String calle, Integer numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
