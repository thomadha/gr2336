package movielist.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = { MovieListRestController.class, MovieListService.class, SpringbootApplication.class})
@WebMvcTest(controllers = MovieListRestController.class)
@AutoConfigureMockMvc
class MovieListApplicationTests {

  @Autowired
  private MockMvc mockMvc;

	private Gson gson; 
	private MovieListHandler handler = new MovieListHandler("/src/main/java/json/MovieList.json"); 
	private MovieList movieList;
	private Movie m1; 
	private Movie m2; 
	private Movie m3; 
	private List<Movie> list; 


	// @BeforeAll
	// public void saveMovieList(){
	// 	movieList = handler.getMovieList("test"); 
	// }

	@AfterAll 
	public void loadMovielist(){
		handler.saveToFile(movieList);
	}

	@BeforeEach
	public void setup() throws Exception{
		this.gson = new Gson(); 
		this.movieList = new MovieList();
		movieList.setUsername("test");
		movieList.setPassword("123");
		m1 = new Movie("StarWars", 10, "scifi"); 
		m2 = new Movie("HarryPotter", 5, "action"); 
		m3 = new Movie("ThomasToget", 3, "horror"); 
		movieList.addMovie(m1);
		movieList.addMovie(m2);
		movieList.addMovie(m3);
		setupdefaultmovielist();
	}

	private MovieList get(String url){
		String movieListInJson; 

		try {
			movieListInJson = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse() 
				.getContentAsString(); 
		} catch (Exception e) {
			throw new RuntimeException("Could not retrive MovieList", e); 
		}
		return gson.fromJson(movieListInJson, MovieList.class); 
	}

	private void setupdefaultmovielist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/movielist/add")
			.accept(MediaType.APPLICATION_JSON)
			.content(gson.toJson(this.movieList)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn(); 
	}



	@Test
	public void testGetMovieList(){
    list = get("http://localhost:8080/movielist/test").getMovies();
		
    assertEquals("StarWars", list.get(0).getName());
    assertEquals("HarryPotter", list.get(1).getName());
    assertEquals("ThomasToget", list.get(2).getName());

    assertEquals("scifi", list.get(0).getGenre());
    assertEquals("action", list.get(1).getGenre());
    assertEquals("horror", list.get(2).getGenre());

    assertEquals(10.0, list.get(0).getScore());
    assertEquals(5.0, list.get(1).getScore());
    assertEquals(3.0 , list.get(2).getScore());
	}

	@Test
	public void testGetNumberOfMovies(){
		assertEquals(3,get("http://localhost:8080/movielist/test").getMovies().size());
	}

	@Test
	public void testAddMovie() throws Exception {
		Movie m4 = new Movie("Teletubbies", 6.0, "horror"); 

		list = get("http://localhost:8080/movielist/test").getMovies(); 

		assertEquals(3, list.size());

		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/movielist/test/addMovie")
			.accept(MediaType.APPLICATION_JSON)
			.content(gson.toJson(m4)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn(); 

		list = get("http://localhost:8080/movielist/test").getMovies(); 

		assertEquals(4, list.size());
	}

	@Test
	public void testGetMovieByName() throws Exception {
		Movie m4 = new Movie("Teletubbies", 6.0, "horror"); 
		String m4InJson = gson.toJson(m4); 
		String movieInJson; 

		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/movielist/test/addMovie")
			.accept(MediaType.APPLICATION_JSON)
			.content(m4InJson))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn(); 
	
		try{
			movieInJson = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/movielist/test/" + m4.getName())
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString(); 
		} catch (Exception e){
			throw new RuntimeException("Could not retrive movie", e); 
		}

		assertEquals(m4InJson, movieInJson);
	}

}
