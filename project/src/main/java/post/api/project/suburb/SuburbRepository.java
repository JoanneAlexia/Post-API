package post.api.project.suburb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SuburbRepository extends JpaRepository<Suburb, Long>{
    List<SuburbNameAndStateOnly> findByPostcode(short postcode);
    List<PostCodeOnly> findBySuburbName(String suburbName);
    List<PostCodeOnly> findBySuburbNameAndSuburbState(String suburbName, String suburbState);
    Suburb findBySuburbNameAndSuburbStateAndPostcode(String suburbName, String suburbState, short postcode);
}
