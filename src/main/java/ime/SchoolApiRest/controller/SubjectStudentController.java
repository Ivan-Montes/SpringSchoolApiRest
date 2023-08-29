package ime.SchoolApiRest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ime.SchoolApiRest.dto.SubjectStudentCuteDto;
import ime.SchoolApiRest.service.impl.SubjectStudentServiceImpl;

/**
 * Controller for SubjectStudent environment
 * 
 * @author Ivan-Montes
 *
 */
@RestController
@RequestMapping("/api/subjectstudents")
public class SubjectStudentController {
	
	@Autowired
	private SubjectStudentServiceImpl subjectStudentService;	
	
	/**
	 * Get a List of all SubjectStudent
	 * 
	 * @return ResponseEntity with a List of SubjectStudentDto
	 */
	@GetMapping
	public ResponseEntity<List<SubjectStudentCuteDto>>getAll(){
		
		return ResponseEntity.ok(subjectStudentService.getAllSubjectStudent());
		
	}
	
	/**
	 * Get a SubjectStudent according to an Id
	 * 
	 *
	 * @return ResponseEntity with the SubjectStudent required
	 */
	@GetMapping("/get-by-composite-key")
	public ResponseEntity<SubjectStudentCuteDto>getSubjectStudentCuteDtoById(@RequestParam Long subjectId, @RequestParam Long studentId){
		
		return ResponseEntity.ok(subjectStudentService.getSubjectStudentCuteDtoById(subjectId, studentId) );
				
	}
	
	

}
