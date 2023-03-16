package post.api.project.suburb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class SuburbService {

    @Autowired
	private SuburbRepository repository;
    
    public Optional<Suburb> create(SuburbCreateDTO data){
        short passcode = Short.parseShort(data.getPostcode());
        Suburb existingSuburb = this.repository.findBySuburbNameAndSuburbStateAndPostcode(data.getSuburbName(), data.getSuburbState(),passcode);
        if(existingSuburb == null){
            Suburb newSuburb = new Suburb(data.getSuburbName(), data.getSuburbState(), passcode);
            this.repository.save(newSuburb);
            Optional<Suburb> newSuburbOptional = Optional.of(newSuburb);
            return newSuburbOptional;
        }else{
            return Optional.empty();
        }

    }

    public List<String> getByPostcode(short postcode){
        List<SuburbNameAndStateOnly> maybeSuburb = this.repository.findByPostcode(postcode);
        System.out.println(maybeSuburb);
        List<String> flattenedMaybeSuburb = new ArrayList<>();
        for(SuburbNameAndStateOnly suburb: maybeSuburb){
            flattenedMaybeSuburb.add(suburb.getSuburbNameAndState());
        }
        return flattenedMaybeSuburb;
    }

    public List<Short> getBySuburbName(String suburbName){
        List<PostCodeOnly> maybePostcode = this.repository.findBySuburbName(suburbName);
        List<Short> flattenedMaybePostcode = new ArrayList<>();
        for(PostCodeOnly suburb: maybePostcode){
            flattenedMaybePostcode.add(suburb.getPostcode());
        }
        return flattenedMaybePostcode;
    }

    public List<Short> getBySuburbNameAndState(String suburbName, String suburbState){
        List<PostCodeOnly> maybePostcode = this.repository.findBySuburbNameAndSuburbState(suburbName, suburbState);
        List<Short> flattenedMaybePostcode = new ArrayList<>();
        for(PostCodeOnly suburb: maybePostcode){
            flattenedMaybePostcode.add(suburb.getPostcode());
        }
        return flattenedMaybePostcode;
    }
}
