package ime.SchoolApiRest.controller;

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

import ime.SchoolApiRest.dto.TeacherBasicCreationDto;
import ime.SchoolApiRest.dto.TeacherBasicDto;
import ime.SchoolApiRest.dto.TeacherDto;
import ime.SchoolApiRest.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
/**
 * Controller for Teacher environment
 * 
 * @author Ivan-Montes
 *
 */
@Tag(name = "Teacher Controller", description="Outlook on Teacher API")
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	/**
	 * Get a List of all teachers
	 * 
	 * @return ResponseEntity with a List of TeacherDto
	 */
	@Operation(summary="Get a List of all teachers", description="Get a List of all teachers, @return ResponseEntity with a List of TeacherDto")
	@GetMapping
	public ResponseEntity<List<TeacherDto>> getAllEagerTeachersDto(){
		
		return ResponseEntity.ok(teacherService.getAllEagerTeachersDto());
		
	}
	/**
	 * Get a teacher according to an Id
	 * 
	 * @param id with the identifier
	 * @return ResponseEntity with the teacher required
	 */
	@Operation(summary="Get a teacher according to an Id", description="Get a teacher according to an Id, @return ResponseEntity with the teacher required")
	@GetMapping("{id}")
	public ResponseEntity<TeacherBasicDto>getTeacherDtoById(@PathVariable Long id){
		
		return ResponseEntity.ok(teacherService.getTeacherDtoById(id));
		
	}
	
	/**
	 * Delete a teacher by its Id
	 * 
	 * @param id with the identifier of a teacher
	 * @return ResponseEntity with a message
	 */
	@Operation(summary="Delete a teacher by its Id", description="Delete a teacher by its Id, @return ResponseEntity with a message")
	@DeleteMapping("{id}")
	public ResponseEntity<String>deteteTeacherById(@PathVariable Long id){
		teacherService.deleteTeacherById(id);
		return ResponseEntity.ok("Teacher with identifier " + id + " deleted successfully");
	}
	
	/**
	 * Create a new teacher
	 * 
	 * @param tbcDto. Object TeacherBasicCreationDto for adding in the DB
	 * @return ResponseEntity with the teacher required
	 */
	@Operation(summary="Create a new teacher", description="Create a new teacher, @return ResponseEntity with the teacher required")
	@PostMapping
	public ResponseEntity<TeacherBasicDto>createTeacher(@Valid @RequestBody TeacherBasicCreationDto tbcDto){
		TeacherBasicDto tbd = teacherService.createTeacher(tbcDto);
		return new ResponseEntity<TeacherBasicDto>(tbd, HttpStatus.CREATED);
	}
	
	/**
	 * Update fields in a teacher
	 * 
	 * @param tbd Object TeacherBasicDto with the new info
	 * @return ResponseEntity with the teacher modified
	 */
	@Operation(summary="Update fields in a teacher", description="Update fields in a teacher, @return ResponseEntity with the teacher modified")
	@PutMapping
	public ResponseEntity<TeacherBasicDto>updateTeacher(@Valid @RequestBody TeacherBasicDto tbd){
		return ResponseEntity.ok(teacherService.updateTeacher(tbd.getTeacherId(), tbd));
	}
	
	/**
	 * Add a subject in a teacher
	 * 
	 * @param teacherId identifier of a teacher
	 * @param subjectId identifier of a subject
	 * @return ResponseEntity with the teacher modified
	 */
	@Operation(summary="Add a subject in a teacher", description="Add a subject in a teacher, @return ResponseEntity with the teacher modified")
	@GetMapping("{teacherId}/{subjectId}")
	public ResponseEntity<TeacherDto>addSubject(@PathVariable Long teacherId, @PathVariable Long subjectId){
		TeacherDto teacherDto = teacherService.addSubjectToTeacher(teacherId, subjectId);
		return ResponseEntity.ok(teacherDto);
	}
	
	/**
	 * Remove a subject from a teacher
	 * 
	 * @param teacherId identifier of a teacher
	 * @param subjectId identifier of a subject
	 * @return ResponseEntity with the teacher modified
	 */
	@Operation(summary="Remove a subject from a teacher", description="Remove a subject from a teacher, @return ResponseEntity with the teacher modified")
	@DeleteMapping("{teacherId}/{subjectId}")
	public ResponseEntity<TeacherDto>removeSubject(@PathVariable Long teacherId, @PathVariable Long subjectId){
		TeacherDto teacherDto = teacherService.removeSubjectFromTeacher(teacherId, subjectId);
		return ResponseEntity.ok(teacherDto);
	}	
	
}
