package configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ThisIsMyFirstConditionalBean {

    @Value("${author.update_access}")
    private boolean isUpdateAuthorised;

    public boolean isUpdateAuthorised() {
        return isUpdateAuthorised;
    }

}
