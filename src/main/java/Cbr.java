import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;


public class Cbr {

    /**
     * My bad, на самом деле, так и не получилось разобраться, почему у меня в доке 43 null Node получается...
     * Вроде, он не пустой, раз их 43, но все почему-то пустые выходят....
     * Никогда, увы не работал с XML хотя бы на подобном уровне :(
     * В остальном, вроде, сделал как требовалось...
     * Хотя это не сильно спасает ситуацию :(
     * Был бы супер благодарен, если кто-то подскажет, в чем у меня тут косяк...
     * Заранее спасибо)))
     *
     */
    public void getInfo(String code, String date) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date).openStream());


            Node root = doc.getDocumentElement();
            NodeList currencies = root.getChildNodes();

            Node neededCur;
            String name = "";
            String value = "";

            for (int i = 0; i < currencies.getLength(); i++) {
                neededCur = currencies.item(i);
                NodeList currencySpecs = neededCur.getChildNodes();
                if (String.valueOf(currencySpecs.item(1)).equals(code)) {
                    name = String.valueOf(currencySpecs.item(3));
                    value = String.valueOf(currencySpecs.item(4));
                    break;
                }
            }

            System.out.println(code + " (" + name + "): " + value);


        } catch (ParserConfigurationException | IOException | SAXException exception) {
            exception.printStackTrace();
        }

    }



}
