## USER STORIES

# Release 1

    Jack loves movies, and needs a way to keep track of the movies that he watches. 
    He is presented with our movie list application, with the logic that is implemented for release 1. 
    Jack wants to add his fifteen favorite movies to the movie list. 
    He opens the application, and enters the first movie's title, genre and his personal score into the respective input fields. 
    When he clicks the "Add movie"-button, the movie details he put in should be validated. 
    If the validation is successful, the movie should be added to the movie list, and displayed on the screen. 
    He continues with this for all of his favorite movies, until a completed and sorted list is displayed on the left side of the screen. 
    Jack is thrilled that he now has a simple way of accessing all of his favorite movies.

    
Screenshot showing the application and its functionality: assets/release1

_Can also be found inside the documentation for release 1 (docs/release1)_

# Release 2

    Jack enjoys rewatching his favorite movies from time to time. Sometimes this means that he wants to give the movie a new score. 
    Whether he picked up on something new, or simply changes his opinion, Jack wants the opportunity to implement this to his movie diary. 
    In the previous release, adding the same movie multiple times would add duplicates to the movie list. 
    As Jack usually watches his absolute favorites many times, the logic in the first release provides a poor solution. 
    However, with the expanded functionality in release 2, Jack can now keep track of his rewatches in a much simpler way. 
    When Jack adds a movie with a title that already exists in his movie list, the two entries now combine. 
    In the movie list view, the new generated score is a average of all entries, while the genre is updated to the most recent. 
    Additionally, each movie object now has a corresponding counter, keeping track of how many times Jack has watched that specific movie.


    After discovering the movie-list application, Jack wants to show his friends in the movie group he is a part of. 
    His friend Alex is super excited, and can't wait to add his favorites to the list. However, with the logic from release 1, Jack and Alex 
    could not separate their lists. This means that Jack would completely lose track of which movies he has watched, and even worse, their 
    separate scores of the same movies would combine! Gladly, Jack did not show the application to Alex and the rest of the group before release 2. 
    This version of the application allows Jack to create his own movie list connected to a personal username. 
    This way, when Alex opens the application to make his movie list, he is able to create a new personal list, instead of sharing with everyone using the application. 


Screenshot showing the application and its functionality: assets/release2

_Can also be found inside the documentation for release 2 (docs/release2)_

# Release 3

    One day, as Jack logs into his account, he notices unfamiliar movies listed under his name. It appears that another person 
    named "Jack" has been adding their movie choices to the same username, causing their movie lists to become entangled. 
    Fortunately, with the introduction of release 3, Jack can now safeguard his list, thanks to the enhanced functionality that 
    offers a more secure way to curate his favorite movies. 
    
    When Jack's friend, Robert, visits the site, he faces the choice of either logging into his existing profile or creating a new one. 
    Since Robert hasn't created a movie list before, he opts to create a new profile. Entering his desired username, "Robert123," and password, 
    "Ilovemovies," he discovers that the username is already in use. Prompted with appropriate feedback, Robert tries again, this time with 
    the username "RobertSmith3105," which is available. He is then directed to a new page displaying his empty movie list, ready for him to 
    populate with his favorite films.

    To access his movielist later, Robert now needs to input both his username and password. If he enters the wrong password, he is denied access. 
    Upon successfully entering his username and the corresponding password, his movie list opens on a separate page. 
    Here, he can continue adding more movies according to his preference and rate them as well.


    Jack's curiosity drives him to explore the movie preferences of others, always on the lookout for new cinematic experiences. 
    With the advanced features introduced in release 3, Jack now has the ability to effortlessly navigate the movie list site. 
    By simply clicking a button, he gains access to a curated selection of the site's most popular movies. This unique page consolidates
     movies from every user's list into a new collection.

    On this page, Jack enjoys the flexibility to sort movies based on either their highest scores or the number of times they have been watched. 
    The application gathers data from all users of the application, computing the combined average scores to identify the movies with the highest 
    overall ratings. Additionally, Jack can explore the most watched movies, calculated by tallying the view counts across all users. 
    This comprehensive approach allows him to discover films that have garnered widespread acclaim or have been viewed repeatedly.

    When Jack discovers a top-rated movie that piques his interest, he can seamlessly exit the page, watch the movie, and then add it to his 
    personal movie list. Once he has curated his selection, he can confidently exit the page, ensuring that he logs out, thereby safeguarding 
    his movie choices and maintaining the security of his account.


Screenshots showing the application and its functionality: assets/release3
_Can also be found inside the documentation for release 3 (docs/release3)_