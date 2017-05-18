FROM maven:3.5-jdk-8
MAINTAINER EaroLau <earo.lau@outlook.com>

VOLUME /tmp
WORKDIR /root/workspace

# install git
RUN apt-get update && apt-get install git-all

RUN git clone "git@github.com:earo-Lau/SpringBootDemo.git" ;\
    cd SpringBootDemo ;\
    git config --global user.email "earo.lau@outlook.com" ;\
	git config --global user.name "earo-Lau" ;\
    mvn clean install

ENTRYPOINT ["/root/workspace/SpringBootDemo/target"]
CMD [ "java", "-jar", "spring-boot-demo-0.0.1-SNAPSHOT.jar" ]