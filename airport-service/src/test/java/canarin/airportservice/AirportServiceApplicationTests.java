package canarin.airportservice;

import static org.mockito.Mockito.*;

import canarin.airportservice.model.Airport;
import canarin.airportservice.payload.AirportRequest;
import canarin.airportservice.repository.AirportRepository;
import canarin.airportservice.service.AirportService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceApplicationTests {

	@Mock
	AirportRepository airportRepository;

	@InjectMocks
	AirportService airportService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findAllTest() {
		airportService.findAll();
		verify(airportRepository).findAll();
	}

	@Test
	public void saveTest() {
		Airport airport = mock(Airport.class);
		verify(airportRepository).save(airport);
	}

}
