package fmfi.sbdemo.adapter.integration;

import org.mockserver.integration.ClientAndServer;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.mock.OpenAPIExpectation.openAPIExpectation;

@org.springframework.stereotype.Component
@org.springframework.context.annotation.Profile("mock")
@lombok.AllArgsConstructor
public class IntegrationMockServer {

    private final ClientAndServer mockServer = startClientAndServer(1080);

    @jakarta.annotation.PostConstruct // method executed after dependency injection is done
    public void startServer() {
        mockServer.upsert(openAPIExpectation("fee-service_v2.yaml"));
    }

    @jakarta.annotation.PreDestroy // method executed before application shutdown
    public void stopServer() {
        mockServer.stop();
    }
}

