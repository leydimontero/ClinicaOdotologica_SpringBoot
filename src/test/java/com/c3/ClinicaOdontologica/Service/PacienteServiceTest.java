package com.c3.ClinicaOdontologica.Service;

import com.c3.ClinicaOdontologica.entity.Domicilio;
import com.c3.ClinicaOdontologica.entity.Paciente;
import com.c3.ClinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente() {

        Paciente pacienteAGuardar= new Paciente("Julian","Ospina","12345", LocalDate.of(2023,4,16),new Domicilio("calle 1",11,"Loclidad 1","Provincia 1"),"julianOspina@gmail.com");
        pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1L, pacienteAGuardar.getId());
    }

    @Test
    @Order(2)
    public void buscarPorIdPacienteTest(){
        Long idABuscar=1L;
        Optional<Paciente> pacienteABuscado = pacienteService.buscarPorIdPacientes(idABuscar);
        assertNotNull(pacienteABuscado);
    }
    @Test
    @Order(3)
    public void buscarTodos(){
        List<Paciente> listaDePacientes= pacienteService.listarTodosLosPacientes();
        assertEquals(1,listaDePacientes.size());
    }
    @Test
    @Order(4)
    public void actualizarPacientes(){
        Long idABuscar = 1L;
        Optional<Paciente> paciente = pacienteService.buscarPorIdPacientes(idABuscar);
        if (paciente.isPresent()) {
            Paciente pacienteAGuardar = new Paciente(idABuscar,"Jeyson","Ospina","12345",LocalDate.of(2023,04,16),new Domicilio("Calle 1",11,"Localidad 1","Provincia 1"),"julianOspina@gmail.com");
            pacienteService.actualizarPaciente(pacienteAGuardar);
            Optional<Paciente> pacienteActualizado = pacienteService.buscarPorIdPacientes(1L);
            assertEquals("Jeyson", pacienteActualizado.get().getNombre());

        }

    }
    @Test
    @Order(5)
    public void eliminarPaciente(){
        Long idEliminar= 1L;
        pacienteService.eliminarPaciente(idEliminar);
        Optional<Paciente> pacienteEliminado= pacienteService.buscarPorIdPacientes(idEliminar);
        assertFalse(pacienteEliminado.isPresent());
    }
}

