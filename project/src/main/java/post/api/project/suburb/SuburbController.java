package post.api.project.suburb;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suburb")
public class SuburbController {
    Logger logger = LoggerFactory.getLogger(SuburbController.class);

    @Autowired 
	private SuburbService service; 

    @PostMapping
    public ResponseEntity<Suburb> create(@Valid @RequestBody SuburbCreateDTO data){
        try{
            Suburb createdSuburb = this.service.create(data);
            return new ResponseEntity<>(createdSuburb,HttpStatus.CREATED);
        }catch(Exception e){
            logger.error("There was an error when attempting to create new suburb entry");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
