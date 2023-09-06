package ime.SchoolApiRest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ime.SchoolApiRest.dto.StudentDto;
import ime.SchoolApiRest.service.impl.StudentServiceImpl;

/**
 * Controller for Student environment
 * 
 * @author Ivan-Montes
 *
 */
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
	@GetMapping
	public ResponseEntity<List<StudentDto>> getAllStudent(){
		
		return ResponseEntity.ok(studentService.getAllStudent());
		
	}
	
	

}
