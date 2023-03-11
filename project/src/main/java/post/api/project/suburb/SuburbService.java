package post.api.project.suburb;

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

    public List<SuburbNameAndStateOnly> getByPostcode(short postcode){
        return this.repository.findByPostcode(postcode);
    }

    public List<Suburb> getBySuburbName(String suburbName){
        System.out.println(suburbName);
        return this.repository.findBySuburbName(suburbName);
    }
}
