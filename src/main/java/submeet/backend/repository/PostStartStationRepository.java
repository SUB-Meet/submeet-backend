package submeet.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.PostStartStation;
import submeet.backend.entity.Station;

import java.util.List;

public interface PostStartStationRepository extends JpaRepository<PostStartStation,Long> {
    public Page<PostStartStation> findByStation(Station station, Pageable pageable);
}
