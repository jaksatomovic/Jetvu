package canarin.airportservice.repository;

        import canarin.airportservice.model.AirportDTO;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<AirportDTO, Long> {
}
