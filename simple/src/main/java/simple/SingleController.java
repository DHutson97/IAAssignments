package simple;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/problems")
public class SingleController {
	
	//
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Model> message() {
		Model m =new Model();
		m.setMessage("Welcome to Radar Web services");
		return (ResponseEntity<Model>) ResponseEntity.ok().body(m);
		
	}
	//
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Model> TO_UpperCase(@RequestHeader (value="TO_UPPERCASE") Boolean k, @RequestBody String model) {
		Model m = null;
			try {
				m = new ObjectMapper().readValue(model, Model.class);
			} catch (JsonMappingException e) {
				
			} catch (JsonProcessingException e) {
				
			}
			if(k==true) {
				
				m.setfName(m.getfName().toUpperCase());
				m.setTitle(m.getTitle().toUpperCase());
			}else if(k==false) {
				
				
			}else {
				m= new Model();
				m.setMessage("Response Not a Boolean");
			return (ResponseEntity<Model>) ResponseEntity.ok().body(m);
			
			
		}
		 
				return (ResponseEntity<Model>) ResponseEntity.ok().body(m);
		

	}
	@PutMapping
	public ResponseEntity<Model> update(@RequestBody String model) throws JsonMappingException, JsonProcessingException {
	 HttpHeaders responseHeaders= new HttpHeaders();
	 responseHeaders.set("X_TIME OF EVENT",LocalDateTime.now().toString());
	 Model m = null;
	
		m = new ObjectMapper().readValue(model, Model.class);
	
		return ResponseEntity.ok().headers(responseHeaders).body(m);
	}
	
	
	
	@RequestMapping(value="/{input}",method=RequestMethod.DELETE)
	
	public ResponseEntity<Model> myDelete(@PathVariable("input") String input) {
		Model m =new Model();
		
		try {
			Float.parseFloat(input);
			m.setMessage(input+" deleted sucessfully");
			return (ResponseEntity<Model>) ResponseEntity.ok().body(m);
		}catch (NumberFormatException e) {
	m.setMessage("nothing deleted");
	return (ResponseEntity<Model>) ResponseEntity.ok().body(m);
	}

}
}
