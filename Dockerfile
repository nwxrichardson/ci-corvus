FROM eclipse-temurin:21

COPY ./test ./test

RUN cd test \
&& tar -xvzf uk.ac.york.ci.corvus.product-linux.gtk.x86_64.tar.gz

RUN cd test/corvus.product_1.0.0.202510211215 && ls \
&& chmod +x eclipse


ENTRYPOINT [ "/test/corvus.product_1.0.0.202510211215/eclipse" ]
