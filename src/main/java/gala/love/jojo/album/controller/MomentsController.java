package gala.love.jojo.album.controller;

import gala.love.jojo.album.entity.MomentsEntity;
import gala.love.jojo.album.service.MomentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moments")
public class MomentsController {

    @Autowired
    private MomentsService momentsService;

    @PostMapping("/createMoment")
    public ResponseEntity<MomentsEntity> createMoment(@RequestBody MomentsEntity momentsEntity) {
        MomentsEntity created = momentsService.createMoment(momentsEntity);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteMoment/{id}")
    public ResponseEntity<Void> deleteMoment(@PathVariable Long id) {
        momentsService.deleteMoment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getMoments")
    public ResponseEntity<List<MomentsEntity>> getMoments() {
        List<MomentsEntity> momentsEntities = momentsService.getMoments();
        return new ResponseEntity<>(momentsEntities, HttpStatus.OK);
    }

    // 在此处添加按日期或关键字筛选的方法的映射
}

