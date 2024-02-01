package com.greatLearning.collegeFest.service;

import com.greatLearning.collegeFest.entity.Student;
import java.util.List;

public interface StudentService {
	void saveStudent(Student student);

	void updateStudent(Student student);

	void deleteStudent(int studentId);

	List<Student> findAllStudents();

	Student findStudentById(int studentId);
}
