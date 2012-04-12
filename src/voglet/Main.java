package voglet;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/*
<pre>
* {@code
* public List<Object> getObjects() {
*    return objects;
* }
* </pre>
*/

/**
 * Main Class
 * @author michael
  */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //createExampleFile();
	//String s[] = {"--info", "--file", "test.xml", "--show", "-l", "spanisch", "--id", "2"};//###todo richtige nummer?!
	//String s[] = {"-i","--file", "test.xml", "-l", "naviisch", "-a", "-w", "testWort", "-t", "testTranslation"};
	String s[] = {"--info", "--file", "test.xml", "--show", "-l", "naviisch", "--id", "2"};//###todo richtige nummer?!
	//String s[] = {""};
        Console c = new Console(s);
    }

    private static void test() {
	Serializer serializer = new Persister();
	File f = new File("test.xml");
	GuiMain window;

	try {
	    // read from xml
	    VogletManager manager = serializer.read(VogletManager.class, f);
	    // create new window with set
	    window = new GuiMain(manager.getSetByRandom());
	    window.setVisible(true);

	    // show me all entries
	    for (int i=0; i<manager.getSet("spanisch").getEntryCount(); i++ )
	    {
		Entry e = manager.getSet("spanisch").getEntryByIndex(i);
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

	    set = new VocabularySet("esperanto");
            set.addEntry("domo", "haus", "gebaeude");
	    set.addEntry("kuri", "rennen", "aktivität");
	    man.addSet(set);

            serializer.write(man, f);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
