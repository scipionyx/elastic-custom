# elastic-custom

https://search-guard.com/company/

http://david.pilato.fr/blog/2016/07/27/creating-a-plugin-for-elasticsearch-5-dot-0-using-maven/

http://david.pilato.fr/blog/2016/10/19/adding-a-new-rest-endpoint-to-elasticsearch-updated-for-ga/

https://amsterdam.luminis.eu/2017/01/31/creating-elasticsearch-plugin-basics/

https://github.com/sscarduzio/elasticsearch-readonlyrest-plugin/blob/master/es63x/src/main/java/tech/beshu/ror/es/security/RoleIndexSearcherWrapper.java


# Jar Hell
In order to run tests directly from IDEA 2017.2 and above, it is required to disable the IDEA run launcher in order to avoid idea_rt.jar causing "jar hell". 
This can be achieved by adding the ``-Didea.no.launcher=true`` JVM option. 

Alternatively, 
``idea.no.launcher=true`` can be set in the idea.properties file which can be accessed under Help > Edit Custom Properties (this will require a restart of IDEA). 

For IDEA 2017.3 and above, in addition to the JVM option, you will need to go to Run->Edit Configurations->...->Defaults->JUnit and verify that the Shorten command line setting is set to user-local default: none. 
You may also need to remove ant-javafx.jar from your classpath if that is reported as a source of jar hell.

To run an instance of <b>Elasticsearch</b> from the source code run ./gradlew run
The Elasticsearch codebase makes heavy use of Java asserts and the test runner requires that assertions be enabled within the JVM. This can be accomplished by passing the flag ``-ea`` to the JVM on startup.
For IntelliJ, go to Run->Edit Configurations...->Defaults->JUnit->VM options and input ``-ea``.

https://github.com/elastic/elasticsearch/blob/master/CONTRIBUTING.md

# Elasticsearch API

https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-index.html#java-docs-index-generate-helpers

