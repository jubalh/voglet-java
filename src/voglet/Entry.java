package voglet;

import java.util.ArrayList;
import java.util.Random;
import org.simpleframework.xml.Attribute;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Represents one vocabulary entry
 * one or more words (synonyms) in your target, translation in your native language (also with synonyms) and a comment  about the word
 * An Entry also has a id which must be unique in the set
 * @author michael
 */
@Root
public class Entry {

	@Attribute
	private int id;
	@ElementList
	private List<String> words;
	@ElementList
	private List<String> translations;
	@Element
	private String comment;

	/**
	 * Creates an Entry
	 * @param word Word
	 * @param translation Translation
	 * @param comment Comment about the Entry
	 * @code Entry e = new Entry("ella", "sie", "female second person singular");
	 */
	public Entry(String word, String translation, String comment) {
		this.init();
		this.words.add(word);
		this.translations.add(translation);
		this.setComment(comment);
	}

	/**
	 * Creates new Entry without values
	 */
	public Entry() {
		this.init();
	}

	/**
	 * Initializes an Entry
	 * Creates ArrayLists for words and translations
	 */
	private void init() {
		this.words = new ArrayList<String>();
		this.translations = new ArrayList<String>();
	}

	/**
	 * @return ID of the Entry
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets ID of Entry
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns how many synonyms the words have(include the word itself)
	 * @return Number of words
	 */
	public int getWordCount() {
		return this.words.size();
	}

	/**
	 * A Word can have multiple synonyms
	 * returns the word specified by index
	 * @param index
	 * @return Word as String
	 * @see Entry#getWordCount
	 */
	public String getWord(int index) {
		return this.words.get(index);
	}

	/**
	  * A Word can have multiple synonyms
	 * returns the word with index 0
	 * @return first added word
	 */
	public String getWord() {
		return this.getWord(0);
	}

	/**
	 * @return the word or an synonym by random
	 */
	public String getWordByRandom() {
		int index = new Random().nextInt(this.words.size());
		return this.getWord(index);
	}

	/**
	 * @return List Object with all words
	 */
	public List getAllWords() {
		return this.words;
	}

	/**
	 * sets word with specific index
	 * @param word word
	 * @param index if index already exists, it gets overridden
	 */
	public void setWord(String word, int index) {
		this.words.remove(index);//TODO: unnecessary?
		this.words.add(index, word);
	}

	/**
	 * clears synonyms and just adds the word
	 * @param word new word
	 */
	public void setWord(String word) {
		this.words.clear();
		this.words.add(word);
	}

	/**
	 * adds a  new word
	 * @param synonym new word
	 * @see Entry#getWordCount()
	 */
	public void addWordSynonym(String synonym) {
		this.words.add(synonym);
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * sets comment
	 * @param comment
	 */
	public void setComment(String comment) {
		if (comment.isEmpty())
			comment = "_";//TODO: here is something to look at. otherwise it will get an error when parsing the xml file with serializer
		this.comment = comment;
	}

	/**
	 * returns how many translations are available
	 * @return number of translations
	 */
	public int getTranslationCount() {
		return this.translations.size();
	}

	/**
	 * returns specific translation
	 * @param index
	 * @return translation number INDEX
	 */
	public String getTranslation(int index) {
		return this.translations.get(index);//TODO: what to do if index > translation.count
	}

	/**
	 * @return first added translation
	 */
	public String getTranslation() {
		return this.getTranslation(0);
	}

	/**
	 * @return a random translation
	 */
	public String getTranslationByRandom() {
		int index = new Random().nextInt(this.translations.size());
		return this.getTranslation(index);
	}

	/**
	 * @return List of all translations
	 */
	public List getAllTranslations() {
		return this.translations;
	}

	/**
	 * sets translation number INDEX to word
	 * @param word translated word
	 * @param index number
	 */
	public void setTranslation(String word, int index) {
		this.translations.remove(index);//...unnecessary?
		this.translations.add(index, word);
	}

	/**
	 * adds this one translation and removes all others
	 * @param word translated word
	 */
	public void setTranslation(String word) {
		this.translations.clear();
		this.translations.add(word);
	}

	/**
	 * adds this translation to list with translations
	 * @param synonym translated word
	 */
	public void addTranslation(String synonym) {
		this.translations.add(synonym);
	}

	/**
	 * checks if WORD is in list of words
	 * @param word which should be searched
	 * @return true if WORD is in list
	 */
	public boolean hasWord(String word) {
		for (String s : this.words) {
			if (s.equals(word)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * checks if WORD is in list of translations
	 * @param word which should be searched
	 * @return true if WORD is in list
	 */
	public boolean hasTranslation(String word) {
		for (String s : this.translations) {
			if (s.equals(word)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * for debugging purposes
	 * @return information on entry
	 */
	@Override
	public String toString() {
		String s = this.getWordCount() + " words\n"
			+ this.words.toString() + "\n"
			+ this.getTranslationCount() + " translations\n"
			+ this.translations.toString() + "\n"
			+ "Comment: " + this.comment + "\n"
			+ "ID: " + this.id;

		return s;
	}
}
