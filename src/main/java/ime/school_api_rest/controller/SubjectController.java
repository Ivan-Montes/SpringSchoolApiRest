package ime.school_api_rest.controller;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ime.school_api_rest.dto.SubjectBasicCreationDto;
import ime.school_api_rest.dto.SubjectBasicDto;
import ime.school_api_rest.dto.SubjectDto;
import ime.school_api_rest.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller for Subject environment
 * 
 * @author Ivan-Montes
 *
 */
@Tag(name = "Subject Controller", description="Outlook on Subject API")
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
	@Operation(summary="Get a List of all subjects", description="Get a List of all subjects, @return ResponseEntity with a List of SubjectDto")
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
	@Operation(summary="Get a subject according to an Id", description="Get a subject according to an Id, @return ResponseEntity with the subject required")
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
	@Operation(summary="Delete a Subject by its Id", description="Delete a Subject by its Id, @return ResponseEntity with a message")
	@DeleteMapping("{id}")
	public ResponseEntity<String>deleteSubjectById(@PathVariable Long id){
		
		subjectService.deleteSubjectById(id);
		return ResponseEntity.ok("Subject with identifier " + id + " deleted successfully");
		
	}
	
	/**
	 * Create a new Subject
	 * 
	 * @param tbcDto. Object SubjectBasicCreationDto for adding in the DB
	 * @return ResponseEntity with the Subject required
	 */
	@Operation(summary="Create a new Subject", description="Create a new Subject, @return ResponseEntity with the Subject required")
	@PostMapping
	public ResponseEntity<SubjectBasicDto>createSubject(@Valid @RequestBody SubjectBasicCreationDto sbcDto){
		SubjectBasicDto sbDto = subjectService.createSubject(sbcDto);
		return new ResponseEntity<>(sbDto, HttpStatus.CREATED);
	}
	
	/**
	 * Update fields in a Subject
	 * 
	 * @param tbd Object SubjectBasicDto with the new info
	 * @return ResponseEntity with the Subject modified
	 */
	@Operation(summary="Update fields in a Subject", description="Update fields in a Subject, @return ResponseEntity with the Subject modified")
	@PutMapping
	public ResponseEntity<SubjectBasicDto>updateSubject(@Valid @RequestBody SubjectBasicDto sbDto){
		return ResponseEntity.ok(subjectService.updateSubject(sbDto.getSubjectId(), sbDto));
	}
	
	/**
	 * Add a Teacher in a Subject
	 * 
	 * @param subjectId identifier of a Subject
	 * @param teacherId identifier of a teacher
	 * @return ResponseEntity with the Subject modified
	 */
	@Operation(summary="Add a Teacher in a Subject", description="Add a Teacher in a Subject, @return ResponseEntity with the Subject modified")
	@GetMapping("{subjectId}/{teacherId}")
	public ResponseEntity<SubjectDto>addTeacher(@PathVariable Long subjectId, @PathVariable Long teacherId){
		
		return ResponseEntity.ok(subjectService.addTeacherToSubject(subjectId, teacherId));
		
	}
	
	/**
	 * Add a Student in a Subject
	 * 
	 * @param subjectId identifier of a Subject
	 * @param studentId identifier of a Student
	 * @return ResponseEntity with the Subject modified
	 */
	@Operation(summary="Add a Student in a Subject", description="Add a Student in a Subject, @return ResponseEntity with the Subject modified")
	@PutMapping("{subjectId}/students/{studentId}")
	public ResponseEntity<SubjectDto>addStudentToSubject(@PathVariable Long subjectId, @PathVariable Long studentId){
		
		return ResponseEntity.ok(subjectService.addStudentToSubject(subjectId, studentId));
		
	}

	/**
	 * Remove a Student in a Subject
	 * 
	 * @param subjectId identifier of a Subject
	 * @param studentId identifier of a Student
	 * @return ResponseEntity with the Subject modified
	 */
	@Operation(summary="Remove a Student in a Subject", description="Remove a Student in a Subject, @return ResponseEntity with the Subject modified")
	@DeleteMapping("{subjectId}/students/{studentId}")
	public ResponseEntity<SubjectDto>removeStudentFromSubject(@PathVariable Long subjectId, @PathVariable Long studentId){
		
		return ResponseEntity.ok(subjectService.removeStudentFromSubject(subjectId, studentId));
		
	}
}
