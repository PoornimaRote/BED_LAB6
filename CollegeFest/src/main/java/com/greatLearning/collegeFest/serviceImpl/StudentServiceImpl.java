package com.greatLearning.collegeFest.serviceImpl;

import com.greatLearning.collegeFest.entity.Student;
import com.greatLearning.collegeFest.repository.StudentRepository;
import com.greatLearning.collegeFest.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(int studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

	@Override
	public void deleteStudent(int studentId) {
		studentRepository.deleteById(studentId);
	}
}
