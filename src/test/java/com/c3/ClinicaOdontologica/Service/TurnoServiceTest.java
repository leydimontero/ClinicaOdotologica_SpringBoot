package com.c3.ClinicaOdontologica.Service;

import com.c3.ClinicaOdontologica.dto.TurnoDTO;
import com.c3.ClinicaOdontologica.entity.Domicilio;
import com.c3.ClinicaOdontologica.entity.Odontologo;
import com.c3.ClinicaOdontologica.entity.Paciente;
import com.c3.ClinicaOdontologica.entity.Turno;
import com.c3.ClinicaOdontologica.service.OdontologoService;
import com.c3.ClinicaOdontologica.service.PacienteService;
import com.c3.ClinicaOdontologica.service.TurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarTurnoTest() {
        //Paciente paciente = new Paciente("Julian","Ospina","12345",LocalDate.of(2023,4,16),new Domicilio("Calle 1",1,"Localidad 1","Provincia 1"),"julian@gmail.com");
        //pacienteService.guardarPaciente(paciente);
        //Odontologo odontologo = new Odontologo("Odontologo","Santiago","Garzon");
        //odontologoService.guardarOdontologo(odontologo);
        //Turno turnoAGuardar = new Turno(paciente,odontologo,LocalDate.of(2023,10,12));
        Turno turnoAGuardar= new Turno(new Paciente("Julian","Ospina","12345",LocalDate.of(2023,4,16),new Domicilio("Calle 1",1,"Localidad 1","Provincia 1"),"julian@gmail.com"),new Odontologo("Odontologo","Santiago","Garzon"),LocalDate.of(2023, 8,10));
        turnoService.guardarTurno(turnoAGuardar);
        long idGenerado = turnoAGuardar.getId();
        assertEquals(1L, idGenerado);
    }
    @Test
    @Order(2)
    public void buscarPorIdTurnoTest(){
        Long idABuscar=1L;
        Optional<TurnoDTO> turnoABuscado = turnoService.bucarTurnoPorId(idABuscar);
        assertNotNull(turnoABuscado);
    }
    @Test
    @Order(3)
    public void buscarTodosLosTurnosTest(){
        List<TurnoDTO> listaDeTurnos= turnoService.listarTurnos();
        assertEquals(1,listaDeTurnos.size());
    }
}
