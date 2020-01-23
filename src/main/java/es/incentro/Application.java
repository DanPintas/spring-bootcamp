package es.incentro;

import es.incentro.repo.BaseRepositoryImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class Application extends SpringBootServletInitializer {

    private static final String SPRING_CONFIG_NAME = "spring.config.name:app-config";

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).properties(SPRING_CONFIG_NAME).build().run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class).properties(SPRING_CONFIG_NAME);
    }

}