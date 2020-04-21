package com.example.demo.form;

public class Pupil {

    private String code;

    private String name;

    private String field;

    private int fatherContact;

    public Pupil(
            String code,
            String name,
            String field) {

        this.code = code;
        this.name = name;
        this.field = field;
    }

    public Pupil(
            String code,
            String name,
            String field,
            int fatherContact) {

        this.code = code;
        this.name = name;
        this.field = field;
        this.fatherContact = fatherContact;
    }

    public Pupil() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getFatherContact() {
        return fatherContact;
    }

    public void setFatherContact(int fatherContact) {
        this.fatherContact = fatherContact;
    }
}
