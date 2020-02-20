package com.github.perscholas.model;

// TODO - Annotate and Implement respective interface and define behaviors
public class Course implements CourseInterface{

    int id;
    String name;
    String insturctor;

    public Course(int id, String name, String insturctor) {
        this.id = id;
        this.name = name;
        this.insturctor = insturctor;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInstructor() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setInstructor(String instructor) {

    }

    public String getInsturctor() {
        return insturctor;
    }

    public void setInsturctor(String insturctor) {
        this.insturctor = insturctor;
    }

}
