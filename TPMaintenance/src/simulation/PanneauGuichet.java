package simulation;import guichet.Guichet;import java.awt.*;import java.awt.event.*;/** The GUI panel that simulates the ATM itself */class PanneauGuichet extends Panel{    /** Constructor     *     *  @param gui L'interface globale du guichet     *  @param operatorPanel La simulation du panneau operateur     *  @param cardReader La simulation du lecteur de carte     *  @param display La simulation de l'ecran     *  @param keyboard La simulation du clavier     *  @param cashDispenser La simulation du distributeur d'argent     *  @param enveloppeAccpetor La simulation de la fente d'enveloppe     *  @param receiptPrinter La simulation de l'imprimeur de recu     */    PanneauGuichet(final GUI gui,             SimPanneauOperation operatorPanel,             SimLecteurCarte cardReader,             SimEcran display,             SimClavier keyboard,             SimDistributeurArgent cashDispenser,             SimFenteEnveloppe envelopeAcceptor,             SimImprimeurRecu receiptPrinter)    {        GridBagLayout atmLayout = new GridBagLayout();        setLayout(atmLayout);                        add(operatorPanel);        atmLayout.setConstraints(operatorPanel,                GUI.makeConstraints(OPERATOR_ROW, OPERATOR_COL,                                OPERATOR_WIDTH, OPERATOR_HEIGHT,                                OPERATOR_FILL));                                                Panel cardReaderPanel = new Panel();        cardReaderPanel.setLayout(new GridLayout(1,1));        cardReaderPanel.add(cardReader);        add(cardReaderPanel);        atmLayout.setConstraints(cardReaderPanel,                 GUI.makeConstraints(READER_ROW, READER_COL,                                READER_WIDTH, READER_HEIGHT,                                 READER_FILL));                                                add(display);        atmLayout.setConstraints(display,                 GUI.makeConstraints(DISPLAY_ROW, DISPLAY_COL,                                DISPLAY_WIDTH, DISPLAY_HEIGHT,                                DISPLAY_FILL));                                        add(keyboard);        atmLayout.setConstraints(keyboard,                GUI.makeConstraints(KEYBOARD_ROW, KEYBOARD_COL,                                KEYBOARD_WIDTH, KEYBOARD_HEIGHT,                                KEYBOARD_FILL));                                                add(cashDispenser);        atmLayout.setConstraints(cashDispenser,                GUI.makeConstraints(DISPENSER_ROW, DISPENSER_COL,                                DISPENSER_WIDTH, DISPENSER_HEIGHT,                                DISPENSER_FILL));                                                                                Panel envelopeAcceptorPanel = new Panel();        envelopeAcceptorPanel.setLayout(new GridLayout(1,1));        envelopeAcceptorPanel.add(envelopeAcceptor);        add(envelopeAcceptorPanel);        atmLayout.setConstraints(envelopeAcceptorPanel,                GUI.makeConstraints(ENVELOPE_ROW, ENVELOPE_COL,                                 ENVELOPE_WIDTH, ENVELOPE_HEIGHT,                                ENVELOPE_FILL));        envelopeAcceptor.setVisible(false);                        add(receiptPrinter);        atmLayout.setConstraints(receiptPrinter,                GUI.makeConstraints(PRINTER_ROW, PRINTER_COL,                                PRINTER_WIDTH, PRINTER_HEIGHT,                                PRINTER_FILL));                                                Panel showLogButtonPanel = new Panel();        showLogButtonPanel.setBackground(operatorPanel.getBackground());        Button showLogButton = new Button("Montrer Journal");        showLogButton.addActionListener(new ActionListener() {            public void actionPerformed(ActionEvent e)            {                gui.showCard("JOURNAL");            }        });        showLogButtonPanel.add(showLogButton);        add(showLogButtonPanel);        atmLayout.setConstraints(showLogButtonPanel,                GUI.makeConstraints(SHOW_LOG_BUTTON_ROW, SHOW_LOG_BUTTON_COL,                                SHOW_LOG_BUTTON_WIDTH, SHOW_LOG_BUTTON_HEIGHT,                                SHOW_LOG_BUTTON_FILL));    }    // L'interface representant le guichet utilise GridBagLayout.      // Les constantes suivantes determinent les positions des composants dans      // dans le grid bag.  Chaque composant a des coordonnees pour la rangee     // et la colonne dans son coin superieur gauche, en plus de hauteur pour     // les rangees et largeur pour les colonnes. L'arragement ressemble a:        // ----------------------------------------------------------------------    // |       ECRAN                                       |     IMPRIMEUR  |    // |                                                   |       RECU     |    // |                                                   |                |    // |                                                   |                |    // |                                                   |                |    // |                                                   |                |    // |                                                   |                |    // |                                                   |                |    // |--------------------------------------------------------------------|    // |  FENTE          |  DISTRIBUTEUR  |    LECTEUR     |   CLAVIER      |    // |  ENVELOPPE      |     ARGENT     |    CARTE       |                |    // |                 |                |                |                |    // |                 |                |                |                |    // |                 |                |                |                |    // |                 |                |                |                |    // ----------------------------------------------------------------------    // | BOUTTON AFFICHER| PANNEAU OPERATEUR                                |    // |     JOURNAL     |                                                  |    // ----------------------------------------------------------------------        // Les constantes suivantes dictent la taille des differents composants,      // et doivent etre utilises par le composant        public static final int DISPLAYABLE_LINES =     9;    public static final String BLANK_DISPLAY_LINE =         "                                             ";    public static final int PRINTABLE_LINES =       9;    public static final int PRINTABLE_CHARS =      30;        private static final int DISPLAY_ROW =          0;    private static final int DISPLAY_COL =          0;    private static final int DISPLAY_WIDTH =        3;    private static final int DISPLAY_HEIGHT =       1;    private static final int DISPLAY_FILL =         GridBagConstraints.BOTH;        private static final int PRINTER_ROW =          0;    private static final int PRINTER_COL =          DISPLAY_COL + DISPLAY_WIDTH;    private static final int PRINTER_WIDTH =        1;    private static final int PRINTER_HEIGHT =       1;    private static final int PRINTER_FILL =         GridBagConstraints.NONE;        private static final int ENVELOPE_ROW =         DISPLAY_ROW + DISPLAY_HEIGHT;    private static final int ENVELOPE_COL =         0;    private static final int ENVELOPE_WIDTH =       1;    private static final int ENVELOPE_HEIGHT =      1;    private static final int ENVELOPE_FILL =        GridBagConstraints.NONE;            private static final int DISPENSER_ROW =        ENVELOPE_ROW;    private static final int DISPENSER_COL =        ENVELOPE_COL + ENVELOPE_WIDTH;    private static final int DISPENSER_WIDTH =      1;    private static final int DISPENSER_HEIGHT =     1;    private static final int DISPENSER_FILL =       GridBagConstraints.NONE;    private static final int READER_ROW =           ENVELOPE_ROW;    private static final int READER_COL =           DISPENSER_COL + DISPENSER_WIDTH;    private static final int READER_WIDTH =         1;    private static final int READER_HEIGHT =        1;    private static final int READER_FILL =          GridBagConstraints.NONE;        private static final int KEYBOARD_ROW =         ENVELOPE_ROW;    private static final int KEYBOARD_COL =         READER_COL + READER_WIDTH;    private static final int KEYBOARD_WIDTH =       1;    private static final int KEYBOARD_HEIGHT =      1;    private static final int KEYBOARD_FILL =        GridBagConstraints.NONE;        private static final int SHOW_LOG_BUTTON_ROW =  ENVELOPE_ROW + ENVELOPE_HEIGHT;    private static final int SHOW_LOG_BUTTON_COL =  0;    private static final int SHOW_LOG_BUTTON_WIDTH= 1;    private static final int SHOW_LOG_BUTTON_HEIGHT=1;    private static final int SHOW_LOG_BUTTON_FILL = GridBagConstraints.BOTH;        private static final int OPERATOR_ROW =         SHOW_LOG_BUTTON_ROW;    private static final int OPERATOR_COL =         SHOW_LOG_BUTTON_COL + SHOW_LOG_BUTTON_WIDTH;    private static final int OPERATOR_WIDTH =       3;    private static final int OPERATOR_HEIGHT =      1;    private static final int OPERATOR_FILL =        GridBagConstraints.BOTH;        private static final int TOTAL_ROWS = 3;    private static final int TOTAL_COLS = 3;}                                            