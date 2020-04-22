package com.example.demo.controller;

import com.example.demo.form.Contact;
import com.example.demo.form.Pupil;
import com.example.demo.form.PupilContact;
import com.example.demo.form.Response;
import com.example.demo.service.ManagingService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
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
