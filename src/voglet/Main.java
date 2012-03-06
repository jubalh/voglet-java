package voglet;

import com.sun.awt.AWTUtilities;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author michael
 * TODO:
 * [ ] fenster mit timer aufrufen
 *     https://encrypted.google.com/#hl=de&sclient=psy-ab&q=java+scheduledexecutorservice&pbx=1&oq=JAVA+SCHEDU&aq=1&aqi=g4&aql=&gs_sm=3&gs_upl=25224l25856l1l28727l6l5l0l1l1l0l137l591l1.4l6l0&gs_l=hp.3.1.0l4.25224l25856l1l28727l6l5l0l1l1l0l137l591l1j4l6l0&bav=on.2,or.r_gc.r_pw.r_qf.,cf.osb&fp=14438979447bcb8e&biw=1429&bih=1042
 * [ ] fenster per tastatur steuerbar
 * [ ] fenster schön machen
 *     http://today.java.net/pub/a/today/2008/03/18/translucent-and-shaped-swing-windows.html
 * [ ] Entry: word und translation and arraylist
 * [ ] GUI show all translations
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createExampleFile();
        
        Serializer serializer = new Persister();
        File f = new File("test.xml");

        try {
            // read from xml
            VogletManager manager = serializer.read(VogletManager.class, f);
            // create new window with set
            GuiMain window = new GuiMain(manager.getSetByRandom());
            AWTUtilities.setWindowOpacity( window, manager.getConf().getOpacity() );
            //window.pack();
            window.setVisible(true);
            
            // show me all entries
            for (int i=0; i<manager.getSet("spanisch").getEntryCount(); i++ )
            {
                Entry e = manager.getSet("spanisch").getRandomEntryByIndex(i);
                System.out.println(e.toString()+"\n");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    static private void createExampleFile() {
        try {
            Serializer serializer = new Persister();
            File f = new File("test.xml");
            VogletManager man = new VogletManager();
            man.setConfiguration(new Configuration());
            VocabularySet set = new VocabularySet("spanisch");
            set.addEntry("casa", "haus", "gebaeude");
            set.addEntry("roja", "rot", "farbe");
            set.addEntry("cabeza", "der Kopf", "Körperteil");
            man.addSet(set);
            serializer.write(man, f);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
