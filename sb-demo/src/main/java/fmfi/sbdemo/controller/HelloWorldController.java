package fmfi.sbdemo.controller;

import fmfi.sbdemo.configProperties.HelloConfigProperties;
import org.springframework.web.bind.annotation.*;

@RestController
@lombok.AllArgsConstructor // creates constructor with all arguments that will be used by Spring
public class HelloWorldController {

  // this field will be initialized by Spring's constructor dependency injection
  private final HelloConfigProperties configProperties;

  @GetMapping("/api/hello")
  public String sayHello(
      @RequestParam(name = "subject", defaultValue = "${app.hello.subject}") String subject
  ) {
    return String.format(configProperties.format(), configProperties.greeting(), subject);
  }

}
