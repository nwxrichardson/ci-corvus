FROM eclipse-temurin:21

COPY ./test ./test

RUN cd test \
&& tar -xvzf ./test/uk.ac.york.ci.corvus.product-linux.gtk.x86_64.tar.gz

ENTRYPOINT [ "/docker-entrypoint.sh" ]
