FROM openjdk:15
COPY . tmp
WORKDIR tmp

RUN javac src/main/java/javadictionary/Dictionary.java
CMD ["java","src/main/java/javadictionary/Dictionary.java"]
