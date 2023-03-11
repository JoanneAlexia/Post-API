package post.api.project.suburb;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SuburbService {

    @Autowired
	private SuburbRepository repository;
    
    public Suburb create(SuburbCreateDTO data){
        Suburb newSuburb = new Suburb(data.getSuburbName(), data.getSuburbState(), data.getPostcode());
        this.repository.save(newSuburb);
        return newSuburb;
    }

    public List<String> getByPostcode(short postcode){
        List<SuburbNameAndStateOnly> maybeSuburb = this.repository.findByPostcode(postcode);
        List<String> flattenedMaybeSuburb = new ArrayList<>();
        for(SuburbNameAndStateOnly suburb: maybeSuburb){
            flattenedMaybeSuburb.add(suburb.getSuburbNameAndState());
        }
        return flattenedMaybeSuburb;
    }

    public List<Suburb> getBySuburbName(String suburbName){
        System.out.println(suburbName);
        return this.repository.findBySuburbName(suburbName);
    }
}
