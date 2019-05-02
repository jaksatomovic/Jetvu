package canarin.lowfare.repository;

import canarin.lowfare.model.ParamQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamQueryRepository extends JpaRepository<ParamQuery, Long> {
}
