# Challenges for the group

## 1. Eclipse Che

_Description:_ 

Like many groups, we faced challenges running the application in Eclipse Che. Although we set up the workspace with all code, it failed to compile, which was surprising as it worked in our local IDEs. A persistent error was “Fatal error compiling: invalid target release: 17.” Extensive debugging revealed that Che defaulted to Java 11, and we couldn't upgrade to Java 17 without admin rights. To clarify, at the deadline, the project didn't run in Eclipse Che, but it did in VSCode.

_Strategy to solve the issue:_

To fix the bug, we meticulously searched Piazza discussions and StackOverflow, while thoroughly reviewing the code. Despite our collaborative efforts and sharing findings during debugging, we had no luck. 

_Reflection:_ 

We discovered the solution around lunchtime the next day, based on two errors: Firstly, the pom.xml file's configuration for Springboot incorrectly specified moving up two directories (../..) instead of one (..) under parent-relative path, which was puzzling since it worked locally. Secondly, in the movie-diary's JSON file, a movie title with an apostrophe, specifically “Schindler’s List,” didn't compile correctly to “Schindler\u0027s List” in the Che workspace due to the lack of extensions.

Understanding that you evaluate solutions in Che to minimize local downloads, we've included the bug fix in the challenges.md commit for smoother review in Che. However, we understand that grading should reflect the project's state at the deadline. We trust our transparency here will be appreciated.

## 2. Sickness 

_Description:_

Throughout the semester, our group faced a significant challenge as multiple group members fell ill. This was especially prevalant in the crucial final weeks leading up to the deadline. The unexpected issue certainly added an extra layer of complexity to our academic workload, making it much harder to complete all necessary tasks. 

_The effects of this situation:_

The health challenges within our group led to an extremely stressful workload the final days before submission, causing an immense pressure towards the deadline. Despite our strategic efforts, the intensity of the situation resulted in some tasks being left unfinished. This experience emphasized the need for proactive planning, and highlighted the importance of resilience in facing unforeseen challenges.

Due to the prevalence of illness, certain members also had to shoulder a disproportionate workload, working significantly more than others in the group to compensate. This was also a big challenge, as the project became too big for the members that were able to work.