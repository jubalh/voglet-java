package voglet;

import java.io.File;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * Accessor for voglet files
 * @author michael
 */
public class VogletFile {

	private File file;
	private VogletManager manager;
	private Serializer serializer;

	/**
	 * Constructor
	 * @param filename for voglet file
	 * @throws Exception
	 */
	public VogletFile(String filename) throws Exception {
		serializer = new Persister();
		this.file = new File(filename);
		if (this.file.exists()) {
			this.manager = serializer.read(VogletManager.class, this.file);
		}
	}

	/**
	 * saves file
	 * @throws Exception
	 */
	public void save() throws Exception {
		serializer.write(this.manager, this.file);
	}

	/**
	 * @return VogletManager object
	 */
	public VogletManager getManager() {
		return this.manager;
	}

	/**
	 * @return location of voglet file
	 */
	public String getFilename() {
		return this.file.getAbsolutePath();
	}
}
