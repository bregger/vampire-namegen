FROM clojure:alpine
WORKDIR /usr/app

COPY project.clj .
RUN lein deps
COPY . .
RUN lein uberjar \
    && mv ./target/vampire-web.jar .

ENV PORT 9000
EXPOSE 9000
CMD ["java", "-jar", "vampire-web.jar"]