package fmfi.sbdemo.adapter.integration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration // Indicates that a class declares one or more @Bean methods
@lombok.AllArgsConstructor
public class IntegrationAdapterConfiguration {

    private final IntegrationFeesConfigProperties feesConfigProperties;

    @Bean // Indicates that a method produces a bean to be managed by the Spring container
    RestTemplate feesRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(feesConfigProperties.baseUri())
                .basicAuthentication(feesConfigProperties.username(), feesConfigProperties.password())
                .build();
    }

    @ConfigurationProperties("app.integration.fees")
    public record IntegrationFeesConfigProperties(
            String baseUri,
            String username,
            String password
    ) {}
}
