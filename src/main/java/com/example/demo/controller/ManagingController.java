package com.example.demo.controller;

import com.example.demo.form.Pupil;
import com.example.demo.form.Response;
import com.example.demo.form.SchoolClass;
import com.example.demo.service.ManagingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class ManagingController {

    private final ManagingService managingService;

    public ManagingController(ManagingService managingService) {
        this.managingService = managingService;
    }

    @PostMapping
    public Response insert(@RequestBody Pupil pupil) {
        return managingService.insert(pupil);
    }

    @PutMapping("/{code}")
    public Response update(@PathVariable String code, @RequestBody Pupil pupil) {
        return managingService.update(code, pupil);
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
