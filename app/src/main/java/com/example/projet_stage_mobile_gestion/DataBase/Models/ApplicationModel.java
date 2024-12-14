package com.example.projet_stage_mobile_gestion.DataBase.Models;

import java.util.Arrays;
import java.util.Date;

public class ApplicationModel {

    private long id;

    private Date applicationDate;
    private Status status;
    private byte[] cv;
    private byte[] applicationLetter;

    private long studentId;

    private long offerId;

    public long getStudentId() {
        return studentId;
    }

    public ApplicationModel(long id, Date applicationDate, Status status, byte[] cv, byte[] applicationLetter, long student, long offer) {
        this.id = id;
        this.applicationDate = applicationDate;
        this.status = status;
        this.cv = cv;
        this.applicationLetter = applicationLetter;
        this.studentId = student;
        this.offerId = offer;
    }

    public ApplicationModel() {
    }

    public void setStudentId(long student) {
        this.studentId = student;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offer) {
        this.offerId = offer;
    }


    public ApplicationModel(Date applicationDate, Status status, byte[] cv, byte[] applicationLetter, long student, long offer) {
        this.applicationDate = applicationDate;
        this.status = status;
        this.cv = cv;
        this.applicationLetter = applicationLetter;
        this.studentId = student;
        this.offerId = offer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    public byte[] getApplicationLetter() {
        return applicationLetter;
    }

    public void setApplicationLetter(byte[] applicationLetter) {
        this.applicationLetter = applicationLetter;
    }

    @Override
    public String toString() {
        return "ApplicationModel{" +
                "id=" + id +
                ", applicationDate=" + applicationDate +
                ", status=" + status +
                ", cv=" + Arrays.toString(cv) +
                ", applicationLetter=" + Arrays.toString(applicationLetter) +
                ", student=" + studentId +
                ", offer=" + offerId +
                '}';
    }
}
