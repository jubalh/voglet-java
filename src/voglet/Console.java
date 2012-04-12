package voglet;

import jargs.gnu.CmdLineParser;

/**
 * Console version of voglet
 * @author michael.vetter
 */
public class Console {

    private CmdLineParser m_parser;
    private VogletManager m_manager;
    private VogletFile m_vogletFile;
    //TODO: use logger. private Logger logger = Logger.getLogger( Console.class.getName() );

    /**
     * Main functionality
     * @param args console parameters
     */
    public Console(String[] args) {

        // set commandline arguments
        m_parser = new CmdLineParser();
        CmdLineParser.Option argFile = m_parser.addStringOption('f', "file");
        CmdLineParser.Option argInfo = m_parser.addBooleanOption('i', "info");
        CmdLineParser.Option argGetRandom = m_parser.addBooleanOption("random");
        CmdLineParser.Option argLogging = m_parser.addBooleanOption("log");//###todo
        CmdLineParser.Option argShow = m_parser.addBooleanOption('s', "show");
        CmdLineParser.Option argAdd = m_parser.addBooleanOption('a', "add");
        CmdLineParser.Option argId = m_parser.addIntegerOption("id");
        CmdLineParser.Option argLanguage = m_parser.addStringOption('l', "language");
        CmdLineParser.Option argWord = m_parser.addStringOption('w', "word");
        CmdLineParser.Option argTranslation = m_parser.addStringOption('t', "translation");
        CmdLineParser.Option argComment = m_parser.addStringOption('c', "comment");

	// try to read arguments
        try {
            m_parser.parse(args);
        } catch (CmdLineParser.OptionException e) {
            this.ShowUsage();
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // get filename
        String filename = (String) m_parser.getOptionValue(argFile);
        if (filename == null) {
            ShowUsage();
            System.exit(0);
        }

        // parse voglet file
        try {
            m_vogletFile = new VogletFile(filename);
            m_manager = m_vogletFile.getManager();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // show information
        if ((Boolean) m_parser.getOptionValue(argInfo, Boolean.FALSE)) {
            this.ShowFileInfo();
        }

	// show random entry
        if ((Boolean) m_parser.getOptionValue(argGetRandom, Boolean.FALSE)) {
            this.r();
        }

	// language
        String paraLanguage = (String) m_parser.getOptionValue(argLanguage, new String());

	// show
        if ((Boolean) m_parser.getOptionValue(argShow, Boolean.FALSE)) {
            CheckEmpty(paraLanguage);
            ShowEntry(paraLanguage, (Integer) m_parser.getOptionValue(argId));
        }

	// add
        if ((Boolean) m_parser.getOptionValue(argAdd, Boolean.FALSE)) {
            String paraWord = (String) m_parser.getOptionValue(argWord, new String());
            String paraTranslation = (String) m_parser.getOptionValue(argTranslation, new String());
            String paraComment = (String) m_parser.getOptionValue(argComment, new String());

            CheckEmpty(paraLanguage, paraWord, paraTranslation);
            AddEntry(paraLanguage, paraWord, paraTranslation, paraComment);//TODO: multiple words
        }
    }

    /**
     * checks if strings S are empty
     * if yes print message and exit
     * @param s
     */
    private void CheckEmpty(String... s) {
        for (String e : s) {
            if (e.isEmpty()) {
                ShowUsage();
                System.exit(0);
            }
        }
    }

    /**
     * prints information about how to use the program on screen
     * arguments and so on
     */
    private void ShowUsage() {
        System.err.println("Usage: voglet [-f,--file] filename.xml ");
    }

    /**
     * prints information about voglet file on the screen
     */
    private void ShowFileInfo() {
        System.out.println("Configuration:");
        System.out.println(m_manager.getConf().toString());

        System.out.println("Set:\t\tEntries:");
        for (VocabularySet set : m_manager.getSetAll()) {
            System.out.println(set.getLanguage() + "\t\t" + set.getEntryCount());
        }
        System.out.println("");
    }

    /**
     * prints entry on screen if possible
     * if id is non existent, it will take a random entry
     * @param language languge set
     * @param id entrys id
     */
    private void ShowEntry(String language, Integer id) {
        VocabularySet set = m_manager.getSet(language);
        Integer max;
        Entry entry;

        if (set == null) {
            System.err.println("Language " + language + " not available");
            //TODO: log
            System.exit(1);
        }
        max = set.getEntryCount();
        if (id == null || id > max) {
            if (id > max) {
                System.err.println("There are just " + max + " entries");//###log
            }
            entry = set.getRandomEntry();
        } else {
            entry = set.getEntryByIndex(id);
        }
        PrintEntry(entry);
    }

    /**
     * prints one entry on the screen
     * @param entry
     */
    private void PrintEntry(Entry entry) {
        System.out.println("Word: " + entry.getWord());
        System.out.println("Translation: " + entry.getTranslation());
        System.out.println("Comment: " + entry.getComment());
    }

    /**
     * adds entry to vogle tfile
     * @param language language set
     * @param word target language word
     * @param translation native language word
     * @param comment comment on word
     */
    private void AddEntry(String language, String word, String translation, String comment) {
        VocabularySet set = m_manager.getSet(language);
        if (set == null) {
            set = new VocabularySet(language);
            m_manager.addSet(set);
        }
        set.addEntry(word, translation, comment);
        try {
            m_vogletFile.save();
        } catch (Exception ex) {
            System.err.println("Error while trying to save file " + m_vogletFile.getFilename());
            System.err.println(ex.getMessage());//###log
        }
    }

    /**
     * for testing
     * prints random entry from random set
     */
    private void r() {
        VocabularySet set = m_manager.getSetByRandom();
        Entry entry = set.getRandomEntry();

        System.out.println("Language: " + set.getLanguage());
        System.out.println("Word: " + entry.getWord());
        System.out.println("Translation: " + entry.getTranslation());
        System.out.println("Comment: " + entry.getComment());
    }

    /**
     * prints all available language sets
     */
    private void PrintAllSets() {
        for (VocabularySet set : m_manager.getSetAll()) {
            System.out.println("Language " + set.getLanguage() + ":");
            for (int i = 0; i < set.getEntryCount(); i++) {
                PrintEntry(set.getEntryByIndex(i));
                System.out.println(" ");
            }
        }
    }

    /**
     * main
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        Console c = new Console(args);
    }
}
