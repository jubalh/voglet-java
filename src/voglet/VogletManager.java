package voglet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author michael
 */
@Root
public class VogletManager {
    @Element
    private Configuration conf;
    @ElementList
    private List <VocabularySet> sets;

    public VogletManager() {
        this.sets = new ArrayList<VocabularySet>();
    }

    public void setConfiguration(Configuration config ) {
        this.conf = config;
    }

    public Configuration getConf() {
        return conf;
    }

    public void addSet(VocabularySet set) {
        this.sets.add(set);
    }

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

    public List<VocabularySet> getSetAll() {
	    return this.sets;
    }

    public VocabularySet getSetByRandom() {
        int index = new Random().nextInt(this.sets.size());
        return this.sets.get(index);
    }

    public List getSetNames() {
        List<String> l = new ArrayList<String>();
        for (int i=0; i<this.sets.size(); i++) {
            l.add(this.sets.get(i).getLanguage());
        }
        return l;
    }

    public Integer getSetCount() {
	    return this.sets.size();
    }
}
