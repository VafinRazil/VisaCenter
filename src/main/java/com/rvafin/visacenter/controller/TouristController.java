package com.rvafin.visacenter.controller;

import com.rvafin.visacenter.dto.request.CreateTouristRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/tourist")
@CrossOrigin
public class TouristController {

    @PostMapping("/new")
    public ResponseEntity<?> createNewTourist(CreateTouristRequestDTO createTouristRequestDTO){
        return null;
    }
}
