package gala.love.jojo.album.service;

import gala.love.jojo.album.entity.MomentsEntity;
import gala.love.jojo.album.repository.MomentsRepository;
import gala.love.jojo.album.util.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MomentsService {

    @Autowired
    private MomentsRepository momentsRepository;
    @Autowired
    private FileService fileService;

    public MomentsEntity createMoment(MomentsEntity momentsEntity, List<MultipartFile> images) {
        // Step 1: Save the entity to the database
        MomentsEntity savedEntity = momentsRepository.save(momentsEntity);
        // Step 2: Get the generated ID and create a folder with that ID
        Long id = savedEntity.getId();

        // 上传图片并将路径添加到imageUrlList中
        List<String> imageUrlList = new ArrayList<>();
        int i = 1;
        for (MultipartFile image : images) {
            String imageUrl = null;
            try {
                imageUrl = fileService.saveImage(image, i,id);
                i++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            imageUrlList.add(imageUrl);
        }
        // 将所有图片的 URL 通过逗号连接成一个字符串，然后赋值给 momentsEntity.imageUrl
        savedEntity.setImageUrl(String.join(",", imageUrlList));

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        savedEntity.setCreateTime(now);
        savedEntity.setUpdateTime(now);

        // 将 momentsEntity 保存到数据库
        return momentsRepository.save(savedEntity);
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
