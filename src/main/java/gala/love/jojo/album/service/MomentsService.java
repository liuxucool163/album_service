package gala.love.jojo.album.service;

import gala.love.jojo.album.entity.MomentsEntity;
import gala.love.jojo.album.repository.MomentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MomentsService {

    @Autowired
    private MomentsRepository momentsRepository;

    public MomentsEntity createMoment(MomentsEntity momentsEntity) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        momentsEntity.setCreateTime(now);
        momentsEntity.setUpdateTime(now);
        return momentsRepository.save(momentsEntity);
    }

    public void deleteMoment(Long id) {
        momentsRepository.deleteById(id);
    }

    public List<MomentsEntity> getMoments() {
        return momentsRepository.findAll();
    }

    public List<MomentsEntity> getMomentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return momentsRepository.findByCreateTimeBetween(startDate.atStartOfDay(), endDate.atStartOfDay().plusDays(1).minusNanos(1));
    }
}
