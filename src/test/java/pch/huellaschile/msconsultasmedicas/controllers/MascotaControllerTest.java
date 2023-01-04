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
import pch.huellaschile.msconsultasmedicas.domain.entities.Mascota;
import pch.huellaschile.msconsultasmedicas.domain.services.MascotaService;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestMascotaDTO;
import pch.huellaschile.msconsultasmedicas.web.controllers.MascotaController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MascotaController.class)
public class MascotaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MascotaService service;
    Mascota mascota = new Mascota();
    List<Mascota> mascotaList = new ArrayList<>();
    RequestMascotaDTO dto = new RequestMascotaDTO();
    @BeforeEach
    void setUp() {
        mascota.setNombre("nombre");

        mascotaList.add(mascota);

        dto.setNombre("nombreDTO");
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void getAll() throws Exception {
        when(service.getAll()).thenReturn(mascotaList);
        this.mockMvc.perform(get("/mascota/all")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(service.findById(1)).thenReturn(Optional.ofNullable(mascota));
        this.mockMvc.perform(get("/mascota/" + "1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(dto);

        when(service.save(mascota)).thenReturn(mascota);

        this.mockMvc.perform(post("/mascota/save")
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

        when(service.update(1, dto)).thenReturn(mascota);
        this.mockMvc.perform(put("/mascota/update/" + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteMascota() throws Exception {
        service.deleteMascota(1);
        this.mockMvc.perform(delete("/mascota/delete/" + "1")).andDo(print()).andExpect(status().isOk());
    }
}
