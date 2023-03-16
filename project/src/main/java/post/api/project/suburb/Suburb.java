package post.api.project.suburb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Suburb")
public class Suburb {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column 
    private String suburbName; 

    @Column
    private String suburbState; 

    @Column
    private short postcode;

    public Suburb(){

    }
    
    public Suburb(String suburbName, String suburbState, short postcode){
        this.suburbName = suburbName;
        this.suburbState = suburbState;
        this.postcode = postcode;
    }

        public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Suburb [id=" + id + ", suburbName=" + suburbName + ", suburbState=" + suburbState + ", postcode="
                + postcode + "]";
    }
}
