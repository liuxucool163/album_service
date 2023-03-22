package gala.love.jojo.album.service;

import gala.love.jojo.album.entity.MomentsEntity;
import gala.love.jojo.album.repository.MomentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<MomentsEntity> getMoments(Pageable pageable) {
        return momentsRepository.findAll(pageable);
    }

    public Page<MomentsEntity> getMomentsByDateRange(LocalDate start, LocalDate end, Pageable pageable) {
        LocalDateTime startDate = start.atStartOfDay();
        LocalDateTime endDate = end.plusDays(1).atStartOfDay().minusSeconds(1);
        return momentsRepository.findByCreateTimeBetween(startDate, endDate, pageable);
    }
}
