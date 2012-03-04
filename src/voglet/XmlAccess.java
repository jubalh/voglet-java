package voglet;

import java.io.File;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author michael
 */
public class XmlAccess {
    private String filename;
    private VocabularySet manager;

    public XmlAccess(String filename) throws Exception {
        this.filename = filename;
        this.init();
    }

    private void init() throws Exception {
        Serializer serializer = new Persister();
        File f = new File(this.filename);
        if ( f.exists() )
            this.manager = serializer.read(VocabularySet.class, f);
    }

    public void save() throws Exception {
        Serializer serializer = new Persister();
        File f = new File(this.filename);
        serializer.write(this.manager, f);
    }

    public VocabularySet getManager() {
        return this.manager;
    }


}
