package post.api.project.suburb;

import org.springframework.beans.factory.annotation.Value;

public interface SuburbNameAndStateOnly {
    @Value("#{target.suburbName + ', ' + target.suburbState}")
    String getSuburbNameAndState();

    void setSuburbName(String suburbName);
    void setSuburbState(String suburbState);
}
