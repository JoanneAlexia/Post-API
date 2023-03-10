package post.api.project.suburb;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SuburbCreateDTO {
    @NotBlank
    String suburbName; 

    @NotBlank
    String suburbState; 

    @NotNull
    short postcode;

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

    public short getPostcode() {
        return postcode;
    }

    public void setPostcode(short postcode) {
        this.postcode = postcode;
    }
}
