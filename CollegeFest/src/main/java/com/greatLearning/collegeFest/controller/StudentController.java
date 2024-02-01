package com.greatLearning.collegeFest.controller;

import com.greatLearning.collegeFest.entity.Student;
import com.greatLearning.collegeFest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/list")
	public String listStudents(Model model) {
		List<Student> students = studentService.findAllStudents();
		model.addAttribute("students", students);
		return "student-list";
	}

	@PostMapping("/403")
	public String accessDenied() {
		return "403";
	}

	@GetMapping("/add")
	public String showStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable int id, Model model) {
		Student student = studentService.findStudentById(id);
		model.addAttribute("student", student);
		return "student/student-edit-form";
	}

	@PostMapping("/edit/{id}")
	public String updateStudent(@PathVariable int id, @ModelAttribute("student") Student student) {
		student.setId(id);
		studentService.updateStudent(student);
		return "redirect:/students";
	}

	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable int id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}
}
