package com.example.demo.controller.rest;

import com.example.demo.form.PupilContact;
import com.example.demo.form.Response;
import com.example.demo.service.ManagingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pupils")
public class ManagingController {

    private final ManagingService managingService;

    public ManagingController(ManagingService managingService) {
        this.managingService = managingService;
    }

    @PostMapping
    public Response insert(@RequestBody PupilContact pupilContact) {
        return managingService.insert(pupilContact);
    }

    @PutMapping("/{code}")
    public Response update(@PathVariable String code, @RequestBody PupilContact pupilContact) {
        return managingService.update(code, pupilContact);
    }

    @DeleteMapping("/{code}")
    public Response delete(@PathVariable String code) {
        return managingService.delete(code);
    }

    @GetMapping
    public Response fetchClass() {
        return managingService.fetchClass();
    }

    @GetMapping("/{code}")
    public Response fetchFindById(@PathVariable String code) {
        return managingService.findById(code);
    }
}
