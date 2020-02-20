package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;
import com.github.perscholas.utils.IOConsole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class StudentService implements StudentDao {
    private static final IOConsole console = new IOConsole();
    private final DatabaseConnection dbc;

    public StudentService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public StudentService() {
//        this(DatabaseConnection.UAT);
        this(DatabaseConnection.MANAGEMENT_SYSTEM);
    }

    @Override
    public List<StudentInterface> getAllStudents() {
        ResultSet resultSet = dbc.executeQuery("SELECT * FROM students");
        try {
            return null; // TODO - Parse `List<StudentInterface>` from `resultSet`
        } catch(Exception e) {
            throw new Error(e);
        }
    }


    // wrks
    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        return getAllStudentsWhere("email = '" + studentEmail + "'").get(0);
    }

    // wks
    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        StudentInterface student =  getStudentByEmail(studentEmail);
        String pass = student.getPassword();
        boolean evaluation = pass.equals(password);
        return evaluation;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        CourseService courseService = new CourseService();
        courseService.addNewCourse( courseId,studentEmail);
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return null;
    }


    //my
    public List<StudentInterface> getAllStudentsWhere(String condition) {
        ResultSet result = dbc.executeQuery("SELECT * FROM students WHERE " + condition + ";");
        List<StudentInterface> list = new ArrayList<>();
        try {
            while (result.next()) {
                String studentEmail = result.getString("email");
                String name = result.getString("name");
                String password = result.getString("password");
                StudentInterface student = new Student(studentEmail, name, password);
                list.add(student);
            }
        } catch (SQLException se) {
            throw new Error(se);
        }


        return list;
    }
}
