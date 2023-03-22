package gala.love.jojo.album.repository;

import gala.love.jojo.album.entity.MomentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MomentsRepository extends JpaRepository<MomentsEntity, Long> {

    List<MomentsEntity> findByCreateTimeBetween(LocalDateTime start, LocalDateTime end);
}
