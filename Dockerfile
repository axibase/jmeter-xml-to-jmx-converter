FROM debian:stretch
#FROM debian:xenial

#metadata
LABEL com.axibase.vendor="Axibase Corporation" \
  com.axibase.product="Apache Jmeter" \
  com.axibase.code="JMETER"


WORKDIR /opt/axibase

#install dependencies
RUN apt-get clean && apt-get update && apt-get install -y netcat wget git openjfx openjdk-8-jdk ant

#download jmeter and jmeter converter
RUN git clone https://github.com/axibase/jmeter-xml-to-jmx-converter.git -b master --depth 1
RUN git clone https://github.com/axibase/jmeter.git -b trunk --depth 1

#build jmeter with ant, download ATSD JDBC driver
WORKDIR /opt/axibase/jmeter
RUN ant download_jars  && ant install

#compile jmeter converter
WORKDIR /opt/axibase/jmeter-xml-to-jmx-converter/src

RUN javac com/axibase/jmeter/Main.java

RUN wget -qO /opt/axibase/jmeter/lib/atsd-jdbc-1.4.7-DEPS.jar https://github.com/axibase/atsd-jdbc/releases/download/RELEASE-1.4.7/atsd-jdbc-1.4.7-DEPS.jar

ADD start.sh /start.sh
RUN chmod +x /start.sh

ENTRYPOINT ["/start.sh"]
