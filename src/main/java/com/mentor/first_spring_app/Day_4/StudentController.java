package com.mentor.first_spring_app.Day_4;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;


    public List<Student> getAllStudents(@RequestParam(required = false) String course){

       if(course == null){
           return students;
       }

       List<Student> filtered = new ArrayList<>();

       for(Student s : students){
           if(s.getCourse().equalsIgnoreCase(course)){
               filtered.add(s);
           }
        }
       return filtered;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id){

        for(Student s : students){
            if(s.getId().equals(id)){
                return ResponseEntity.ok(s);

            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Student> createStudent (@RequestBody Student student){

        student.setId(nextId++);
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent (@PathVariable Long id, @RequestBody Student updated ){

        for(Student s : students){

            if(s.getId().equals(id)){

                s.setName(updated.getName());
                s.setAge(updated.getAge());
                s.setCourse(updated.getCourse());
                return ResponseEntity.ok(s);
            }

        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent (@PathVariable Long id){

        boolean removed = students.removeIf(s -> s.getId().equals(id));

        if(removed ){

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.notFound().build();

    }


}
