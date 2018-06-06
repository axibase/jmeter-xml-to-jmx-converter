#!/bin/bash
cd /opt/axibase/tmp/

#download fresh copy of a .xml file with test-plan
rm -rf jmeter-xml-to-jmx-converter
git clone https://github.com/axibase/jmeter-xml-to-jmx-converter.git -b $1

#make .jmx file from .xml
mv jmeter-xml-to-jmx-converter/input.xml /opt/axibase/input.xml
cd /opt/axibase/jmeter-xml-to-jmx-converter/src
java com.axibase.jmeter.Main /opt/axibase/input.xml /opt/axibase/tmp/credentials/config.properties

#reporting to atsd that container is still up
#while true; do echo -e "series e:hbs.axibase.com m:jmeter.container.up=1" > /dev/tcp/hbs.axibase.com/9081; sleep 5m; done &

#launch jmeter with freshly biult .jmx test plan
#also write logs to /opt/axibase/tmp/
/opt/axibase/jmeter/bin/jmeter -n -t /opt/axibase/jmeter-xml-to-jmx-converter/src/test.jmx -j /opt/axibase/tmp/credentials/jmeter.log

#write short version of log
cd /opt/axibase/tmp/credentials/ 
grep -P "Thread (started|finished).*Main requests" jmeter.log > short.log