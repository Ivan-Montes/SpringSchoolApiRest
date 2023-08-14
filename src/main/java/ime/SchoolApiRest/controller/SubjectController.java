package ime.SchoolApiRest.controller;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ime.SchoolApiRest.dto.SubjectDto;
import ime.SchoolApiRest.service.SubjectService;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	/**
	 * Get a List of all subjects
	 * 
	 * @return ResponseEntity with a List of SubjectDto
	 */
	@GetMapping
	public ResponseEntity<Set<SubjectDto>> getAllEagerSubjectDto(){
		return ResponseEntity.ok(subjectService.getAllEagerSubjectDto());
	}
	
	
	
}
