FROM eclipse-temurin:21

COPY /test /test

RUN tar -xvzf community_images.tar.gz

ENTRYPOINT [ "/docker-entrypoint.sh" ]
