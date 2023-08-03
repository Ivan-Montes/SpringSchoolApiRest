package ime.SchoolApiRest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.SchoolApiRest.entity.Teacher;

@Service
public interface TeacherService {

	List<Teacher> findAll();
	
	List<Teacher> findAllEager();
}
