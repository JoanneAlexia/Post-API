package post.api.project.suburb;

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
}
