package com.rvafin.visacenter.controller;

import com.rvafin.visacenter.dto.request.TouristRequestDTO;
import com.rvafin.visacenter.service.interfaces.TouristService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tourist")
@CrossOrigin
public class TouristController {

    private final static Logger log = LoggerFactory.getLogger(TouristController.class);

    private final TouristService touristService;

    @Autowired
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewTourist(
            @RequestBody TouristRequestDTO touristRequestDTO){
        try {
            log.info(touristRequestDTO.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(touristService.createNewTourist(touristRequestDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editTourist(
            @RequestBody TouristRequestDTO touristRequestDTO,
            @PathVariable Long id){
        try {
            return ResponseEntity.ok(touristService.editTouristInfo(touristRequestDTO, id));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTourist(@PathVariable Long id){
        try {
            return ResponseEntity.ok(touristService.getTouristInfoById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
