package gala.love.jojo.album.controller;

import gala.love.jojo.album.entity.MomentsEntity;
import gala.love.jojo.album.service.MomentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/getMomentsByDateRange")
    public ResponseEntity<List<MomentsEntity>> getMomentsByDateRange(@RequestParam(name = "startDate") String startDateStr, @RequestParam(name = "endDate") String endDateStr) {
        // 调用 MomentsService 类中的方法，按照日期范围筛选获取 moments
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        List<MomentsEntity> momentsEntities = momentsService.getMomentsByDateRange(startDate, endDate);
        return new ResponseEntity<>(momentsEntities, HttpStatus.OK);
    }
}

