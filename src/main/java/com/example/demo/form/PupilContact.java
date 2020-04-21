package com.example.demo.form;

public class PupilContact {
    private Pupil pupil;
    private Contact contact;

    public PupilContact(Pupil pupil, Contact contact) {
        this.pupil = pupil;
        this.contact = contact;
    }

    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
