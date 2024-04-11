package submeet.backend.repository;

import feign.Param;
import org.locationtech.jts.geom.Polygon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import submeet.backend.entity.Station;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station,Long> {
    public Optional<Station> findByNameAndLine(String name,String line);
    public Optional<Station> findByStationCode(String station_code);

    /**
     * 휴대폰 화면 안에 네 점 parameter로 받음
     * 네 점 안에 포함되는 역 조회
     */
    @Query("select s from Station s where true = ST_CONTAINS(:appPolygon,s.location)")
    public List<Station> findStationWithLocation(@Param("appPolygon")Polygon appPolygon);
}
