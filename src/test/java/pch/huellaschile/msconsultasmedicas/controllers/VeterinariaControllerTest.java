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
import pch.huellaschile.msconsultasmedicas.domain.entities.Veterinaria;
import pch.huellaschile.msconsultasmedicas.domain.services.VeterinariaService;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestVeterinariaDTO;
import pch.huellaschile.msconsultasmedicas.web.controllers.VeterinariaController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VeterinariaController.class)
public class VeterinariaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VeterinariaService service;
    Veterinaria veterinaria = new Veterinaria();
    List<Veterinaria> veterinariaList = new ArrayList<>();
    RequestVeterinariaDTO dto = new RequestVeterinariaDTO();
    @BeforeEach
    void setUp() {
        veterinaria.setNombre("nombre");

        veterinariaList.add(veterinaria);

        dto.setNombre("nombreDTO");
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void getAll() throws Exception {
        when(service.getAll()).thenReturn(veterinariaList);
        this.mockMvc.perform(get("/veterinaria/all")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(dto);

        when(service.save(veterinaria)).thenReturn(veterinaria);

        this.mockMvc.perform(post("/veterinaria/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(service.findById(1)).thenReturn(Optional.ofNullable(veterinaria));
        this.mockMvc.perform(get("/veterinaria/" + "1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(dto);

        when(service.update(1, dto)).thenReturn(veterinaria);
        this.mockMvc.perform(put("/veterinaria/update/" + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteveterinaria() throws Exception {
        service.deleteVeterinaria(1);
        this.mockMvc.perform(delete("/veterinaria/delete/" + "1")).andDo(print()).andExpect(status().isOk());
    }
}
