FROM maven:3.5-jdk-8
MAINTAINER EaroLau <earo.lau@outlook.com>

VOLUME /tmp
WORKDIR /root/workspace

# install git
RUN apt-get update -yqq && apt-get install -y git-all

# copy source
RUN git clone "https://github.com/earo-Lau/SpringBootDemo.git" ;\
    cd SpringBootDemo ;\
    git config --global user.email "earo.lau@outlook.com" ;\
	git config --global user.name "earo-Lau" ;\
	mvn package -Dmaven.test.skip=true

# set entry point
ENTRYPOINT ["/root/workspace/SpringBootDemo/target"]
EXPOSE 8088
CMD [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "spring-boot-demo-0.0.1-SNAPSHOT.jar" ]