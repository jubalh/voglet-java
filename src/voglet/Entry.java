package voglet;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author michael
 */
@Root
public class Entry {
    @Attribute
    private int id;
    @Element
    private String word;
    @Element
    private String translation;
    @Element
    private String comment;

    public Entry(String word, String translation, String comment) {
        this.word = word;
        this.translation = translation;
        this.comment = comment;
    }

    public Entry() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        String s = "Word: "+this.word+"\n"
                + "Translation: "+this.translation+"\n"
                + "Comment: "+this.comment+"\n"
                + "ID: "+this.id;
        return s;
    }
}
