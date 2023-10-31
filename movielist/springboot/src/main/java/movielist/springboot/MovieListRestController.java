package movielist.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MovieListRestController {
  
  @RequestMapping("/movielist")
  public String movielist() {
    return "Movielist";
  }

}
