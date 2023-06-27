package com.example.attenfancestudent.Model;

public class Subject {
    private int subject_id;
    private String subject_name;
    private String subject_class;

    public Subject() {
    }

    public Subject(int subject_id, String subject_name, String subject_class) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.subject_class = subject_class;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_class() {
        return subject_class;
    }

    public void setSubject_class(String subject_class) {
        this.subject_class = subject_class;
    }
}
