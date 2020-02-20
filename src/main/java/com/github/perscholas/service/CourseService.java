package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



// TODO - Implement respective DAO interface
public class CourseService implements CourseDao {
    @Override
    public List<CourseInterface> getAllCourses() {
        List<CourseInterface> list = new ArrayList<>();
        ResultSet rs = DatabaseConnection.MANAGEMENT_SYSTEM.executeQuery("SELECT * FROM courses");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String insturctor = rs.getString("instructor");
                Course course = new Course(id, name, insturctor);
                list.add(course);
            }
        } catch (SQLException e) {
            throw new Error(e);
        }
        return list;
    }


    public List<Integer> getAllCourseIds() {
        return getAllCourses()
                .stream()
                .map(course -> course.getId())
                .collect(Collectors.toList());
    }

    // m
    public String getNameById( int courseid) {
        int id;
        StringBuilder name = new StringBuilder();
        ResultSet rs = DatabaseConnection.MANAGEMENT_SYSTEM.executeQuery("select * from courses where id = " + courseid + ";");
        try {
            while (rs.next()) {
                id = rs.getInt("id");
                name.append(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new Error(e);
        }

        return name.toString();
    }


    //
    public void addNewCourse( int courseid,String studentemail) {

        String coursename = getNameById(courseid);
        try {

            String sqlStatement = "INSERT INTO studentcourses(courseid, studentemail, coursename) VALUES (" + courseid +", '"+ studentemail +"', '"+ coursename +"');";
            DatabaseConnection.MANAGEMENT_SYSTEM.getDatabaseConnection()
//            getDatabaseEngineConnection()
                    .prepareStatement(sqlStatement)
                    .execute();
            String infoMessage = String.format("Successfully executed statement \n\t`%s`", sqlStatement);
            System.out.println(infoMessage);

            //
//            try {
//                String infoMessage = getAllCourses();
//                System.out.println(infoMessage);
//            } catch ( Exception e) {
//                System.out.println(" hello from method  addNewCourse method");
//            }

//
        } catch (SQLException e) {
            throw new Error(e);
        }

    }


    public List<String> getAllCoursesByStudent(String email) {
        List<String> list = new ArrayList<>();
//        ResultSet rs = DatabaseConnection.MANAGEMENT_SYSTEM.executeQuery("SELECT * FROM courses");
        ResultSet rs = DatabaseConnection.MANAGEMENT_SYSTEM.executeQuery("SELECT * FROM studentcourses WHERE `studentemail` = '" + email +"';");
        try {
            while (rs.next()) {
                int courseid = rs.getInt("courseid");
                String coursename = rs.getString("coursename");
//                String insturctor = rs.getString("instructor");
//                Course course = new Course(id, name, insturctor);
                list.add(courseid + " "+ coursename);
            }
        } catch (SQLException e) {
            throw new Error(e);
        }
        return list;
    }
}
