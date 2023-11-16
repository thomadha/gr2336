package dataaccess;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import core.Movie;
import core.MovieList;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.List;


/**
 * Classs for accessing movielist remotely.
 */
public class MovieListRemoteAccess implements MovieListAccess {

  private MovieList movieList = new MovieList();
  private final URI endpointUri;
  private Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private String mapping = "movielist/";

  

  /**
   * Contstructor for ListContainerRemoteAccess.
   * Insures that the server is live. 
   *
   * @param endUri        the endURI.
   * @param mock          tells whether the server is a mocked one or not, 
   *     and with that information whether to test the conncetion or not.
   * @throws IOException  thrown if the server is not running.
   * @throws InterruptedException thrown if the request is interrupted.
   */
  public MovieListRemoteAccess(URI endUri, Boolean mock) 
        throws IOException, InterruptedException {
    if (!mock) {
      HttpRequest request = HttpRequest.newBuilder(endUri)
            .header("Accept", "application/json").GET().build();
      final HttpResponse<String> response = 
            HttpClient.newBuilder().build().send(request,
            HttpResponse.BodyHandlers.ofString());
      if (!response.body().equals("OK")) {
        throw new IOException("Server is not running");
      }
    }
    this.endpointUri = endUri;
  }

  /**
   * Method for creating the correct path to the URI.
   *
   * @param uri   the uri you want to find the path to.
   * @return URI  the endpoint URI.
   */
  private URI movieListUri(String uri) {  
    return endpointUri.resolve(uri);
  }


  public void updateMovieList(MovieList movieList) {
    this.movieList = movieList;
  }

  /**
   * Access method for a specific movielist by name. 
   *
   * @param username the name of the movielist you want to access.
   * @return movielist the wanted movielist.
   */
  @Override
  public MovieList getMovieListByUsername(String username) {
    try {
      HttpRequest request = HttpRequest.newBuilder(movieListUri(mapping + username))
          .header("Accept", "application/json").GET().build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      final String responseString = response.body();
      MovieList list = gson.fromJson(responseString, MovieList.class);
      return list;
    } catch (IOException | InterruptedException e) {
      return null;
    }
  }


  @Override
  public void addMovieList(MovieList movieList) {
    String username = movieList.getUsername();
    String password = movieList.getPassword();
    String addList = "/" + username + "/" + password + "/newUser";
    String jsonBody = gson.toJson(movieList);

    HttpRequest request = HttpRequest.newBuilder(movieListUri(mapping + addList))
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(jsonBody))
            .build();

    try {
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, 
            HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() != 200) {
        throw new IOException("Failed to add movie list. Status code: " + response.statusCode());
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Method for removing a spesific movielist by name.
   *
   * @param username the name of the movielist you want to remove.
   * @throws RuntimeException  thrown if not able to perform it's task.
   */
  @Override
  public void removeMovieList(String username) {
    String remove = "/deleteUser";
    try {
      HttpRequest request = HttpRequest
          .newBuilder(movieListUri(mapping + username + remove))
          .DELETE()
          .build();
      HttpResponse<String> response = HttpClient.newBuilder().build()
           .send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() > 399) {
        throw new IOException("Not legal status code");
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }



  /**
   * Method for getting all movie lists from file.
   *
   * @return the movielists
   */
  @Override
  public List<MovieList> getAllMovieListsFromFile() {
    String get = "getall";
    try {
      HttpRequest request = HttpRequest.newBuilder(movieListUri(mapping + get))
          .header("Accept", "application/json").GET().build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() == 200) {
        Gson gson = new Gson();
        Type movieListType = new TypeToken<List<MovieList>>(){}.getType();
        List<MovieList> movieLists = gson.fromJson(response.body(), movieListType);

        if (movieLists != null) {
          return movieLists;
        }
      } else {
        System.out.println("Failed to retrieve movie lists. Status code: " + response.statusCode());
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return null;
  }


  /**
   * Add a movie to the MovieList on the remote server.
   *
   * @param newMovie  The movie to add to the list.
   */
  public void addMovieToList(Movie newMovie) {
    String username = this.movieList.getUsername();
    String add = username + "/addMovie";
    movieList.addMovie(newMovie);

    try {
      String jsonBody = gson.toJson(newMovie);
      System.out.println("Request Payload: " + jsonBody);

      HttpRequest request = HttpRequest.newBuilder(movieListUri(mapping + add))
          .header("Add", "application/json")
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(jsonBody))
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
            HttpResponse.BodyHandlers.ofString());

      // Check if the response indicates successful update
      if (response.statusCode() == 200) {
        // Updating successful
        System.out.println("Movie added successfully");
      } else {
        // Updating failed, print the response body for debugging
        System.out.println("Failed to add movie. Response body: " + response.body());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
