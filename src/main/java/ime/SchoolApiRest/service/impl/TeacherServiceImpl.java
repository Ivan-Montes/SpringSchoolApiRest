package ime.SchoolApiRest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ime.SchoolApiRest.entity.Teacher;
import ime.SchoolApiRest.repository.TeacherRepository;
import ime.SchoolApiRest.service.TeacherService;

@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepo;
	
	@Override
	public List<Teacher> findAll() {
		return teacherRepo.findAll();
	}

	@Override
	public List<Teacher> findAllEager() {		
		return teacherRepo.findAllEager();
	}

}
