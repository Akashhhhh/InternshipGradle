FROM openjdk:15
COPY . build/classes/java/main/javadictionary
WORKDIR build/classes/java/main/javadictionary

RUN javac src/main/java/javadictionary/Dictionary.java
CMD ["java","src/main/java/javadictionary/Dictionary.java"]
