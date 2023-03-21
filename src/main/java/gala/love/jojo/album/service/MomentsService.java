package gala.love.jojo.album.service;

import gala.love.jojo.album.entity.MomentsEntity;
import gala.love.jojo.album.repository.MomentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MomentsService {

    @Autowired
    private MomentsRepository momentsRepository;

    public MomentsEntity createMoment(MomentsEntity momentsEntity) {
        return momentsRepository.save(momentsEntity);
    }

    public void deleteMoment(Long id) {
        momentsRepository.deleteById(id);
    }

    public List<MomentsEntity> getMoments() {
        return momentsRepository.findAll();
    }

    // 在此处添加按日期或关键字筛选的方法
}
