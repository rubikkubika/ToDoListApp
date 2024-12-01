FROM maven:3.8.3-openjdk-17
COPY src ToDoListApp/app/src
COPY pom.xml ToDoListApp/app
RUN mvn -f ToDoListApp/app/pom.xml clean package -Dmaven.test.skip
RUN cp ToDoListApp/app/target/*.jar todolistapp.jar
ENTRYPOINT java -jar todolistapp.jar