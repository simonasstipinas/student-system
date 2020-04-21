package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {

    private List<Pupil> schoolClass = new ArrayList<>();

    public SchoolClass() {
    }

    public List<Pupil> getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(List<Pupil> schoolClass) {
        this.schoolClass = schoolClass;
    }
}

