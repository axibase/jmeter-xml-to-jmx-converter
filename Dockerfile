FROM openjdk:8

#metadata
LABEL com.axibase.vendor="Axibase Corporation" \
  com.axibase.product="Apache Jmeter" \
  com.axibase.code="JMETER"


WORKDIR /opt/axibase

#install dependencies
RUN apt-get clean && apt-get update && apt-get install -y \
      ant \
      git \
      netcat \
      openjfx 
#      wget

#download jmeter and jmeter converter
RUN git clone https://github.com/axibase/jmeter-xml-to-jmx-converter.git -b master \
 && git clone https://github.com/axibase/jmeter.git -b trunk

#compile jmeter converter
WORKDIR /opt/axibase/jmeter-xml-to-jmx-converter/src
RUN javac com/axibase/jmeter/Main.java

#build jmeter with ant, download ATSD JDBC driver
WORKDIR /opt/axibase/jmeter
RUN ant download_jars \
 && ant install 
# && wget -qO /opt/axibase/jmeter/lib/atsd-jdbc-1.2.17-DEPS.jar https://github.com/axibase/atsd-jdbc/releases/download/RELEASE-1.2.17/atsd-jdbc-1.2.17-DEPS.jar

COPY atsd-jdbc-1.3.0-SNAPSHOT-DEPS.jar /opt/axibase/jmeter/lib/

ADD start.sh /start.sh
RUN chmod +x /start.sh

ENTRYPOINT ["/start.sh"]