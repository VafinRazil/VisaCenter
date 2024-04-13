package com.rvafin.visacenter.controller;

import com.rvafin.visacenter.dto.request.CreateVisaApplicationRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/visa")
public class VisaCenterController {

    @PostMapping("/create/application")
    public ResponseEntity<?> createVisaApplicationForm(CreateVisaApplicationRequestDTO createVisaApplicationRequestDTO){
        return ResponseEntity.ok(null);
    }



}
