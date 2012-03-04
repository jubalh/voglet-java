/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package voglet;

import java.io.File;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author michael
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Serializer serializer = new Persister();
        File f = new File("test.xml");

        VocManager manager = new VocManager();
        /*
        Entry e = new Entry("wordtext", "translationtext", "commenttext");
        Entry e2 = new Entry("casa", "haus", "gebaeude");
        manager.addEntry(e);
        manager.addEntry(e2);
        */
        try{

        //serializer.write(manager, f);
        manager = serializer.read(VocManager.class, f);
        for (int i=0; i<manager.getEntryCount(); i++ )
        {
            Entry e = manager.getRandomEntryByIndex(i);
            System.out.println(e.toString()+"\n");
        }

        }catch(Exception ex){
            ex.printStackTrace();
        }


    }

}
