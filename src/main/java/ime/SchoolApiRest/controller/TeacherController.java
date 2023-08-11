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
/**
 * Controller for Teacher environment
 * 
 * @author Ivan-Montes
 *
 */
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
	@GetMapping
	public ResponseEntity<List<TeacherDto>> getAllEagerTeachersDto(){
		
		return ResponseEntity.ok(teacherService.getAllEagerTeachersDto());
		
	}
	/**
	 * Get a teacher according to Id
	 * 
	 * @param id with the identifier of a teacher
	 * @return ResponseEntity with the teacher required
	 */
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
	@DeleteMapping("{id}")
	public ResponseEntity<String>deteteTeacher(@PathVariable Long id){
		teacherService.deleteTeacherById(id);
		return ResponseEntity.ok("Teacher with identifier " + id + " deleted successfully");
	}
	
	/**
	 * Create a new teacher
	 * 
	 * @param tbcDto. Object TeacherBasicCreationDto for adding in the DB
	 * @return ResponseEntity with the teacher required
	 */
	@PostMapping
	public ResponseEntity<TeacherBasicDto>createTeacher(@RequestBody TeacherBasicCreationDto tbcDto){
		TeacherBasicDto tbd = teacherService.createTeacher(tbcDto);
		return new ResponseEntity<TeacherBasicDto>(tbd, HttpStatus.CREATED);
	}
	
	/**
	 * Update fields in a teacher
	 * 
	 * @param tbd Object TeacherBasicCreationDto with the new info
	 * @return ResponseEntity with the teacher modified
	 */
	@PutMapping
	public ResponseEntity<TeacherBasicDto>updateTeacher(@RequestBody TeacherBasicDto tbd){
		return ResponseEntity.ok(teacherService.updateTeacher(tbd.getTeacherId(), tbd));
	}
	
	/**
	 * Add a subject in a teacher
	 * 
	 * @param teacherId identifier of a teacher
	 * @param subjectId identifier of a subject
	 * @return ResponseEntity with the teacher modified
	 */
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
	@DeleteMapping("{teacherId}/{subjectId}")
	public ResponseEntity<TeacherDto>removeSubject(@PathVariable Long teacherId, @PathVariable Long subjectId){
		TeacherDto teacherDto = teacherService.removeSubjectFromTeacher(teacherId, subjectId);
		return ResponseEntity.ok(teacherDto);
	}
}
