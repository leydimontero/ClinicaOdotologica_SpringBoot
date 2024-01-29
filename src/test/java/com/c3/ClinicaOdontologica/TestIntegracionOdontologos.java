package com.c3.ClinicaOdontologica;


import com.c3.ClinicaOdontologica.entity.Odontologo;
import com.c3.ClinicaOdontologica.service.OdontologoService;
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
public class TestIntegracionOdontologos {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listarTodosLosOdontolgosTest() throws Exception{
        Odontologo odontologo = odontologoService.guardarOdontologo(new Odontologo("12345","Doris","Montero"));
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/odontologos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }
}
