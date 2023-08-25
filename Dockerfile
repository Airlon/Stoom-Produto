FROM openjdk
WORKDIR /app

COPY target/produto.jar /app/produto.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "investimento.jar"]