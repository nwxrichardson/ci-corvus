FROM eclipse-temurin:21

COPY /test /test

RUN tar -xvzf uk.ac.york.ci.corvus.product-linux.gtk.x86_64.tar.gz

ENTRYPOINT [ "/docker-entrypoint.sh" ]
