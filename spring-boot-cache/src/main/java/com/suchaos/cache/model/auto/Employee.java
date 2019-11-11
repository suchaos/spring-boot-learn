package com.suchaos.cache.model.auto;

import java.io.Serializable;

public class Employee implements Serializable {
    private Integer id;

    private String lastname;

    private String email;

    private Integer gender;

    private Integer dId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public Employee withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public Employee withLastname(String lastname) {
        this.setLastname(lastname);
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname == null ? null : lastname.trim();
    }

    public String getEmail() {
        return email;
    }

    public Employee withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public Employee withGender(Integer gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getdId() {
        return dId;
    }

    public Employee withdId(Integer dId) {
        this.setdId(dId);
        return this;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", lastname=").append(lastname);
        sb.append(", email=").append(email);
        sb.append(", gender=").append(gender);
        sb.append(", dId=").append(dId);
        sb.append("]");
        return sb.toString();
    }
}