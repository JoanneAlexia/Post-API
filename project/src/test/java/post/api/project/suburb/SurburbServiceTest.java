package post.api.project.suburb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

@ExtendWith(MockitoExtension.class)
public class SurburbServiceTest {
    
    @Mock
    private SuburbRepository suburbRepository;

    @InjectMocks
    private SuburbService suburbService;

    @Test
    public void duplicateEntriesReturnEmptyOptional(){
        String suburbName = "Stanmore";
        String suburbState = "NSW";
        short postcode = 2048;


        Suburb testSuburb = new Suburb(suburbName,suburbState,postcode);
        SuburbCreateDTO testSuburbCreateDTO= new SuburbCreateDTO(suburbName, suburbState, String.valueOf(2048));

        when(suburbRepository.findBySuburbNameAndSuburbStateAndPostcode(suburbName,suburbState, postcode)).thenReturn(testSuburb);

        assertTrue(suburbService.create(testSuburbCreateDTO).isEmpty());
    }

    @Test
    public void canGetListSuburbsByPostcode(){
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        SuburbNameAndStateOnly suburbNameAndState1=  factory.createProjection(SuburbNameAndStateOnly.class);
        suburbNameAndState1.setSuburbName("Stanmore");
        suburbNameAndState1.setSuburbState("NSW");

        SuburbNameAndStateOnly suburbNameAndState2=  factory.createProjection(SuburbNameAndStateOnly.class);
        suburbNameAndState2.setSuburbName("Westgate");
        suburbNameAndState2.setSuburbState("NSW");

        List<SuburbNameAndStateOnly> expectedSuburbList = new ArrayList<>();
        expectedSuburbList.add(suburbNameAndState1);
        expectedSuburbList.add(suburbNameAndState2);
        
        short testPostcode = 2048;
        when(suburbRepository.findByPostcode(testPostcode)).thenReturn(expectedSuburbList);

        List<String> returnedPostcodeList = suburbService.getByPostcode(testPostcode);
        assertThat(returnedPostcodeList.size()).isEqualTo(2);
        assertThat(returnedPostcodeList).contains("Stanmore, NSW");
        assertThat(returnedPostcodeList).contains("Westgate, NSW");
    }

    @Test
    public void canGetListPostcodesBySuburbName(){
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        
        PostCodeOnly postcode1=  factory.createProjection(PostCodeOnly.class);
        postcode1.setPostcode((short) 2121);

        PostCodeOnly postcode2=  factory.createProjection(PostCodeOnly.class);
        postcode2.setPostcode((short) 3076);

        List<PostCodeOnly> expectedPostcodeList = new ArrayList<>();
        expectedPostcodeList.add(postcode1);
        expectedPostcodeList.add(postcode2);
        
        String suburbName = "Epping";
        when(suburbRepository.findBySuburbName(suburbName)).thenReturn(expectedPostcodeList);

        List<Short> returnedPostcodeList = suburbService.getBySuburbName(suburbName);
        assertThat(returnedPostcodeList.size()).isEqualTo(2);
        assertThat(returnedPostcodeList).contains((short) 2121);
        assertThat(returnedPostcodeList).contains((short) 3076);
    }

        @Test
    public void canGetListPostcodesBySuburbNameAndState(){
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        
        PostCodeOnly postcode1=  factory.createProjection(PostCodeOnly.class);
        postcode1.setPostcode((short) 2121);

        PostCodeOnly postcode2=  factory.createProjection(PostCodeOnly.class);
        postcode2.setPostcode((short) 1710);

        List<PostCodeOnly> expectedPostcodeList = new ArrayList<>();
        expectedPostcodeList.add(postcode1);
        expectedPostcodeList.add(postcode2);
        
        String suburbName = "Epping";
        String suburbState= "NSW";
        when(suburbRepository.findBySuburbNameAndSuburbState(suburbName,suburbState)).thenReturn(expectedPostcodeList);

        List<Short> returnedPostcodeList = suburbService.getBySuburbNameAndState(suburbName,suburbState);
        assertThat(returnedPostcodeList.size()).isEqualTo(2);
        assertThat(returnedPostcodeList).contains((short) 2121);
        assertThat(returnedPostcodeList).contains((short) 1710);
    }

}
