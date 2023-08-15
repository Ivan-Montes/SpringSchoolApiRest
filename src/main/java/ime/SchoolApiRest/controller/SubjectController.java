package ime.SchoolApiRest.controller;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ime.SchoolApiRest.dto.SubjectBasicDto;
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
	
	/**
	 * Get a subject according to an Id
	 * 
	 * @param id with the identifier
	 * @return ResponseEntity with the subject required
	 */
	@GetMapping("{id}")
	public ResponseEntity<SubjectBasicDto> getSubjectBasicDtoById(@PathVariable Long id){
		
		return ResponseEntity.ok(subjectService.getSubjectBasicDtoById(id));
		
	}
	
}
