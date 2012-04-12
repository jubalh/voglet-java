package voglet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Holds configuration and sets
 * @author michael
 */
@Root
public class VogletManager {
    @Element
    private Configuration conf;
    @ElementList
    private List <VocabularySet> sets;

    /**
     * Constructor
     */
    public VogletManager() {
        this.sets = new ArrayList<VocabularySet>();
    }

    /**
     * @param config configuration
     */
    public void setConfiguration(Configuration config ) {
        this.conf = config;
    }

    /**
     * @return configuration
     */
    public Configuration getConf() {
        return conf;
    }

    /**
     * @param set set to be added
     */
    public void addSet(VocabularySet set) {
        this.sets.add(set);
    }

    /**
     * @param language
     * @return returns set with identifier LANGUAGE
     */
    public VocabularySet getSet(String language) {
        VocabularySet set = null;
        for (int i=0; i<this.sets.size(); i++) {
            if ( this.sets.get(i).getLanguage().equals(language) )
            {
                set = this.sets.get(i);
            }
        }
        return set;
    }

    /**
     * @return List with sets
     */
    public List<VocabularySet> getSetAll() {
	    return this.sets;
    }

    /**
     * @return random set
     */
    public VocabularySet getSetByRandom() {
        int index = new Random().nextInt(this.sets.size());
        return this.sets.get(index);
    }

    /**
     * @return List with identifier of all sets
     */
    public List getSetNames() {
        List<String> l = new ArrayList<String>();
	//TODO: is this better?
	// for (int i=0, c= this.sets.size(); i<c; i++) {
        for (int i=0; i<this.sets.size(); i++) {
            l.add(this.sets.get(i).getLanguage());
        }
        return l;
    }

    /**
     * @return number of sets
     */
    public Integer getSetCount() {
	    return this.sets.size();
    }
}
