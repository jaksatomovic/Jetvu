package canarin.airportservice;

import canarin.airportservice.model.AirportDTO;
import canarin.airportservice.repository.AirportRepository;
import canarin.airportservice.service.AirportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest() {

        List<AirportDTO> airporList = new ArrayList<AirportDTO>();
        airporList.add(new AirportDTO("London","LON"));
        airporList.add(new AirportDTO("Milan","MIL"));

        when(airportRepository.findAll()).thenReturn(airporList);

        List<AirportDTO> result = airportService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void saveAirport() {
        AirportDTO airport = new AirportDTO("London","LON");
        airportRepository.save(airport);
        verify(airportRepository, times(1)).save(airport);
    }
}
