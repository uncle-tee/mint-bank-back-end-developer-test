This is code base for the mint bank back end developer test.   

The project was implemented using a monolitic multi module spring boot project.

You will need docker compose to run the project.

-  CD into the project terminal and run `./gradlew build.`
-  Create a docker image out of this using `docker build -t developertest` . (Make sure you are in the directory).
- Run `docker compose up -d
- That is pretty much all. Docker compose is currently running on port 8080. So you may have to free that port.


**Note**

If you are curious about what is really happening under the hood you can use `docker compose logs -f` to see logs.


**Todo.**

Use Redis for caching to allow high perfomance read and write. Currently postgres is used to cache the data, although this scales very well since there is not likely to be many request.


