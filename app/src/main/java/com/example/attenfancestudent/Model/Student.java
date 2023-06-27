package com.example.attenfancestudent.Model;

public class Student {
    private int st_id;
    private String st_name;
    private String st_class;
    private int lesson_id;
    private String status;

    public Student() {
    }

    public Student(int st_id, String st_name, String st_class, int lesson_id, String status) {
        this.st_id = st_id;
        this.st_name = st_name;
        this.st_class = st_class;
        this.lesson_id = lesson_id;
        this.status = status;
    }

    public int getSt_id() {
        return st_id;
    }

    public void setSt_id(int st_id) {
        this.st_id = st_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_class() {
        return st_class;
    }

    public void setSt_class(String st_class) {
        this.st_class = st_class;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
