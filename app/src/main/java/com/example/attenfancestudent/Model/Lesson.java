package com.example.attenfancestudent.Model;

public class Lesson {
    private int Id_lesson;
    private String stime;
    private String etime;
    private String datelesson;
    private int id_teacher;
    private String n_teacher;
    private int id_subject;
    private String lclass;

    public Lesson() {
    }

    public Lesson(int id_lesson, String stime, String etime, String datelesson, int id_teacher, String n_teacher, int id_subject, String lclass) {
        Id_lesson = id_lesson;
        this.stime = stime;
        this.etime = etime;
        this.datelesson = datelesson;
        this.id_teacher = id_teacher;
        this.n_teacher = n_teacher;
        this.id_subject = id_subject;
        this.lclass = lclass;
    }

    public int getId_lesson() {
        return Id_lesson;
    }

    public void setId_lesson(int id_lesson) {
        Id_lesson = id_lesson;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getDatelesson() {
        return datelesson;
    }

    public void setDatelesson(String datelesson) {
        this.datelesson = datelesson;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public String getN_teacher() {
        return n_teacher;
    }

    public void setN_teacher(String n_teacher) {
        this.n_teacher = n_teacher;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getLclass() {
        return lclass;
    }

    public void setLclass(String lclass) {
        this.lclass = lclass;
    }
}
