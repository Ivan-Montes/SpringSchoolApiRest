package ime.school_api_rest.controller;
import java.util.List;

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

import ime.school_api_rest.dto.StudentBasicCreationDto;
import ime.school_api_rest.dto.StudentBasicDto;
import ime.school_api_rest.dto.StudentDto;
import ime.school_api_rest.dto.SubjectStudentDto;
import ime.school_api_rest.service.impl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller for Student environment
 * 
 * @author Ivan-Montes
 *
 */
@Tag(name = "Student Controller", description="Outlook on Student API")
@RestController
@RequestMapping("api/students")
public class StudentController {
	
	@Autowired
	private StudentServiceImpl studentService;
	
	/**
	 * Get a List of all Student
	 * 
	 * @return ResponseEntity with a List of Student
	 * 
	 */
	@Operation(summary="Get a List of all students", description="Get a List of all students, @return ResponseEntity with a List of StudentDto")
	@GetMapping
	public ResponseEntity<List<StudentDto>> getAllStudent(){
		
		return ResponseEntity.ok(studentService.getAllStudent());
		
	}	

	/**
	 * Get a Student according to an Id
	 * 
	 * @param id with the identifier
	 * @return ResponseEntity with the Student required
	 * 
	 */
	@Operation(summary="Get a Student according to an Id", description="Get a Student according to an Id, @return ResponseEntity with the Student required")
	@GetMapping("{id}")
	public ResponseEntity<StudentDto>getStudentDtoById(@PathVariable Long id){
		
		return ResponseEntity.ok(studentService.getStudentDtoById(id));
		
	}
	
	/**
	 * Delete a Student by its Id
	 * 
	 * @param id with the identifier of a Student
	 * @return ResponseEntity with a message
	 * 
	 */
	@Operation(summary="Delete a Student by its Id", description="Delete a Student by its Id, @return ResponseEntity with a message")
	@DeleteMapping("{id}")
	public ResponseEntity<String>deteteStudentById(@PathVariable Long id){
		
		studentService.deleteStudentById(id);
		return ResponseEntity.ok("Student with identifier " + id + " deleted successfully");
		
	}
	
	/**
	 * Create a new Student
	 * 
	 * @param sbcDto. Object StudentBasicCreationDto for adding in the DB
	 * @return ResponseEntity with the Student required
	 * 
	 */
	@Operation(summary="Create a new Student", description="Create a new Student, @return ResponseEntity with the Student required")
	@PostMapping
	public ResponseEntity<StudentBasicDto>createStudent(@Valid @RequestBody StudentBasicCreationDto sbcDto){
		
		return new ResponseEntity<StudentBasicDto>( studentService.createStudent(sbcDto) ,HttpStatus.CREATED);
		
	}

	/**
	 * Update fields in a Student
	 * 
	 * @param tbd Object StudentBasicDto with the new info
	 * @return ResponseEntity with the Student modified
	 * 
	 */
	@Operation(summary="Update fields in a Student", description="Update fields in a Student, @return ResponseEntity with the Student modified")
	@PutMapping
	public ResponseEntity<StudentBasicDto> updateStudent(@Valid @RequestBody StudentBasicDto sbDto){
		
		return ResponseEntity.ok(studentService.updateStudent(sbDto));
		
	}
	
	/**
	 * Add a Student in a Subject
	 * 
	 * @param studentId identifier of a Student
	 * @param subjectId identifier of a Subject
	 * @return ResponseEntity with the Student
	 * 
	 */
	@Operation(summary="Add a Student in a Subject", description="Add a Student in a Subject, @return ResponseEntity with the Student")
	@PutMapping("{studentId}/subjects/{subjectId}")
	public ResponseEntity<StudentDto>addStudentToSubject(@PathVariable Long studentId, @PathVariable Long subjectId){
		
		return ResponseEntity.ok(studentService.addStudentToSubject(studentId, subjectId));
				
	}
	
	
	/**
	 * Add a Student in a Subject
	 * 
	 * @param studentId identifier of a Student
	 * @param subjectId identifier of a Subject
	 * @param averageGrade 
	 * @return ResponseEntity with the Student
	 * 
	 */
	@Operation(summary="Add a Student in a Subject", description="Add a Student in a Subject, @return ResponseEntity with the Student")
	@PutMapping("{studentId}/subjects/{subjectId}/mark/{averageGrade}")
	public ResponseEntity<StudentDto>addStudentToSubjectWithMark(@PathVariable Long studentId, @PathVariable Long subjectId, @PathVariable Double averageGrade){
		
		return ResponseEntity.ok(studentService.addStudentToSubjectWithMark(studentId, subjectId, averageGrade));
				
	}
	
	/**
	 * Add a Student in a Subject
	 * 
	 * @param SubjectStudentDto
	 * @return ResponseEntity with the Student
	 * 
	 */
	@Operation(summary="Add a Student in a Subject", description="Add a Student in a Subject, @return ResponseEntity with the Student")
	@PostMapping("/subjects")
	public ResponseEntity<StudentDto>addStudentToSubjectWithMark(@Valid @RequestBody SubjectStudentDto subjectStudentDto){
		
		return ResponseEntity.ok(studentService.addStudentToSubjectWithMark(subjectStudentDto));
				
	}	

	/**
	 * Remove a Student from Subject
	 * 
	 * @param studentId identifier of a Student
	 * @param subjectId identifier of a Subject
	 * 
	 * @return ResponseEntity with message
	 * 
	 */
	@Operation(summary="Remove a Student from Subject", description="Remove a Student from Subject, @return ResponseEntity with message")
	@DeleteMapping("{studentId}/subjects/{subjectId}")
	public ResponseEntity<String>removeStudenFromSubject(@PathVariable Long studentId, @PathVariable Long subjectId){
		
		studentService.removeStudenFromSubject(studentId, subjectId);
		return ResponseEntity.ok("Student removed successfully");
		
	}
}
