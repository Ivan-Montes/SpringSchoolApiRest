package ime.SchoolApiRest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ime.SchoolApiRest.dto.SubjectStudentCuteDto;
import ime.SchoolApiRest.dto.SubjectStudentDto;
import ime.SchoolApiRest.service.impl.SubjectStudentServiceImpl;

@RestController
@RequestMapping("/api/subjectstudents")
public class SubjectStudentController {
	
	@Autowired
	private SubjectStudentServiceImpl subjectStudentService;
	
	
	
	@GetMapping
	public ResponseEntity<List<SubjectStudentDto>>getAll(){
		
		return ResponseEntity.ok(subjectStudentService.getAllSubjectStudent());
		
	}
	
	@GetMapping
	public ResponseEntity<SubjectStudentCuteDto>getSubjectStudentCuteDtoById(@RequestParam Long subjectId, @RequestParam Long studentId){
		
		return ResponseEntity.ok(subjectStudentService.getSubjectStudentCuteDtoById(subjectId, studentId) );
				
	}
	
	

}
