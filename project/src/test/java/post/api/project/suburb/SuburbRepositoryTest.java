package post.api.project.suburb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SuburbRepositoryTest {
    
    @Autowired
    private SuburbRepository suburbRepository;

    @AfterEach
    void tearDown(){
        suburbRepository.deleteAll();
    }

    @Test
    void itShouldfindSuburbByPostcode(){
        //given
        short testPostcode = 2048;
        Suburb testSuburb = new Suburb(
            "Stanmore",
            "NSW",
            testPostcode
        );

        suburbRepository.save(testSuburb);
    
        //when
        SuburbNameAndStateOnly returnedSuburb =  suburbRepository.findByPostcode(testPostcode).get(0);

        //then
        assertThat(returnedSuburb.getSuburbNameAndState()).isEqualTo("Stanmore, NSW");
    }

    @Test
    void itShouldfindPostcodeBySuburbName(){
        //given
        short testPostcode = 2048;
        Suburb testSuburb = new Suburb(
            "Stanmore",
            "NSW",
            testPostcode
        );

        suburbRepository.save(testSuburb);
    
        //when
        PostCodeOnly returnedSuburb =  suburbRepository.findBySuburbName("Stanmore").get(0);

        //then
        assertThat(returnedSuburb.getPostcode()).isEqualTo(testPostcode);
    }

    @Test
    void itShouldfindPostcodeBySuburbNameAndState(){
        //given
        short testPostcode = 2048;
        Suburb testSuburb = new Suburb(
            "Stanmore",
            "NSW",
            testPostcode
        );

        suburbRepository.save(testSuburb);
    
        //when
        PostCodeOnly returnedSuburb =  suburbRepository.findBySuburbNameAndSuburbState("Stanmore","NSW").get(0);

        //then
        assertThat(returnedSuburb.getPostcode()).isEqualTo(testPostcode);
    }
}
