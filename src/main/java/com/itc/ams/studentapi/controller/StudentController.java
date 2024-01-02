package com.itc.ams.studentapi.controller;

import com.itc.ams.studentapi.data.StudentRepository;
import com.itc.ams.studentapi.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
//@RequestMapping("/api/student")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository repository;
    @GetMapping
    public String getStudents(Model model, @RequestParam(name="search", required = false) String search){
        List<Student> studentList = new ArrayList<>();
        if(search!=null && !search.isEmpty())
            studentList = repository.findByNameContainingIgnoreCaseOrCityContainingIgnoreCase(search, search);
        else
            studentList = repository.findAll();
        model.addAttribute("studentList", studentList);
        return "index";
    }

    @GetMapping("/create")
    public String showAdddingForm(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute Student student){
        repository.save(student);
        return "redirect:/student";
    }

    @GetMapping("/update/{id}")
    public String updateStudent(@PathVariable int id, Model model){
        Student student = repository.findById(id).get(0);
        model.addAttribute("student", student);
        return "update_student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id){
        repository.deleteById(id);
        return "redirect:/student";
    }

//    @GetMapping
//    public List<Student> getStudents(){
//        return  repository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Student getStudentById(@PathVariable int id){
//        return  repository.findById(id).get(0);
//    }
//
//    @PostMapping("/create")
//    public Student saveStudent(@RequestBody Student student){
//        Student record = repository.save(student);
//        return record;
//    }
//
//    @PutMapping("/update")
//    public Student updateStudent(@RequestBody Student student){
//        Student record = repository.save(student);
//        return record;
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public Student deleteStudent(@PathVariable int id){
//        Student student = repository.findById(id).get(0);
//        repository.deleteById(id);
//        return student;
//    }

}
