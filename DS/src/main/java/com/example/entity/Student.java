package com.example.entity;

import com.example.annotation.TableName;
import com.example.annotation.Type;

import java.sql.SQLException;

/**
 * @author qp
 */
@TableName("student")
public class Student {
    @Type(name = "id", isKey = true)
    private String id;
    @Type(name = "name")
    private String name;
    @Type(name = "gender")
    private String gender;
    @Type(name = "telephoneNumber")
    private String telephoneNumber;
    @Type(name = "specialize")
    private String specialize;


    public Student() {

    }


    public Student(String id, String name, String gender, String telephoneNumber, String specialize) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.telephoneNumber = telephoneNumber;
        this.specialize = specialize;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) throws SQLException {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws SQLException {
        this.gender = gender;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) throws SQLException {
        this.telephoneNumber = telephoneNumber;

    }

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) throws SQLException {
        this.specialize = specialize;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", specialize='" + specialize + '\'' +
                '}';
    }
}
