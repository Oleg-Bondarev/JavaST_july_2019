package main;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        /*try {
            JAXBContext jc = JAXBContext.newInstance(Flowers.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader("data/flowers2.xml");
            Flowers flowers = (Flowers) u.unmarshal(reader);
            System.out.println(flowers.getContent());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
