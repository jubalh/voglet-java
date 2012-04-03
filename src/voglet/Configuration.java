package voglet;

import org.simpleframework.xml.Element;


/**
 *
 * @author michael
 */
public class Configuration {
    @Element
    private String version;
    @Element
    private int timerIntervall;
    @Element
    private float opacity;
    //abfrage richtung etc...

    public Configuration() {
        this.version = "0.1";
        this.opacity = 0.9f;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public int getTimerIntervall() {
        return timerIntervall;
    }

    public void setTimerIntervall(int timerIntervall) {
        this.timerIntervall = timerIntervall;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
