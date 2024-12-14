package com.example.projet_stage_mobile_gestion.DataBase.Models;


public class CompanyModel {

    private long id;
    private String name;
    private String address;
    private String contactEmail;
    private String contactNumber;
    private String fax;
    private String description;
    private byte[] logo;
    private String password;

    public CompanyModel(long id, String name, String address, String contactEmail, String contactNumber, String fax, String description, byte[] logo, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.fax = fax;
        this.description = description;
        this.logo = logo;
        this.password = password;
    }

    public CompanyModel(String name, String addresse, String contactEmail, String contactNumber, String fax, String description, byte[] logo, String password) {
        this.name = name;
        this.address = addresse;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.fax = fax;
        this.description = description;
        this.logo = logo;
        this.password = password;
    }

    public CompanyModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddresse() {
        return address;
    }

    public void setAddresse(String addresse) {
        this.address = addresse;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addresse='" + address + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", fax='" + fax + '\'' +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
