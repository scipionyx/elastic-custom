FROM docker.elastic.co/elasticsearch/elasticsearch:6.5.1

COPY plugin-1.0-SNAPSHOT.zip /
RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install file:///plugin-1.0-SNAPSHOT.zip