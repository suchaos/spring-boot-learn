package com.suchaos.cache.model.auto;

import java.io.Serializable;

public class Department implements Serializable {
    private Integer id;

    private String departmentname;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public Department withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public Department withDepartmentname(String departmentname) {
        this.setDepartmentname(departmentname);
        return this;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", departmentname=").append(departmentname);
        sb.append("]");
        return sb.toString();
    }
}