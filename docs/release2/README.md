## About the project

The Movie App is a JavaFX application that allows you to add and display a list of movies with their scores. This is the second draft of a database where you can save the movies you've watched. It's sorted by score, from highest to lowest.

## Assets

Screenshot of the current application:

![release2](assets/release2/release2.png "Picture"){width=25%}

## New features for this release

- If you add the same movie more than once, the data connected combines instead of making duplicates
    - In the json file, a count attribute is added, and shows how many times you have watched the movie
    - The genre attribute is updated to the latest contribution
    - The score is generated as an average from all the times you have watched it
- A login feature is also added
    - This seperates your movie list from other users
    - It works by writing in your username and clicking "Save & Close". If you want to open the list, you write in your username and click "Open"
    - Currently it is not possible to save your list for later use. Meaning if you close the app after you run it and saved a list, it will not be possible to open the next time you run it. This is planned to implement for the next release.

## How to use

If you haven't already, you need to clone the repository to your local machine. The app can be run from the App.java class by clicking run, or with mvn javafx:run if Maven is installed.

## Diagrams related to the release

For this release, the group has included three diagrams to illustrate the architecture of the application. The class diagram, sequence diagram and package diagram are linked underneath:

![Class Diagram](http://your-plantuml-server-url.com/plantuml/svg/ZLJRRjim37ttL-ZHsPOVC8QXMRfs3xqCY7z0iLY7g2ovafpTi7-VH4cnrIP0ys1OXuU7XnITA-kuSTCWsLPoQzaryjxmeRBFg4RktzZE_PR0h3FO1Tuo0YyJg0xkmLhUmqPYhmPGZdN-0QQgEfAyrqS4zhTYQzQYaz1uIOEgPm7PTTfuHEXfxwi4v1QKAJbRFIdNCFJA5Vl8ZXe5i-0U-01rfAsME4dMKR48aFAR32x4zJHAxBY3ck1KkaybNc5HXS3akiyr2GmsFKAcFVAl6CCTMXT7GKVRi81yfiENa4q4dnx3TM6Ji9gUoqeFqx07y_YqJ4bsXroprH69qRtM4hXYtG6wvsNptkniSxtMwUwQA6HpAvjnt6hbZ9OIJ6Z7_zRi2ILyvqfuh9cxJGXBf9EZ1IN7PY0F81o2PGhJ652XGoxn3vJuWSfiXFZgLBtf76hrxUZNi21rlclJmYztWo25BHNoW5jDLK_rBvIdEEcKCjCe_2nFsMSOqUhseD72HQi6yiA6XixaFuwWteudndRyc4TtPl_oZUHxwwrM3RlrpyL5_k3ka3z2WjCQ_ZIuwAxLDvuxp-fJobYnz-Ui4dM-VGDSp0CqUg24qtVxBdQvoxdb3yibIrij_RkjofM-pAkkG0d_D_WV "Class Diagram")

![Sequence Diagram](http://your-plantuml-server-url.com/plantuml/svg/ZP71Ri8m38RlUGeVXpJs01mGY5sG1ZjKF83dU9MtDEaIXuaz_LAAHe4YxP8uzl_zJfn5KeoQ6mSLVsNsnF0iM4TiJFeKtsA9eDA3OqWQXJhfi7A2DfmIrhnqKlk6lGALXQCnI1eYx5A92w52qc8fhuBN69oxIc_2NlX5ajvcZUbCO3eVi3DOMWjR_kubiFPjLdEkT-fZPGQhoAWyqBw-VJ2fwG_JAzkXv8JQST-r5nLqyiENq8F7XXyXKOXbOwMdoNzwdUr6-tMZwHzzGMc0uz3-D7U14UNeTGh15gfCL3xsFJjtC6R1tfRV_mK0 "Sequence Diagram")

![Package diagram](http://your-plantuml-server-url.com/plantuml/svg/RP1H3eCW38QVrrFq11nXyGYxGeD8q046mPWitdsjMmQQySRNZ_wbtP8fvUAV-20zaJNOExA9F82EVex1X8mw9eClkCrVnSVL6INqoiOb1jW1ge5AtTjYqzRodTl-TAaVkhs8nR66VCvXnSdRgesqqh39e9KD6t-DoLvZk9k4VEmp4_kYDQF_uSSE03eJUjxX6m00 "Package diagram")

## Architecture

The architecture of the project is described in the diagram above. The project can be found within the folder movielist, where the two main dividers are "core" and "ui". Classes like the Movie-object, MovieList-object, as well as the file handler can be found here. The "ui"-folder includes the App and AppController. The json-file where the data is stored can also be located here. Tests for each of the packages can be found in their respective folder, for example ui/src/main/test/java/ui for "ui". Documentation is to be found in the docs-package, whereas the screenshots of the application is in the "assets"-folder.

## Work habits

The group has experienced a steep learning curve since submission 1, and in this release, er extensively practiced the use of merge requests and issues on GitLab. Coding tasks served as the foundation for all work, and issues were created for every action. A milestone related to the submission was established, where all issues associated with release 1 were linked. Branches consolidated the work for each coding task, and all merge requests were reviewed and approved by another group member before merging. As we worked in pairs, alternating coding responsibilities were documented in commit messages. Due to the substantial amount of work that needed to complete within a short timeframe, there were limited opportunities to divide tasks in this particular release. The group has many ideas for further developing the application but chose to focus on becoming proficient in GitLab and forming proper work habits in this release. Consequently, the workflow progressed steadily, with a focus on ensuring quality every step of the way.

In release 2, we expanded with two additional user stories for the two major new functionalities in this release. No further expansions were made, to avoid compromising quality. The group ensured that everything was in order before enhancing the application with these two new features. As we noticed that adding new functions eventually required substantial effort, we made every effort to prevent one part of the architecture from significantly outpacing another.