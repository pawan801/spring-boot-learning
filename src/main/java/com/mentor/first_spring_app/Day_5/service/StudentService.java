package com.mentor.first_spring_app.Day_5.service;

import com.mentor.first_spring_app.Day_5.dto.StudentRequestDto;
import com.mentor.first_spring_app.Day_5.dto.StudentResponseDto;
import com.mentor.first_spring_app.Day_5.model.Student;
import com.mentor.first_spring_app.Day_5.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public Optional<StudentResponseDto> getStudentById(Long id) {
        return studentRepository.findById(id).map(this::toResponseDto);
    }

    public StudentResponseDto createStudent(StudentRequestDto request) {

        if (request.getAge() < 16) {
            throw new IllegalArgumentException("Student must be at least 16 years old");
        }

        Student student = new Student();
        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setCourse(request.getCourse());
        student.setNic(request.getNic());

        Student saved = studentRepository.save(student);
        return toResponseDto(saved);
    }

    public boolean deleteStudent(Long id) {
        return studentRepository.deleteById(id);
    }


    private StudentResponseDto toResponseDto(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getCourse()
        );
    }


}
