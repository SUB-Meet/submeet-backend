package submeet.backend.repository;

import feign.Param;
import org.locationtech.jts.geom.Polygon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import submeet.backend.entity.Post;
import submeet.backend.entity.PostStartStation;
import submeet.backend.entity.Station;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    /**
     * 휴대폰 화면 안에 네 점 Polygon으로 받음
     * 네 점 안에 포함되는 역이 출발역인 게시글 조회 쿼리
     */
    @Query("select p from Post p where true = ST_CONTAINS(:appPolygon,p.postStartStation.station.location)")
    public Page<Post> findPostByLocation(@Param("appPolygon") Polygon appPolygon, Pageable pageable);

    public List<Post> findByPostStartStation(PostStartStation postStartStation);
}
