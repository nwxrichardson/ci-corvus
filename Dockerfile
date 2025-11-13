FROM eclipse-temurin:21

COPY ./test ./test

RUN cd test \
&& tar -xvzf uk.ac.york.ci.corvus.product-linux.gtk.x86_64.tar.gz

RUN cd test/corvus.product_1.0.0 && ls \
&& chmod +x eclipse

RUN apt-get update && apt-get install -y --no-install-recommends \
  bzr \
  cvs \
  git \
  mercurial \
  subversion \
  && rm -rf /var/lib/apt/lists/*
  
 RUN apt update && apt-get install dos2unix
  
 COPY ./docker-entrypoint.sh ./docker-entrypoint.sh
 RUN dos2unix ./docker-entrypoint.sh && chmod +x ./docker-entrypoint.sh

ENTRYPOINT [ "./docker-entrypoint.sh" ]

