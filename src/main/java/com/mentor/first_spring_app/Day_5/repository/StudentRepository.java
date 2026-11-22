package com.mentor.first_spring_app.Day_5.repository;


import com.mentor.first_spring_app.Day_5.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;

    public List<Student> findAll(){
        return students;
    }

    public Optional<Student> findById(Long id){

        return students.stream().filter(s -> s.getId().equals(id)).findFirst();

    }

    public Student save(Student student){

        student.setId(nextId++);
        return student;
    }

    public boolean deleteById(Long id){
        return students.removeIf(s->s.getId().equals(id));

    }

}
