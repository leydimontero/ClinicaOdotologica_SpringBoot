package com.c3.ClinicaOdontologica.Service;

import com.c3.ClinicaOdontologica.entity.Odontologo;
import com.c3.ClinicaOdontologica.service.OdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologoAGuardar = new Odontologo("12345","Santiago","Garzon");
        odontologoService.guardarOdontologo(odontologoAGuardar);
        assertEquals(1L, odontologoAGuardar.getId());
    }
    @Test
    @Order(2)
    public void buscarPorIdOdontologoTest(){
        Long idABuscar = 1L;
        Optional<Odontologo> odontologoABuscar = odontologoService.buscarPorIdOdontologo(idABuscar);
        assertNotNull(odontologoABuscar);
    }
    @Test
    @Order(3)
    public void buscarTodos(){
        List<Odontologo> listaDeOdontologos= odontologoService.listarTodosLosOdontologos();
        assertEquals(1,listaDeOdontologos.size());
    }
    @Test
    @Order(4)
    public void actualizarOdontologo(){
        Long idABuscar = 1L;
        Optional<Odontologo> odontologo = odontologoService.buscarPorIdOdontologo(idABuscar);
        if (odontologo.isPresent()) {
            Odontologo odontologoAGuardar = new Odontologo(idABuscar,"12345","Santiago","Montero");
            odontologoService.actualizarOdontologo(odontologoAGuardar);
            Optional<Odontologo> odontologoActualizado = odontologoService.buscarPorIdOdontologo(1L);
            assertEquals("Montero", odontologoActualizado.get().getApellido());

        }

    }
    @Test
    @Order(5)
    public void eliminarOdontologo(){
        Long idEliminar= 1L;
        odontologoService.eliminarOdontologo(idEliminar);
        Optional<Odontologo> odontologoEliminado= odontologoService.buscarPorIdOdontologo(idEliminar);
        assertFalse(odontologoEliminado.isPresent());
    }
}








