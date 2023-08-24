package ime.SchoolApiRest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Secured Area environment
 * 
 * @author Ivan-Montes
 *
 */
@RestController
@RequestMapping("/secured")
public class SecuredAreaController {

	@GetMapping
	public ResponseEntity<String>helloWorld(){
		return ResponseEntity.ok("Hello Secured Brave new World");
	}
}
