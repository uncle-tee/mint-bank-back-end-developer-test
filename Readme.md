This code base is for the mint bank back end developer test.   

The project was implemented using a monolithic multi module spring boot project.

You will need docker compose to run the project.

-  CD into the project terminal and run `./gradlew build .` 
-  Run `docker compose up -d (This will containerize  kafka, zookeeper and postgres)
-  Run the build jar using ` "java","-jar", "developertest-0.0.1.jar"`, 
this will run the jar on the default port 8080

If you will like to see publish and subscription  in action, you can run them on separate ports using.

Producer - `java","-jar", "developertest-0.0.1.jar –server.port=8085`
Consumer - `java","-jar", "developertest-0.0.1.jar –server.port=9090`

With the above, Producer will run on port 8085 and the message will be published to port 9090.
Upon publishing message is cached.  

**Note**

If you are curious about what is really happening under the hood you can use `docker compose logs -f` to see logs.


**Todo.**

- Use Redis for caching to allow high performance read and write. 
Currently postgres is used to cache the data, although this scales very well since there is not likely to be many request.

- Run the main jar also in a container 


