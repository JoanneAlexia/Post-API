package post.api.project.suburb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SuburbRepository extends JpaRepository<Suburb, Long>{
    public List<SuburbNameAndStateOnly> findByPostcode(short postcode);
    List<Suburb> findBySuburbName(String suburbName);
}
