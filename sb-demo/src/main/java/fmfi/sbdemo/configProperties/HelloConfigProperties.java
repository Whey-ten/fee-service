package fmfi.sbdemo.configProperties;

import org.springframework.boot.context.properties.*;

@ConfigurationProperties("app.hello")
public record HelloConfigProperties(String greeting, String format) {

  public HelloConfigProperties(String greeting, String format) {
    // set greeting default value to "Hello"
    this.greeting = greeting == null ? "Hello" : greeting;
    this.format = format == null ? "%s, %s" : format;
  }
}
