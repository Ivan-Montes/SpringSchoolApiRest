package ime.SchoolApiRest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller for Secured Area environment
 * 
 * @author Ivan-Montes
 *
 */
@Tag(name = "SecuredArea Controller", description="Outlook on SecuredArea API")
@RestController
@RequestMapping("/secured")
public class SecuredAreaController {

	@Operation(summary="Gretting", description="Gretting from SecuredArea")
	@GetMapping
	public ResponseEntity<String>helloWorld(){
		return ResponseEntity.ok("Hello Secured Brave new World");
	}
}
