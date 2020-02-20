package com.github.perscholas.service;


import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.RegisteredStudentsDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 2/19/2020.
 */
public class RegisteredStudentsService implements RegisteredStudentsDao {

//    private DatabaseConnection bd;
private static final DatabaseConnection dbc = DatabaseConnection.MANAGEMENT_SYSTEM;
public static DatabaseConnection getDbConnection() {
    return dbc;
}
    private StudentService sc;

    @Override
    public List<CourseInterface> getAllCourses(String studentEmail) {

        return null;
    }


    public List<String> getAllCoursesStr(String studentemail) throws SQLException {
        ResultSet result = dbc.executeQuery("SELECT * FROM studentcourses WHERE `studentemail` = '" + studentemail + "';");
            List<String> list = new ArrayList<>();
            try {
                while (result.next()) {
                    String studentEmail = result.getString("studentemail");
                    String courseid = result.getString("courseid");
                    String coursename = result.getString("coursename");

                    list.add(courseid + " "+ studentEmail + " " + courseid);
                }
            } catch (SQLException se) {
                throw new Error(se);
            }
//        System.out.println(list.size());
//        System.out.println(list);

            return list;
    }




    // end
}
