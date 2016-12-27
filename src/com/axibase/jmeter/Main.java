package com.axibase.jmeter;

import java.io.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Main {

    public static String formJMX (String atsdURL, String port, String TCPport, String protocol, String username, String password, NodeList tests){

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
        sb.append(substituteSpecialSymbols(atsdURL));
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
                "        <stringProp name=\"checkQuery\">SELECT value FROM mpstat.cpu_busy\n LIMIT 1</stringProp>\n" +
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



        for (int temp = 0; temp < tests.getLength(); temp++) {
            Node nNode = tests.item(temp);

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
                "              <stringProp name=\"Argument.value\">${atsdURL}</stringProp>\n" +
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

        for (int temp = 0; temp < tests.getLength(); temp++) {
            sb.append(prefix);
            prefix = ";";

            Node nNode = tests.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                sb.append(substituteSpecialSymbols(eElement.getElementsByTagName("testID").item(0).getTextContent()).toLowerCase());
            }
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
                "      <ResultCollector guiclass=\"SummaryReport\" testclass=\"ResultCollector\" testname=\"Summary Report\" enabled=\"true\">\n" +
                "        <boolProp name=\"ResultCollector.error_logging\">false</boolProp>\n" +
                "        <objProp>\n" +
                "          <name>saveConfig</name>\n" +
                "          <value class=\"SampleSaveConfiguration\">\n" +
                "            <time>true</time>\n" +
                "            <latency>true</latency>\n" +
                "            <timestamp>true</timestamp>\n" +
                "            <success>true</success>\n" +
                "            <label>true</label>\n" +
                "            <code>true</code>\n" +
                "            <message>true</message>\n" +
                "            <threadName>true</threadName>\n" +
                "            <dataType>true</dataType>\n" +
                "            <encoding>false</encoding>\n" +
                "            <assertions>true</assertions>\n" +
                "            <subresults>true</subresults>\n" +
                "            <responseData>false</responseData>\n" +
                "            <samplerData>false</samplerData>\n" +
                "            <xml>false</xml>\n" +
                "            <fieldNames>true</fieldNames>\n" +
                "            <responseHeaders>false</responseHeaders>\n" +
                "            <requestHeaders>false</requestHeaders>\n" +
                "            <responseDataOnError>false</responseDataOnError>\n" +
                "            <saveAssertionResultsFailureMessage>true</saveAssertionResultsFailureMessage>\n" +
                "            <assertionsResultsToSave>0</assertionsResultsToSave>\n" +
                "            <bytes>true</bytes>\n" +
                "            <sentBytes>true</sentBytes>\n" +
                "            <threadCounts>true</threadCounts>\n" +
                "            <idleTime>true</idleTime>\n" +
                "            <connectTime>true</connectTime>\n" +
                "          </value>\n" +
                "        </objProp>\n" +
                "        <stringProp name=\"filename\"></stringProp>\n" +
                "      </ResultCollector>\n" +
                "      <hashTree/>\n" +
                "    </hashTree>\n" +
                "  </hashTree>\n" +
                "</jmeterTestPlan>\n");

        return sb.toString();
    }

    public static String substituteSpecialSymbols(String input) {
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("\'", "&apos;");
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
        if (args.length != 2) {
            System.out.println("Need 2 args -- input filepath, credentials filepath!");
            return;
        }

        File inputFile = new File(args[0]);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("query");
        String atsdURL = doc.getElementsByTagName("atsdURL").item(0).getTextContent();
        String port = doc.getElementsByTagName("port").item(0).getTextContent();
        String TCPport = doc.getElementsByTagName("TCPport").item(0).getTextContent();
        String protocol = doc.getElementsByTagName("protocol").item(0).getTextContent();

        FileInputStream credentialsFile = new FileInputStream(args[1]);
        BufferedReader br = new BufferedReader(new InputStreamReader(credentialsFile));
        String username = br.readLine();
        String password = br.readLine();

        String rawFile = formJMX(atsdURL, port, TCPport, protocol, username, password, nList);
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
