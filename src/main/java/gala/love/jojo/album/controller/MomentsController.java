package gala.love.jojo.album.controller;

import gala.love.jojo.album.entity.MomentsEntity;
import gala.love.jojo.album.service.MomentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/moments")
public class MomentsController {

    @Autowired
    private MomentsService momentsService;

    @PostMapping("/createMoment")
    public ResponseEntity<MomentsEntity> createMoment(@RequestParam String content,
                                                      @RequestParam(value = "images", required = false) List<MultipartFile> images) {
        MomentsEntity momentsEntity = new MomentsEntity();
        momentsEntity.setContent(content);
        MomentsEntity created = momentsService.createMoment(momentsEntity, images);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteMoment/{id}")
    public ResponseEntity<Void> deleteMoment(@PathVariable Long id) {
        momentsService.deleteMoment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getMoments")
    public ResponseEntity<List<MomentsEntity>> getMoments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MomentsEntity> momentsPage = momentsService.getMoments(PageRequest.of(page, size));
        List<MomentsEntity> momentsEntities = momentsPage.getContent();
        return new ResponseEntity<>(momentsEntities, HttpStatus.OK);
    }

    @GetMapping("/getMomentsByDateRange")
    public ResponseEntity<List<MomentsEntity>> getMomentsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        Page<MomentsEntity> momentsPage = momentsService.getMomentsByDateRange(start, end, PageRequest.of(page, size));
        List<MomentsEntity> momentsEntities = momentsPage.getContent();
        return new ResponseEntity<>(momentsEntities, HttpStatus.OK);
    }
}

