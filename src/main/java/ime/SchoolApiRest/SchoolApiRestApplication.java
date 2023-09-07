package ime.SchoolApiRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class SchoolApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApiRestApplication.class, args);
	}
	
	@Bean
	OpenAPI myOpenAPI() {
	    Contact contact = new Contact();
	    contact.setEmail("ivan@github.com");
	    contact.setName("Ivan");
	    contact.setUrl("https://www.github.com/ivan-montes");

	    License mitLicense = new License().name("GPL 3").url("https://choosealicense.com/licenses/gpl-3.0/");

	    Info info = new Info()
	        .title("School Api Rest")
	        .version("1.0")
	        .contact(contact)
	        .description("This API exposes endpoints to manage School Api Rest.").termsOfService("https://choosealicense.com/licenses/gpl-3.0/")
	        .license(mitLicense);

	    return new OpenAPI().info(info);
	 
	}

}
