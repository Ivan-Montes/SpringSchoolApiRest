package ime.SchoolApiRest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ime.SchoolApiRest.dto.StudentBasicDto;
import ime.SchoolApiRest.dto.StudentDto;
import ime.SchoolApiRest.dto.TeacherBasicDto;
import ime.SchoolApiRest.service.impl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
	 */
	@Operation(summary="Get a Student according to an Id", description="Get a Student according to an Id, @return ResponseEntity with the Student required")
	@GetMapping("{id}")
	public ResponseEntity<StudentDto>getStudentDtoById(@PathVariable Long id){
		
		return ResponseEntity.ok(studentService.getStudentDtoById(id));
		
	}

}
