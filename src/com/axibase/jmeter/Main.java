package com.axibase.jmeter;

import java.io.*;
import java.net.URL;
import java.util.Properties;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Main {
    private static String protocol;
    private static String atsdStoreUrl;
    private static String atsdUrlAddress;
    private static String port;
    private static String TCPport;
    private static String username;
    private static String password;
    private static NodeList SQLQueries;
    private static NodeList dataQueries;

    private static String formJMX() {

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<jmeterTestPlan version=\"1.2\" properties=\"3.2\" jmeter=\"3.2-SNAPSHOT.20161128\">\n" +
                "  <hashTree>\n" +
                "    <TestPlan guiclass=\"TestPlanGui\" testclass=\"TestPlan\" testname=\"Test Plan\" enabled=\"true\">\n" +
                "      <stringProp name=\"TestPlan.comments\"></stringProp>\n" +
                "      <boolProp name=\"TestPlan.functional_mode\">false</boolProp>\n" +
                "      <boolProp name=\"TestPlan.serialize_threadgroups\">true</boolProp>\n" +
                "      <elementProp name=\"TestPlan.user_defined_variables\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" testname=\"User Defined Variables\" enabled=\"true\">\n" +
                "        <collectionProp name=\"Arguments.arguments\">\n" +
                "          <elementProp name=\"atsdURL\" elementType=\"Argument\">\n" +
                "            <stringProp name=\"Argument.name\">atsdURL</stringProp>\n" +
                "            <stringProp name=\"Argument.value\">");
        sb.append(substituteSpecialSymbols(atsdUrlAddress));
        sb.append("</stringProp>\n" +
                "            <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "          </elementProp>\n" +
                "          <elementProp name=\"port\" elementType=\"Argument\">\n" +
                "            <stringProp name=\"Argument.name\">port</stringProp>\n" +
                "            <stringProp name=\"Argument.value\">");
        sb.append(substituteSpecialSymbols(port));
        sb.append("</stringProp>\n" +
                "            <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "          </elementProp>\n" +
                "          <elementProp name=\"TCPport\" elementType=\"Argument\">\n" +
                "            <stringProp name=\"Argument.name\">TCPport</stringProp>\n" +
                "            <stringProp name=\"Argument.value\">");
        sb.append(substituteSpecialSymbols(TCPport));
        sb.append("</stringProp>\n" +
                "            <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "          </elementProp>\n" +
                "          <elementProp name=\"protocol\" elementType=\"Argument\">\n" +
                "            <stringProp name=\"Argument.name\">protocol</stringProp>\n" +
                "            <stringProp name=\"Argument.value\">");
        sb.append(substituteSpecialSymbols(protocol));
        sb.append("</stringProp>\n" +
                "            <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "          </elementProp>\n" +
                "        </collectionProp>\n" +
                "      </elementProp>\n" +
                "      <stringProp name=\"TestPlan.user_define_classpath\"></stringProp>\n" +
                "    </TestPlan>\n" +
                "    <hashTree>\n" +
                "      <JDBCDataSource guiclass=\"TestBeanGUI\" testclass=\"JDBCDataSource\" testname=\"JDBC Connection to ATSD Configuration\" enabled=\"true\">\n" +
                "        <boolProp name=\"autocommit\">true</boolProp>\n" +
                "        <stringProp name=\"checkQuery\">SELECT 1</stringProp>\n" +
                "        <stringProp name=\"connectionAge\">5000</stringProp>\n" +
                "        <stringProp name=\"dataSource\">atsd</stringProp>\n" +
                "        <stringProp name=\"dbUrl\">jdbc:axibase:atsd:${protocol}://${atsdURL}:${port}/api/sql;trustServerCertificate=true</stringProp>\n" +
                "        <stringProp name=\"driver\">com.axibase.tsd.driver.jdbc.AtsdDriver</stringProp>\n" +
                "        <boolProp name=\"keepAlive\">true</boolProp>\n" +
                "        <stringProp name=\"password\">");
        sb.append(substituteSpecialSymbols(password));
        sb.append("</stringProp>\n" +
                "        <stringProp name=\"poolMax\">10</stringProp>\n" +
                "        <stringProp name=\"timeout\">10000</stringProp>\n" +
                "        <stringProp name=\"transactionIsolation\">DEFAULT</stringProp>\n" +
                "        <stringProp name=\"trimInterval\">60000</stringProp>\n" +
                "        <stringProp name=\"username\">");
        sb.append(substituteSpecialSymbols(username));
        sb.append("</stringProp>\n" +
                "      </JDBCDataSource>\n" +
                "      <hashTree/>");



        for (int temp = 0; temp < SQLQueries.getLength(); temp++) {
            Node nNode = SQLQueries.item(temp);

            sb.append(" <ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"Ramp-Up ");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
            }
            sb.append(" thread\" enabled=\"true\">\n" +
                    "        <stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n" +
                    "        <elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"Loop Controller\" enabled=\"true\">\n" +
                    "          <boolProp name=\"LoopController.continue_forever\">false</boolProp>\n" +
                    "          <stringProp name=\"LoopController.loops\">1</stringProp>\n" +
                    "        </elementProp>\n" +
                    "        <stringProp name=\"ThreadGroup.num_threads\">1</stringProp>\n" +
                    "        <stringProp name=\"ThreadGroup.ramp_time\">0</stringProp>\n" +
                    "        <longProp name=\"ThreadGroup.start_time\">1479801071000</longProp>\n" +
                    "        <longProp name=\"ThreadGroup.end_time\">1479801071000</longProp>\n" +
                    "        <boolProp name=\"ThreadGroup.scheduler\">false</boolProp>\n" +
                    "        <stringProp name=\"ThreadGroup.duration\"></stringProp>\n" +
                    "        <stringProp name=\"ThreadGroup.delay\"></stringProp>\n" +
                    "      </ThreadGroup>\n" +
                    "      <hashTree>\n" +
                    "        <JDBCSampler guiclass=\"TestBeanGUI\" testclass=\"JDBCSampler\" testname=\"rampup ");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
            }
            sb.append("\" enabled=\"true\">\n" +
                    "          <stringProp name=\"dataSource\">atsd</stringProp>\n" +
                    "          <stringProp name=\"query\">");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("queryText").item(0).getTextContent()));
            }
            sb.append("</stringProp>\n" +
                    "          <stringProp name=\"queryArguments\"></stringProp>\n" +
                    "          <stringProp name=\"queryArgumentsTypes\"></stringProp>\n" +
                    "          <stringProp name=\"queryTimeout\"></stringProp>\n" +
                    "          <stringProp name=\"queryType\">Select Statement</stringProp>\n" +
                    "          <stringProp name=\"resultSetHandler\">Store as String</stringProp>\n" +
                    "          <stringProp name=\"resultVariable\"></stringProp>\n" +
                    "          <stringProp name=\"variableNames\"></stringProp>\n" +
                    "        </JDBCSampler>\n" +
                    "        <hashTree/>\n" +
                    "      </hashTree>\n" +
                    "      <ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"Main requests ");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
            }
            sb.append(" thread\" enabled=\"true\">\n" +
                    "        <stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n" +
                    "        <elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"Loop Controller\" enabled=\"true\">\n" +
                    "          <boolProp name=\"LoopController.continue_forever\">false</boolProp>\n" +
                    "          <stringProp name=\"LoopController.loops\">");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("loops").item(0).getTextContent()));
            }
            sb.append("</stringProp>\n" +
                    "        </elementProp>\n" +
                    "        <stringProp name=\"ThreadGroup.num_threads\">1</stringProp>\n" +
                    "        <stringProp name=\"ThreadGroup.ramp_time\">0</stringProp>\n" +
                    "        <longProp name=\"ThreadGroup.start_time\">1480059282000</longProp>\n" +
                    "        <longProp name=\"ThreadGroup.end_time\">1480059282000</longProp>\n" +
                    "        <boolProp name=\"ThreadGroup.scheduler\">false</boolProp>\n" +
                    "        <stringProp name=\"ThreadGroup.duration\"></stringProp>\n" +
                    "        <stringProp name=\"ThreadGroup.delay\"></stringProp>\n" +
                    "      </ThreadGroup>\n" +
                    "      <hashTree>\n" +
                    "        <JDBCSampler guiclass=\"TestBeanGUI\" testclass=\"JDBCSampler\" testname=\"");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
            }
            sb.append("\" enabled=\"true\">\n" +
                    "          <stringProp name=\"dataSource\">atsd</stringProp>\n" +
                    "          <stringProp name=\"query\">");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("queryText").item(0).getTextContent()));
            }
            sb.append("</stringProp>\n" +
                    "          <stringProp name=\"queryArguments\"></stringProp>\n" +
                    "          <stringProp name=\"queryArgumentsTypes\"></stringProp>\n" +
                    "          <stringProp name=\"queryTimeout\"></stringProp>\n" +
                    "          <stringProp name=\"queryType\">Select Statement</stringProp>\n" +
                    "          <stringProp name=\"resultSetHandler\">Store as String</stringProp>\n" +
                    "          <stringProp name=\"resultVariable\"></stringProp>\n" +
                    "          <stringProp name=\"variableNames\"></stringProp>\n" +
                    "        </JDBCSampler>\n" +
                    "        <hashTree/>\n" +
                    "      </hashTree>");
        }



        sb.append("<ConfigTestElement guiclass=\"HttpDefaultsGui\" testclass=\"ConfigTestElement\" testname=\"HTTP Request Defaults\" enabled=\"true\">\n" +
                "        <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\" guiclass=\"HTTPArgumentsPanel\" testclass=\"Arguments\" testname=\"User Defined Variables\" enabled=\"true\">\n" +
                "          <collectionProp name=\"Arguments.arguments\"/>\n" +
                "        </elementProp>\n" +
                "        <stringProp name=\"HTTPSampler.domain\">${atsdURL}</stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.port\">${port}</stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.connect_timeout\"></stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.protocol\">${protocol}</stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.path\"></stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.concurrentPool\">6</stringProp>\n" +
                "      </ConfigTestElement>\n" +
                "      <hashTree/>\n" +
                "      <AuthManager guiclass=\"AuthPanel\" testclass=\"AuthManager\" testname=\"HTTP Authorization Manager\" enabled=\"true\">\n" +
                "        <collectionProp name=\"AuthManager.auth_list\">\n" +
                "          <elementProp name=\"\" elementType=\"Authorization\">\n" +
                "            <stringProp name=\"Authorization.url\"></stringProp>\n" +
                "            <stringProp name=\"Authorization.username\">").append(substituteSpecialSymbols(username)).append("</stringProp>\n" +
                "            <stringProp name=\"Authorization.password\">").append(substituteSpecialSymbols(password)).append("</stringProp>\n" +
                "            <stringProp name=\"Authorization.domain\"></stringProp>\n" +
                "            <stringProp name=\"Authorization.realm\"></stringProp>\n" +
                "          </elementProp>\n" +
                "        </collectionProp>\n" +
                "      </AuthManager>\n" +
                "      <hashTree/>");
        sb.append("<HeaderManager guiclass=\"HeaderPanel\" testclass=\"HeaderManager\" testname=\"HTTP Header Manager\" enabled=\"true\">\n" +
                "        <collectionProp name=\"HeaderManager.headers\">\n" +
                "          <elementProp name=\"\" elementType=\"Header\">\n" +
                "            <stringProp name=\"Header.name\">Accept-Encoding</stringProp>\n" +
                "            <stringProp name=\"Header.value\">gzip, deflate, br</stringProp>\n" +
                "          </elementProp>\n" +
                "        </collectionProp>\n" +
                "      </HeaderManager>\n" +
                "      <hashTree/>");

        for (int temp = 0; temp < dataQueries.getLength(); temp++) {
            Element eElement = (Element) dataQueries.item(temp);

            sb.append("      <ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"Ramp-Up ");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
            sb.append(" thread\" enabled=\"true\">\n" +
                    "        <stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n" +
                    "        <elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"Loop Controller\" enabled=\"true\">\n" +
                    "          <boolProp name=\"LoopController.continue_forever\">false</boolProp>\n" +
                    "          <stringProp name=\"LoopController.loops\">1</stringProp>\n" +
                    "        </elementProp>\n" +
                    "        <stringProp name=\"ThreadGroup.num_threads\">1</stringProp>\n" +
                    "        <stringProp name=\"ThreadGroup.ramp_time\">1</stringProp>\n" +
                    "        <longProp name=\"ThreadGroup.start_time\">1487154353000</longProp>\n" +
                    "        <longProp name=\"ThreadGroup.end_time\">1487154353000</longProp>\n" +
                    "        <boolProp name=\"ThreadGroup.scheduler\">false</boolProp>\n" +
                    "        <stringProp name=\"ThreadGroup.duration\"></stringProp>\n" +
                    "        <stringProp name=\"ThreadGroup.delay\"></stringProp>\n" +
                    "      </ThreadGroup>\n" +
                    "      <hashTree>\n" +
                    "        <HTTPSamplerProxy guiclass=\"HttpTestSampleGui\" testclass=\"HTTPSamplerProxy\" testname=\"rampup ");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
            sb.append("\" enabled=\"true\">\n" +
                    "          <boolProp name=\"HTTPSampler.postBodyRaw\">true</boolProp>\n" +
                    "          <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\">\n" +
                    "            <collectionProp name=\"Arguments.arguments\">\n" +
                    "              <elementProp name=\"\" elementType=\"HTTPArgument\">\n" +
                    "                <boolProp name=\"HTTPArgument.always_encode\">false</boolProp>\n" +
                    "                <stringProp name=\"Argument.value\">");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("payload").item(0).getTextContent()));
            sb.append("</stringProp>\n" +
                    "                <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                    "              </elementProp>\n" +
                    "            </collectionProp>\n" +
                    "          </elementProp>\n" +
                    "          <stringProp name=\"HTTPSampler.domain\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.port\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.connect_timeout\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.protocol\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.path\">");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("path").item(0).getTextContent()));
            sb.append("</stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.method\">");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("method").item(0).getTextContent()));
            sb.append("</stringProp>\n" +
                    "          <boolProp name=\"HTTPSampler.follow_redirects\">true</boolProp>\n" +
                    "          <boolProp name=\"HTTPSampler.auto_redirects\">false</boolProp>\n" +
                    "          <boolProp name=\"HTTPSampler.use_keepalive\">true</boolProp>\n" +
                    "          <boolProp name=\"HTTPSampler.DO_MULTIPART_POST\">false</boolProp>\n" +
                    "          <stringProp name=\"HTTPSampler.embedded_url_re\"></stringProp>\n" +
                    "        </HTTPSamplerProxy>\n" +
                    "        <hashTree/>\n" +
                    "      </hashTree>\n" +
                    "      <ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"Main requests ");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
            sb.append(" thread HTTP\" enabled=\"true\">\n" +
                    "        <stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n" +
                    "        <elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"Loop Controller\" enabled=\"true\">\n" +
                    "          <boolProp name=\"LoopController.continue_forever\">false</boolProp>\n" +
                    "          <stringProp name=\"LoopController.loops\">");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("loops").item(0).getTextContent()));
            sb.append("</stringProp>\n" +
                    "        </elementProp>\n" +
                    "        <stringProp name=\"ThreadGroup.num_threads\">1</stringProp>\n" +
                    "        <stringProp name=\"ThreadGroup.ramp_time\">1</stringProp>\n" +
                    "        <longProp name=\"ThreadGroup.start_time\">1487154353000</longProp>\n" +
                    "        <longProp name=\"ThreadGroup.end_time\">1487154353000</longProp>\n" +
                    "        <boolProp name=\"ThreadGroup.scheduler\">false</boolProp>\n" +
                    "        <stringProp name=\"ThreadGroup.duration\"></stringProp>\n" +
                    "        <stringProp name=\"ThreadGroup.delay\"></stringProp>\n" +
                    "      </ThreadGroup>\n" +
                    "      <hashTree>\n" +
                    "        <HTTPSamplerProxy guiclass=\"HttpTestSampleGui\" testclass=\"HTTPSamplerProxy\" testname=\"");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
                    sb.append("\" enabled=\"true\">\n" +
                    "          <boolProp name=\"HTTPSampler.postBodyRaw\">true</boolProp>\n" +
                    "          <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\">\n" +
                    "            <collectionProp name=\"Arguments.arguments\">\n" +
                    "              <elementProp name=\"\" elementType=\"HTTPArgument\">\n" +
                    "                <boolProp name=\"HTTPArgument.always_encode\">false</boolProp>\n" +
                    "                <stringProp name=\"Argument.value\">");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("payload").item(0).getTextContent()));
            sb.append("</stringProp>\n" +
                    "                <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                    "              </elementProp>\n" +
                    "            </collectionProp>\n" +
                    "          </elementProp>\n" +
                    "          <stringProp name=\"HTTPSampler.domain\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.port\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.connect_timeout\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.protocol\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.path\">");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("path").item(0).getTextContent()));
            sb.append("</stringProp>\n" +
                    "          <stringProp name=\"HTTPSampler.method\">");
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("method").item(0).getTextContent()));
            sb.append("</stringProp>\n" +
                    "          <boolProp name=\"HTTPSampler.follow_redirects\">true</boolProp>\n" +
                    "          <boolProp name=\"HTTPSampler.auto_redirects\">false</boolProp>\n" +
                    "          <boolProp name=\"HTTPSampler.use_keepalive\">true</boolProp>\n" +
                    "          <boolProp name=\"HTTPSampler.DO_MULTIPART_POST\">false</boolProp>\n" +
                    "          <stringProp name=\"HTTPSampler.embedded_url_re\"></stringProp>\n" +
                    "        </HTTPSamplerProxy>\n" +
                    "        <hashTree/>\n" +
                    "      </hashTree>");
        }



        sb.append("<BackendListener guiclass=\"BackendListenerGui\" testclass=\"BackendListener\" testname=\"Backend Listener\" enabled=\"true\">\n" +
                "        <elementProp name=\"arguments\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" enabled=\"true\">\n" +
                "          <collectionProp name=\"Arguments.arguments\">\n" +
                "            <elementProp name=\"graphiteMetricsSender\" elementType=\"Argument\">\n" +
                "              <stringProp name=\"Argument.name\">graphiteMetricsSender</stringProp>\n" +
                "              <stringProp name=\"Argument.value\">org.apache.jmeter.visualizers.backend.graphite.TextGraphiteMetricsSender</stringProp>\n" +
                "              <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "            </elementProp>\n" +
                "            <elementProp name=\"graphiteHost\" elementType=\"Argument\">\n" +
                "              <stringProp name=\"Argument.name\">graphiteHost</stringProp>\n" +
                "              <stringProp name=\"Argument.value\">");
        sb.append(substituteSpecialSymbols(atsdStoreUrl));
        sb.append("</stringProp>\n" +
                "              <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "            </elementProp>\n" +
                "            <elementProp name=\"graphitePort\" elementType=\"Argument\">\n" +
                "              <stringProp name=\"Argument.name\">graphitePort</stringProp>\n" +
                "              <stringProp name=\"Argument.value\">${TCPport}</stringProp>\n" +
                "              <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "            </elementProp>\n" +
                "            <elementProp name=\"rootMetricsPrefix\" elementType=\"Argument\">\n" +
                "              <stringProp name=\"Argument.name\">rootMetricsPrefix</stringProp>\n" +
                "              <stringProp name=\"Argument.value\">${atsdURL}.delimiter.</stringProp>\n" +
                "              <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "            </elementProp>\n" +
                "            <elementProp name=\"summaryOnly\" elementType=\"Argument\">\n" +
                "              <stringProp name=\"Argument.name\">summaryOnly</stringProp>\n" +
                "              <stringProp name=\"Argument.value\">false</stringProp>\n" +
                "              <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "            </elementProp>\n" +
                "            <elementProp name=\"samplersList\" elementType=\"Argument\">\n" +
                "              <stringProp name=\"Argument.name\">samplersList</stringProp>\n" +
                "              <stringProp name=\"Argument.value\">");

        String prefix = "";
        for (int temp = 0; temp < SQLQueries.getLength(); temp++) {
            sb.append(prefix);
            prefix = ";";

            Element eElement = (Element) SQLQueries.item(temp);
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
        }

        if (SQLQueries.getLength() == 0) {
            prefix = "";
        }
        for (int temp = 0; temp < dataQueries.getLength(); temp++) {
            sb.append(prefix);
            prefix = ";";

            Element eElement = (Element) dataQueries.item(temp);
            sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
        }

        sb.append("</stringProp>\n" +
                "              <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "            </elementProp>\n" +
                "            <elementProp name=\"percentiles\" elementType=\"Argument\">\n" +
                "              <stringProp name=\"Argument.name\">percentiles</stringProp>\n" +
                "              <stringProp name=\"Argument.value\">50;75;90;95;99</stringProp>\n" +
                "              <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "            </elementProp>\n" +
                "          </collectionProp>\n" +
                "        </elementProp>\n" +
                "        <stringProp name=\"classname\">org.apache.jmeter.visualizers.backend.graphite.GraphiteBackendListenerClient</stringProp>\n" +
                "        <stringProp name=\"QUEUE_SIZE\">1</stringProp>\n" +
                "      </BackendListener>\n" +
                "      <hashTree/>\n" +
                "    </hashTree>\n" +
                "  </hashTree>\n" +
                "</jmeterTestPlan>\n");

        return sb.toString();
    }

    private static String substituteSpecialSymbols(String input) {
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("\'", "&apos;");
    }

    private static void readProperties(String pathToFile) throws IOException {
        Properties prop = new Properties();
        InputStream input = new FileInputStream(pathToFile);
        prop.load(input);

        URL atsdQueryUrl = new URL(prop.getProperty("atsd-query-url"));
        atsdStoreUrl = prop.getProperty("atsd-store-url");
        atsdUrlAddress = atsdQueryUrl.getHost();
        String[] userInfo = atsdQueryUrl.getUserInfo().split(":");
        username = userInfo[0];
        password = userInfo[1];
        protocol = atsdQueryUrl.getProtocol();
        port = Integer.toString(atsdQueryUrl.getPort());
        TCPport = prop.getProperty("atsd-store-port");
        input.close();
    }

    private static void buildQueryLists(String pathToFile) throws IOException,
                                                                  ParserConfigurationException, SAXException {
        File inputFile = new File(pathToFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        SQLQueries = doc.getElementsByTagName("query");
        dataQueries = doc.getElementsByTagName("dataQuery");
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
        if (args.length != 2) {
            System.out.println("Need 2 args -- input filepath, config filepath!");
            return;
        }

        buildQueryLists(args[0]);
        readProperties(args[1]);

        String rawFile = formJMX();
        File file = new File("test.jmx");

        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        Writer writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(rawFile);
        bufferedWriter.close();
    }
}
