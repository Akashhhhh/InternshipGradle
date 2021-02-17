FROM openjdk:15
COPY . tmp
WORKDIR tmp
CMD ["java","src/main/java/javadictionary/Dictionary.java"]
