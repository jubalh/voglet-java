package voglet;

import org.simpleframework.xml.Element;

/**
 *
 * @author michael
 */
public class ConfigurationManager {

    @Element
    private String version;
    @Element
    private int timerIntervall;
    //abfrage richtung etc...

    public ConfigurationManager() {
        this.version = "0.1";
    }
}
