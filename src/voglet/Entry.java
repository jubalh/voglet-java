package voglet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author michael
 */
@Root
public class Entry {
    @Attribute
    private int id;
    @ElementList
    private List <String> words;
    @ElementList
    private List <String>translations;
    @Element
    private String comment;

    public Entry(String word, String translation, String comment) {
        this.init();
        this.words.add(word);
        this.translations.add(translation);
        this.comment = comment;
    }

    public Entry() {
        this.init();
    }

    private void init() {
        this.words = new ArrayList<String>();
        this.translations = new ArrayList<String>();
    }

    /*
     * returns how many synonyms the words have(include the word itself)
     */
    public int getWordCount() {
        return this.words.size();
    }

    /*
     * returns the word specified by index
     */
    public String getWord(int index) {
        return this.words.get(index);
    }

    /*
     * returns the word with index 0
     */
    public String getWord() {
        return this.getWord(0);
    }

    /*
     * returns the word or an synonym by random
     */
    public String getWordByRandom() {
        int index = new Random().nextInt(this.words.size());
        return this.getWord(index);
    }

    /*
     * returns all words
     */
    public List getAllWords() {
        return this.words;
    }

    /*
     * sets word with specific index
     */
    public void setWord(String word, int index) {
        this.words.remove(index);//...unnecessary?
        this.words.add(index, word );
    }

    /*
     * clears synonyms and just adds the word
     */
    public void setWord(String word) {
        this.words.clear();
        this.words.add(word);
    }

    /*
     * adds synonyms
     */
    public void addWordSynonym(String synonym) {
        this.words.add(synonym);
    }

    /*
     * returns the comment
     */
    public String getComment() {
        return comment;
    }

    /*
     * sets a comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /*
     * returns how many translations are available
     */
    public int getTranslationCount() {
        return this.translations.size();
    }

    /*
     * returns the translation specified by index
     */
    public String getTranslation(int index) {
        return this.translations.get(index);
    }

    /*
     * returns the translation with index 0
     */
    public String getTranslation() {
        return this.getTranslation(0);
    }

    /*
     * returns a random translation
     */
    public String getTranslationByRandom() {
        int index = new Random().nextInt(this.translations.size());
        return this.getTranslation(index);
    }

    /*
     * returns all translation
     */
    public List getAllTranslations() {
        return this.translations;
    }

    /*
     * sets translation with specific index
     */
    public void setTranslation(String word, int index) {
        this.translations.remove(index);//...unnecessary?
        this.translations.add(index, word );
    }

    /*
     * just adds this one translation and removes all others
     */
    public void setTranslation(String word) {
        this.translations.clear();
        this.translations.add(word);
    }

    /*
     * adds synonyms
     */
    public void addTranslation(String synonym) {
        this.translations.add(synonym);
    }

    /*
     * searches for word in list of words
     */
    public boolean hasWord(String word) {
       for ( String s : this.words )  {
           if ( s.equals(word) )
               return true;
       }
       return false;
    }

    /*
     * searches for translation in list of words
     */
    public boolean hasTranslation(String word) {
       for ( String s : this.translations )  {
           if ( s.equals(word) )
               return true;
       }
       return false;
    }

    @Override
    public String toString() {
        String s = this.getWordCount()+" words\n"
                + this.words.toString()+"\n"
                + this.getTranslationCount()+" translations\n"
                + this.translations.toString()+"\n"
                + "Comment: "+this.comment+"\n"
                + "ID: "+this.id;

        return s;
    }
}
