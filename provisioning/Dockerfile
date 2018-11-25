FROM docker.elastic.co/elasticsearch/elasticsearch:6.5.1

COPY plugin-1.0-SNAPSHOT.zip /
RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install file:///plugin-1.0-SNAPSHOT.zip

EXPOSE 9200:9200
EXPOSE 9300:9300