package ime.SchoolApiRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ime.SchoolApiRest.dto.TeacherDto;
import ime.SchoolApiRest.entity.Teacher;
import ime.SchoolApiRest.service.TeacherService;

@RestController
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("/api/teachers")
	public ResponseEntity<List<Teacher>> findAll(){
		
		List<Teacher>teachers = teacherService.findAllEager();
		
		return ResponseEntity.ok(teachers);
	}
}