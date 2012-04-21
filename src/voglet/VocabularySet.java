package voglet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

/**
 * a set of entries
 * @author michael
 */
public class VocabularySet {

	@Attribute
	private String language;
	@ElementList
	private List<Entry> entries;

	/**
	 * Constructor
	 * @param language name of the set1
	 */
	public VocabularySet(String language) {
		this.language = language;
		this.init();
	}

	/**
	 * Constructor
	 */
	public VocabularySet() {
		this.init();
	}

	/**
	 * Creates list with entires
	 */
	private void init() {
		this.entries = new ArrayList<Entry>();
	}

	/**
	 * determines the next free id
	 * @return net free id
	 */
	private Integer getNextEntryId() {
		return this.getEntryCount()+1;
	}

	/**
	 * adds an entry
	 * @param word
	 * @param translation
	 * @param comment
	 */
	public void addEntry(String word, String translation, String comment) {
		Entry e = new Entry(word, translation, comment);
		e.setId( this.getNextEntryId() );
		this.addEntry(e);
	}

	/**
	 * @param e entry to be added
	 */
	public void addEntry(Entry e) {
		e.setId( this.getNextEntryId() );
		this.entries.add(e);
	}

	/**
	 * @param e entry to be removed
	 */
	public void removeEntry(Entry e) {
		this.entries.remove(e);
	}

	/**
	 * @return random entry
	 */
	public Entry getRandomEntry() {
		int index = new Random().nextInt(this.entries.size());
		return this.entries.get(index);
	}

	/**
	 * @param index of entry
	 * @return entry with index INDEX
	 */
	public Entry getEntryByIndex(int index) {
		return this.entries.get(index);
	}

	/**
	 * @return number of entries in list
	 */
	public Integer getEntryCount() {
		return this.entries.size();
	}

	/**
	 * @return set identifier
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language name of set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}
