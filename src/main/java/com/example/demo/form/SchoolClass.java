package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {

    List<Pupil> schoolClass = new ArrayList<>();

    public SchoolClass() {
        schoolClass.add(new Pupil("CLASSCODE1", "SIMONAS", 1l));
        schoolClass.add(new Pupil("CLASSCODE2", "KRISTUPAS"));
    }

    public List<Pupil> getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(List<Pupil> schoolClass) {
        this.schoolClass = schoolClass;
    }
}

