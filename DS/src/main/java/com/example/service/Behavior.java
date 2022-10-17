package com.example.service;

import com.example.entity.Student;

public interface Behavior {
    int add(Student student) throws Exception;
    void delete() throws Exception;
    void update() throws Exception;
    void quire() throws Exception;
}
