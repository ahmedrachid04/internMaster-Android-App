package com.example.projet_stage_mobile_gestion.DataBase.Models;

import java.util.Date;


public class OfferModel {

    private long id;
    private String title;
    private String description;

    private Type type;
    private String domaine;
    private String duration;

    private Date startDate;

    private Date endDate;

    private Date postDate;

    private long companyId;

    public OfferModel(long id, String title, String description, Type type, String domaine, String duration, Date startDate, Date endDate, Date postDate, long companyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.domaine = domaine;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.postDate = postDate;
        this.companyId = companyId;
    }

    public OfferModel(String title, String description, Type type, String domaine, String duration, Date startDate, Date endDate, Date postDate, long companyId) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.domaine = domaine;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.postDate = postDate;
        this.companyId = companyId;
    }
    public OfferModel(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "OfferModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", domaine='" + domaine + '\'' +
                ", duration='" + duration + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", postDate=" + postDate +
                ", company=" + companyId +
                '}';
    }
}
