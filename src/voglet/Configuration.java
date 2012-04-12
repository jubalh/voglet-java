package voglet;

import org.simpleframework.xml.Element;


/**
 * Configuration for voglet program
 * @author michael
 */
public class Configuration {
    @Element
    private String version;
    @Element
    private int timerIntervall;
    @Element
    private float opacity;
    //TODO: abfrage richtung etc...

    /**
     * Constructor
     */
    public Configuration() {
        this.version = "0.1";
        this.opacity = 0.9f;
    }

    /**
     * @return
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * @param opacity
     */
    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    /**
     * @return interval in which you want to answer questions
     */
    public int getTimerIntervall() {
        return timerIntervall;
    }

    /**
     * @param timerIntervall interval in which you want to answer questions
     */
    public void setTimerIntervall(int timerIntervall) {
        this.timerIntervall = timerIntervall;
    }

    /**
     * @return version of configuration
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version version of configuration
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return information about configuration
     */
    @Override
    public String toString() {
	    StringBuilder s = new StringBuilder();
	    String NEW_LINE = System.getProperty( "line.separator" );

	    s.append( "Version: " + this.getVersion() + NEW_LINE );
	    s.append( "TimerInterval: " + this.getTimerIntervall() + NEW_LINE );
	    s.append( "Opacity: " + this.getOpacity() + NEW_LINE );

	    return s.toString();
    }
}
