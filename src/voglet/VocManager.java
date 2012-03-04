package voglet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author michael
 */
@Root
public class VocManager {
    @Attribute
    //private String language;

    @ElementList
    private List <Entry> entries;
    
    public VocManager(String path) {
        this.init();
    }

    public VocManager() {
        this.init();
    }

    private void init() {
        this.entries = new ArrayList<Entry>();
    }

    public void addEntry(Entry e) {
        this.entries.add(e);
    }

    public void addEntry(String word, String translation, String comment) {
        Entry e = new Entry(word, translation, comment);
        this.addEntry(e);
    }

    public void removeEntry(Entry e) {
        this.entries.remove(e);
    }

    public Entry getRandomEntry() {
        int index = new Random().nextInt(this.entries.size());
        return this.entries.get(index);
    }

    public Entry getRandomEntryByIndex(int index) {
        return this.entries.get(index);
    }

    public int getEntryCount() {
        return this.entries.size();
    }
}
