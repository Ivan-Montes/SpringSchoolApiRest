package ime.SchoolApiRest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ime.SchoolApiRest.dto.StudentDto;
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
	
	

}
