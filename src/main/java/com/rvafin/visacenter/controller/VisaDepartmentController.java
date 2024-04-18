package com.rvafin.visacenter.controller;

import com.rvafin.visacenter.dto.request.VisaApplicationRequestDTO;
import com.rvafin.visacenter.enums.VisaStatus;
import com.rvafin.visacenter.service.interfaces.VisaDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller("/visa/department/")
@CrossOrigin
public class VisaDepartmentController {

    private static Logger log = LoggerFactory.getLogger(VisaDepartmentController.class);

    private final VisaDepartmentService visaDepartmentService;

    @Autowired
    public VisaDepartmentController(
            VisaDepartmentService visaDepartmentService
    ) {
        this.visaDepartmentService = visaDepartmentService;
    }

    @PostMapping("/new/application")
    public ResponseEntity<?> createVisaApplicationForm(VisaApplicationRequestDTO visaApplicationRequestDTO){
        try {
            return ResponseEntity.ok(visaDepartmentService.createVisaApplication(visaApplicationRequestDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all/client/applications")
    public ResponseEntity<?> getVisaApplicationsForClient(){
        try {
            return ResponseEntity.ok(visaDepartmentService.getVisaApplicationsForClient());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }


    @GetMapping("/applications")
    public ResponseEntity<?> getVisaApplicationsForEmployee(
            @RequestParam Long countryId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam VisaStatus visaStatus
    ){
        try {
            return ResponseEntity.ok(visaDepartmentService.getVisaApplications(countryId, startDate, endDate, visaStatus));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @DeleteMapping("/application/{id}")
    public ResponseEntity<?> deleteVisaApplicationById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(visaDepartmentService.deleteVisaApplication(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<?> getVisaApplicationById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(visaDepartmentService.getVisaApplicationById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @PutMapping("/application/{id}")
    public ResponseEntity<?> changeVisaApplicationInfo(@PathVariable Long id, VisaApplicationRequestDTO visaApplicationRequestDTO){
        try {
            return ResponseEntity.ok(visaDepartmentService.changeVisaApplication(visaApplicationRequestDTO, id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
        }
    }

    @PostMapping("/send/applications/visaCenter")
    public ResponseEntity<?> sendVisaApplicationsToVisaCenter(@RequestParam("id[]") List<Long> ids){
        try {
            visaDepartmentService.sendVisaApplicationsToVisaCenter(ids);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/visa/{id}")
    public ResponseEntity<?> getVisaById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(visaDepartmentService.getVisaById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/visas")
    public ResponseEntity<?> getVisasForClient(){
        try {
            return ResponseEntity.ok(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
}