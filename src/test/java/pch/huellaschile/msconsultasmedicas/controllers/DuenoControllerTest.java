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
import pch.huellaschile.msconsultasmedicas.domain.entities.Dueno;
import pch.huellaschile.msconsultasmedicas.domain.services.DuenoService;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestDuenoDTO;
import pch.huellaschile.msconsultasmedicas.web.controllers.DuenoController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DuenoController.class)
public class DuenoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DuenoService service;
    Dueno dueno = new Dueno();
    List<Dueno> duenoList = new ArrayList<>();
    RequestDuenoDTO dto = new RequestDuenoDTO();
    @BeforeEach
    void setUp() {
       dueno.setNombre("nombre");

       duenoList.add(dueno);

       dto.setNombre("nombreDTO");
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void getAll() throws Exception {
        when(service.getAll()).thenReturn(duenoList);
        this.mockMvc.perform(get("/dueno/all")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(dto);

        when(service.save(dueno)).thenReturn(dueno);

        this.mockMvc.perform(post("/dueno/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(service.findById(1)).thenReturn(Optional.ofNullable(dueno));
        this.mockMvc.perform(get("/dueno/" + "1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(dto);

        when(service.update(1, dto)).thenReturn(dueno);
        this.mockMvc.perform(put("/dueno/update/" + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteDueno() throws Exception {
        service.deleteDueno(1);
        this.mockMvc.perform(delete("/dueno/delete/" + "1")).andDo(print()).andExpect(status().isOk());
    }
}
