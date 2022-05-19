package com.example.riams.response;

public class courseresponse {
    String id,course,department;

    public courseresponse(String id, String course, String department) {
        this.id = id;
        this.course = course;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
