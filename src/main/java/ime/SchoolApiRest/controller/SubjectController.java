package ime.SchoolApiRest.controller;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ime.SchoolApiRest.dto.SubjectBasicCreationDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.dto.SubjectDto;
import ime.SchoolApiRest.service.SubjectService;
import jakarta.validation.Valid;

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
	
	/**
	 * Delete a Subject by its Id
	 * 
	 * @param id with the identifier
	 * @return ResponseEntity with a message
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<String>deleteSubjectById(@PathVariable Long id){
		
		return ResponseEntity.ok("Subject with identifier " + id + " deleted successfully");
	}
	
	/**
	 * Create a new Subject
	 * 
	 * @param tbcDto. Object SubjectBasicCreationDto for adding in the DB
	 * @return ResponseEntity with the Subject required
	 */
	@PostMapping
	public ResponseEntity<SubjectBasicDto>createSubject(@Valid @RequestBody SubjectBasicCreationDto sbcDto){
		SubjectBasicDto sbDto = subjectService.createSubject(sbcDto);
		return new ResponseEntity<SubjectBasicDto>(sbDto, HttpStatus.CREATED);
	}
}
