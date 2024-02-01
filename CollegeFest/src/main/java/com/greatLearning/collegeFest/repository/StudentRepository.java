package com.greatLearning.collegeFest.repository;
import com.greatLearning.collegeFest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
	
	@Repository
	public interface StudentRepository extends JpaRepository<Student, Integer> {
	    // You can add custom query methods if needed
	}

