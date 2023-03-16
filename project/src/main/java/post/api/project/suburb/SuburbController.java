package post.api.project.suburb;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            Optional<Suburb> createdSuburb = this.service.create(data);
            if(createdSuburb.isPresent()){
                return new ResponseEntity<>(createdSuburb.get(),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(null,HttpStatus.CONFLICT);
            }
            
        }catch(Exception e){
            logger.error("There was an error when attempting to create new suburb entry");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/postcode/{postcode}")
    public ResponseEntity<List<String>> getByPostcode(@PathVariable short postcode){
        try{
            List<String> maybeSuburb = this.service.getByPostcode(postcode);
            if(maybeSuburb.isEmpty()){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(maybeSuburb,HttpStatus.OK);
        }catch(Exception e){
            logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/suburbName/{suburbName}")
    public ResponseEntity<List<Short>> getBySuburbName(@PathVariable String suburbName){
        try{
            List<Short> maybeSuburb = this.service.getBySuburbName(suburbName);
            if(maybeSuburb.isEmpty()){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(maybeSuburb,HttpStatus.OK);
        }catch(Exception e){
            logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/suburbName/{suburbName}/suburbState/{suburbState}")
    public ResponseEntity<List<Short>> getBySuburbNameAndState(@PathVariable String suburbName, @PathVariable String suburbState){
        try{
            List<Short> maybeSuburb = this.service.getBySuburbNameAndState(suburbName,suburbState);
            if(maybeSuburb.isEmpty()){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(maybeSuburb,HttpStatus.OK);
        }catch(Exception e){
            logger.error(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
