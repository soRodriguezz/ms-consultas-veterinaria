package pch.huellaschile.msconsultasmedicas.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pch.huellaschile.msconsultasmedicas.domain.entities.Consulta;
import pch.huellaschile.msconsultasmedicas.domain.entities.Dueno;
import pch.huellaschile.msconsultasmedicas.domain.entities.Mascota;
import pch.huellaschile.msconsultasmedicas.domain.entities.Veterinaria;
import pch.huellaschile.msconsultasmedicas.domain.services.ConsultaService;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestConsultaDTO;
import pch.huellaschile.msconsultasmedicas.web.controllers.ConsultaController;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsultaController.class)
public class ConsultaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ConsultaService service;
    Consulta consulta = new Consulta();
    List<Consulta> consultaList = new ArrayList<>();
    Mascota mascota = new Mascota();
    Dueno dueno = new Dueno();
    Veterinaria veterinaria = new Veterinaria();

    RequestConsultaDTO dto = new RequestConsultaDTO();
    @BeforeEach
    void setUp() {

        mascota.setNombre("nombreMascota");
        dueno.setNombre("nombreDueno");
        veterinaria.setNombre("nombreVet");

        consulta.setIdMascota(1);
        consulta.setMascota(mascota);
        consulta.setIdDueno(1);
        consulta.setDueno(dueno);
        consulta.setIdVeterinaria(1);
        consulta.setVeterinaria(veterinaria);
        consulta.setValor(123);
        consulta.setFormaPago("formapago");
        consulta.setDescripcionProceso("descripcion");
        consulta.setEstadoConsulta("estado");
        consulta.setEstaEnTratamiento(true);

        consultaList.add(consulta);

        dto.setIdMascota(1);
        dto.setIdDueno(1);
        dto.setIdVeterinaria(1);
        dto.setValor(123);
        dto.setFormaPago("formapago");
        dto.setDescripcionProceso("Descripcion");
        dto.setEstadoConsulta("estado");
        dto.setEstaEnTratamiento(true);
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void getAll() throws Exception {
        when(service.getAll()).thenReturn(consultaList);
        this.mockMvc.perform(get("/consulta/all")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(dto);

        when(service.save(dto)).thenReturn(consulta);

        this.mockMvc.perform(post("/consulta/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(dto);

        when(service.update(1, dto)).thenReturn(consulta);
        this.mockMvc.perform(put("/consulta/update/" + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void estadoCerrada() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(dto);

        when(service.estadoCerrada(1)).thenReturn(consulta);
        this.mockMvc.perform(patch("/consulta/estado-cerrada/" + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void tratamientoTerminado() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(dto);

        when(service.tratamientoTerminado(1)).thenReturn(consulta);
        this.mockMvc.perform(patch("/consulta/tratamiento-terminado/" + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void tratamientoPagado() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(dto);

        when(service.tratamientoPagado(1)).thenReturn(consulta);
        this.mockMvc.perform(patch("/consulta/tratamiento-pagado/" + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void enTratamiento() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(dto);

        when(service.enTratamiento(1)).thenReturn(consulta);
        this.mockMvc.perform(patch("/consulta/en-tratamiento/" + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteConsulta() throws Exception {
        service.deleteConsulta(1);
        this.mockMvc.perform(delete("/consulta/delete/" + "1")).andDo(print()).andExpect(status().isOk());
    }

}
