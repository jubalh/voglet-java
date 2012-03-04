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

        VogletManager man = new VogletManager();
        man.setConfiguration(new ConfigurationManager());
        VocabularySet set = new VocabularySet("spanisch");
        set.addEntry("casa", "haus", "gebaeude");
        set.addEntry("roja", "rot", "farbe");
        man.addSet(set);
        
        try{

        serializer.write(man, f);
        /*manager = serializer.read(VocManager.class, f);
        for (int i=0; i<manager.getEntryCount(); i++ )
        {
            Entry e = manager.getRandomEntryByIndex(i);
            System.out.println(e.toString()+"\n");
        }*/

        }catch(Exception ex){
            ex.printStackTrace();
        }


    }

}
