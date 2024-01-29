package com.c3.ClinicaOdontologica;

import com.c3.ClinicaOdontologica.entity.Domicilio;
import com.c3.ClinicaOdontologica.entity.Paciente;
import com.c3.ClinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)//que voy a desactivar la seguridad.
public class TestIbtegracionPacientes {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MockMvc mockMvc;

   @Test
    public void listarTodosLosPacientesTest() throws Exception{
        Paciente paciente = pacienteService.guardarPaciente(new Paciente("Julian","Ospina","12345", LocalDate.of(2023,10,12),new Domicilio("Calle 1",1,"Localidad 1","Provincia 1"),"julian@gmail.com"));
       MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/pacientes").accept(MediaType.APPLICATION_JSON))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
       assertFalse(resultado.getResponse().getContentAsString().isEmpty());
   }

}
