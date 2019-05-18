package canarin.airportservice;

import canarin.airportservice.controller.AirportController;
import canarin.airportservice.model.AirportDTO;
import canarin.airportservice.service.AirportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(value = AirportController.class, secure = false)
public class AirportApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Test
    public void testFindAll() throws Exception {
        AirportDTO airport = new AirportDTO();
        airport.setId(1L);
        airport.setName("London");
        airport.setIATA("LON");

        List<AirportDTO> airports = Arrays.asList(airport);
        given(airportService.findAll()).willReturn(airports);

        this.mockMvc.perform(get("/airports"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1,'name': 'London';'iata': 'LON'}]"));
    }

    @WithMockUser(username="admin", password = "admin")
    @Test
    public void testSave() throws Exception {
        AirportDTO airport = new AirportDTO();
        airport.setId(1L);
        airport.setName("London");
        airport.setIATA("LON");

        this.mockMvc.perform(post("/airports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(airport)))
                .andExpect(status().is2xxSuccessful());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
