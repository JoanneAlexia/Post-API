package post.api.project.suburb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class SuburbControllerTest {
    @Mock
    private SuburbService suburbService;

    @InjectMocks
    private SuburbController suburbController;

    @Test
    void createShouldCreateSuccessfully(){
        Suburb suburb = new Suburb("Stanmore", "NSW", (short) 2048);
        suburb.setId((long) 23);
        Optional<Suburb> expected  = Optional.of(suburb);

        when(suburbService.create(any())).thenReturn(expected);

        SuburbCreateDTO request = new SuburbCreateDTO("Stanmore", "NSW", "2048");
        ResponseEntity<Suburb> response = suburbController.create(request);
        Suburb actual = response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(suburb.getId(), actual.getId())
        );
    }

    @Test
    void getByPostcodeShouldGetSuburbListSuccessfully(){
        List<String> expected = new ArrayList<>();
        expected.add("Stanmore, NSW");
        expected.add("Westgate, NSW");

        when(suburbService.getByPostcode((short) 2048)).thenReturn(expected);

        short request = 2048;
        ResponseEntity<List<String>> response = suburbController.getByPostcode(request);
        List<String> actual = response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertThat(actual).contains(expected.get(0)),
                () -> assertThat(actual).contains(expected.get(1))
        );
    }

    @Test
    void getBySuburbNameShouldGetPostcodeListSuccessfully(){
        List<Short> expected = new ArrayList<>();
        expected.add(Short.valueOf("2121"));
        expected.add(Short.valueOf("3076"));

        when(suburbService.getBySuburbName("Epping")).thenReturn(expected);

        ResponseEntity<List<Short>> response = suburbController.getBySuburbName("Epping");
        List<Short> actual = response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertThat(actual).contains(expected.get(0)),
                () -> assertThat(actual).contains(expected.get(1))
        );
    }

    @Test
    void getBySuburbNameAndStateShouldGetPostcodeListSuccessfully(){
        List<Short> expected = new ArrayList<>();
        expected.add(Short.valueOf("2121"));
        expected.add(Short.valueOf("1710"));

        when(suburbService.getBySuburbNameAndState("Epping","NSW")).thenReturn(expected);

        ResponseEntity<List<Short>> response = suburbController.getBySuburbNameAndState("Epping","NSW");
        List<Short> actual = response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertThat(actual).contains(expected.get(0)),
                () -> assertThat(actual).contains(expected.get(1))
        );
    }
}
