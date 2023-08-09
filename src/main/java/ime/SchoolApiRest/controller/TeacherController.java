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

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@GetMapping
	public ResponseEntity<List<TeacherDto>> getAllEagerTeachersDto(){
		
		return ResponseEntity.ok(teacherService.getAllEagerTeachersDto());
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TeacherBasicDto>getTeacherDtoById(@PathVariable Long id){
		
		return ResponseEntity.ok(teacherService.getTeacherDtoById(id));
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String>deteteTeacher(@PathVariable Long id){
		teacherService.deleteTeacherById(id);
		return ResponseEntity.ok("Teacher with identifier " + id + " deleted successfully");
	}
	
	@PostMapping
	public ResponseEntity<TeacherBasicDto>createTeacher(@RequestBody TeacherBasicCreationDto tbcDto){
		TeacherBasicDto tbd = teacherService.createTeacher(tbcDto);
		return new ResponseEntity<TeacherBasicDto>(tbd, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<TeacherBasicDto>updateTeacher(@RequestBody TeacherBasicDto tbd){
		return ResponseEntity.ok(teacherService.updateTeacher(tbd.getTeacherId(), tbd));
	}
	
	@GetMapping("{teacherId}/{subjectId}")
	public ResponseEntity<TeacherDto>addSubject(@PathVariable Long teacherId, @PathVariable Long subjectId){
		TeacherDto teacherDto = teacherService.addSubjectToTeacher(teacherId, subjectId);
		return ResponseEntity.ok(teacherDto);
	}
	
	
}
