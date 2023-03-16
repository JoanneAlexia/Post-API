package post.api.project.suburb;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SuburbCreateDTO {
    @NotBlank
    String suburbName; 

    @NotBlank
    @Pattern(regexp="^[A-Z]{3}",message="State format incorrect")
    String suburbState; 

    @NotNull
    @Pattern(regexp="^[0-9]{4}",message="Postcode format incorrect")
    String postcode;

    public String getSuburbName() {
        return suburbName;
    }

    public void setSuburbName(String suburbName) {
        this.suburbName = suburbName;
    }

    public String getSuburbState() {
        return suburbState;
    }

    public void setSuburbState(String suburbState) {
        this.suburbState = suburbState;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
