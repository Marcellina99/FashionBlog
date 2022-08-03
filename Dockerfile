FROM openjdk:17
ADD target/fashion-blog-api.jar fashion-blog-api.jar
ENTRYPOINT ["java", "-jar", "/fashion-blog-api.jar"]