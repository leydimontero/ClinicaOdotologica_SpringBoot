package com.c3.ClinicaOdontologica;

import com.c3.ClinicaOdontologica.dto.TurnoDTO;
import com.c3.ClinicaOdontologica.entity.Domicilio;
import com.c3.ClinicaOdontologica.entity.Odontologo;
import com.c3.ClinicaOdontologica.entity.Paciente;
import com.c3.ClinicaOdontologica.entity.Turno;
import com.c3.ClinicaOdontologica.service.OdontologoService;
import com.c3.ClinicaOdontologica.service.PacienteService;
import com.c3.ClinicaOdontologica.service.TurnoService;
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
public class TestIntegracionTurnos {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
     private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;
    public void cargarDatos(){
        Paciente paciente= pacienteService.guardarPaciente(new Paciente("Martin","Cerbin","11111", LocalDate.of(2023,9,25),new Domicilio("Siempre viva",742,"Springfield","Entre Rios"),"martincerbin@dj.com"));
        Odontologo odontologo= odontologoService.guardarOdontologo(new Odontologo("MP10","Romina","Mazzuco"));
        Turno turno= new Turno(paciente,odontologo,LocalDate.of(2023,10,23));
        TurnoDTO turnoDTO= turnoService.guardarTurno(turno);

    }
    @Test
    public void listarTodosTurnosTest() throws Exception {
        cargarDatos();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }

}
