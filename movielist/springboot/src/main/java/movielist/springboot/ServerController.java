package movielist.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class for checking if the server is running.
 */

@RestController
public class ServerController {
  @GetMapping
  public String health() {
    return "OK";
  }
}


