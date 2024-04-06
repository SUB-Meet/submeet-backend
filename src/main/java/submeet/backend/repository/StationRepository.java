package submeet.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.Station;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station,Long> {
    public Optional<Station> findByNameAndLine(String name,String line);
}
