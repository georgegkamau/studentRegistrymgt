package com.github.perscholas.model;

// TODO - Annotate and Implement respective interface and define behaviors
public class Student implements StudentInterface {

    private String studentEmail;
    private String name;
    String password;

    public Student(String studentEmail, String name, String password) {
        this.studentEmail = studentEmail;
        this.name = name;
        this.password = password;
    }


    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Override
    public String getEmail() {
        return studentEmail;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentEmail='" + studentEmail + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
