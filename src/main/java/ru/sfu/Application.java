package ru.sfu;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/** Main class of Spring Boot Application */
@SpringBootApplication(scanBasePackages = "ru.sfu")
public class Application extends SpringBootServletInitializer {

  private static String port = "8081";

  /** Configuring the application */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(Application.class);
  }

  /**
   * Entry point
   * @param args Command Line Arguments
   */
  public static void main(String[] args) {
    if (args.length > 0 && args[0].matches("[0-9]+")) {
      port = args[0];
    }

    SpringApplication app = new SpringApplication(Application.class);
    app.setDefaultProperties(Collections.singletonMap("server.port", port));
    app.run(args);
  }
}
