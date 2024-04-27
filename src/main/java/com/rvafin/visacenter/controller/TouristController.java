package com.rvafin.visacenter.controller;

import com.rvafin.visacenter.dto.request.TouristRequestDTO;
import com.rvafin.visacenter.service.interfaces.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/tourist")
@CrossOrigin
public class TouristController {

    private final TouristService touristService;

    @Autowired
    public TouristController(
            TouristService touristService
    ) {
        this.touristService = touristService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createNewTourist(TouristRequestDTO touristRequestDTO){
        try {
            return ResponseEntity.ok(touristService.createNewTourist(touristRequestDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editTourist(TouristRequestDTO touristRequestDTO, @PathVariable Long id){
        try {
            return ResponseEntity.ok(touristService.editTouristInfo(touristRequestDTO, id));
        }catch (Exception e){
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
