package voglet;

import com.sun.awt.AWTUtilities;
import java.io.File;
import java.util.TimerTask;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author michael
 */
public class Starter extends TimerTask {

    private static boolean running = false;

    public void run() {
        System.out.println("run");
        //if ( !running )
        {
            running = true;

            Serializer serializer = new Persister();
            File f = new File("test.xml");

            try {
                // read from xml
                VogletManager manager = serializer.read(VogletManager.class, f);
                // create new window with set
                GuiMain window = new GuiMain(manager.getSetByRandom());
                if (AWTUtilities.isTranslucencySupported(AWTUtilities.Translucency.TRANSLUCENT)) {
                    AWTUtilities.setWindowOpacity(window, manager.getConf().getOpacity());
                }
                window.setVisible(true);

                // show me all entries
                for (int i = 0; i < manager.getSet("spanisch").getEntryCount(); i++) {
                    Entry e = manager.getSet("spanisch").getEntryByIndex(i);
                    System.out.println(e.toString() + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
