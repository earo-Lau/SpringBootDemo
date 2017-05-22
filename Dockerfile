FROM maven:3.5-jdk-8
MAINTAINER EaroLau <earo.lau@outlook.com>

VOLUME /tmp
VOLUME ${PWD}/workspace
WORKDIR ${PWD}/workspace

# install git
RUN apt-get update -yqq && apt-get install -y git-all

# copy source
RUN git clone "https://github.com/earo-Lau/SpringBootDemo.git" ;\
    cd SpringBootDemo ;\
    git config --global user.email "earo.lau@outlook.com" ;\
	git config --global user.name "earo-Lau" ;

# set entry point
WORKDIR SpringBootDemo
EXPOSE 8088
ENTRYPOINT[ "mvn", "package" ]
CMD [ "-Dmaven.test.skip=true" ]