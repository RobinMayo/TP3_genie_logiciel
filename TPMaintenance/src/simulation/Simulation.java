package simulation;import compte.Balances;import compte.Carte;import compte.Message;import compte.Argent;import compte.Etat;import guichet.Guichet;/** Simulation des composants physiques du guichet, incluant sa  *  connection reseau avec la banque. Une instance est creee au startup par le main() *  de l'application. *   *  Les composants individuels sont affiches dans un panneau de la class GUI. *  La banque est simulee par un objet de la classe BanqueSimulee. Le constructeur de *  cette classe cree une instance de chaque. * *  La methode static getInstance() permet aux composants du guichet d'acceder  *  a l'unique instance de cette classe pour simuler les differents operations. *  Les autres methodes simule des operations specifiques du guichet, et sont  *  passees soit au panneau GUI ou a la banque simulee pour s'en occuper. */public class Simulation{    public Simulation(Guichet atm)    {        this.atm = atm;                // Cree les composants individuels simules de l'interface du guichet                 operatorPanel = new SimPanneauOperation(this);        cardReader = new SimLecteurCarte(this);        display = new SimEcran();        cashDispenser = new SimDistributeurArgent();        envelopeAcceptor = new SimFenteEnveloppe();        receiptPrinter = new SimImprimeurRecu();        keyboard = new SimClavier(display, envelopeAcceptor);                // Cree l'interface du guichet contenant les composants ci haut                gui = new GUI(operatorPanel, cardReader, display, keyboard,                      cashDispenser, envelopeAcceptor, receiptPrinter);                // Cree La simulation de la banque                simulatedBank = new BanqueSimulee();                theInstance = this;    }        /** Accessor pour la seule et unique instance de cette classe     *     *  @return L'instance de cette classe     */    public static Simulation getInstance()    {        return theInstance;    }        /** Simule la recuperation du montant initial d'argent dans le guichet par l'operateur     *     *  @return value of initial cash entered     */    public Argent getInitialCash()    {        return gui.getInitialCash();    }        /** Simule la lecture d'une carte     *     *     *  @return objet carte representant les information sur une carte client si lecture reussie,     *  null sinon     */    public Carte readCard()    {        // Le guichet ne peut etre eteint si il y a une carte inseree dedans        operatorPanel.setEnabled(false);        cardReader.animateInsertion();                // On simule la lecture de la bande magnetique de la carte par l'entree du numero de carte                return gui.readCard();    }        /** Simule ejection d'une carte      */    public void ejectCard()    {        cardReader.animateEjection();        operatorPanel.setEnabled(true);    }        /** Simule retention d'une carte     */    public void retainCard()    {        cardReader.animateRetention();        operatorPanel.setEnabled(true);    }        /** Effacer l'ecran simule     */    public void clearDisplay()    {        display.clearDisplay();    }        /** Ecrire une ou plusieurs lignes a l'ecran - apres la derniere ligne ecrite     *     *  @param text le text a afficher     */    public void display(String text)    {        display.display(text);    }         /** Simule la lecture d'une entree du clavier     *     *  @param mode Le mode d'entree utilise - une des constantes definies ci-dessous.     *  @param maxValue La valeur maximale acceptee (utilisee dans MENU_MODE seulement)     *  @return La ligne qui a ete entre - null si l'usager a appuyer sur ANNULER.     */    public String readInput(int mode, int maxValue)    {        return keyboard.readInput(mode, maxValue);    }        /** Simule la distribution d'argent a un client     *     *  @param amount Le montant a distribuer au client     *     *  Precondition: amount est <= argent disponible     */    public void dispenseCash(Argent amount)    {        cashDispenser.animateDispensingCash(amount);    }    /** Simule acceptation d'une enveloppe d'un client.     *     *  return true si une enveloppe a ete recu dans le temps allouee, faux sinon     */    public boolean acceptEnvelope()    {        return envelopeAcceptor.acceptEnvelope();    }    /** Simule l'impression d'une ligne du recu     *     *  @param text la ligne a imprimer     */    public void printReceiptLine(String text)    {        receiptPrinter.println(text);    }        /** Simule l'impression d'une ligne du journal     *     *  @param text la ligne a imprimer     */    public void printLogLine(String text)    {        gui.printLogLine(text);    }        /** Simule l'envoi d'un message a la banque     *     *  @param message le message a envoyer     *  @param balances (out) balances du compte client rapportees par la banque     *  @return code de l'etat retourne par la banque     */    public Etat sendMessage(Message message, Balances balances)    {        // Simule le temps pris pour l'envoi du message par reseau                try        {            Thread.sleep(2 * 1000);        }        catch(InterruptedException e)        { }                return simulatedBank.handleMessage(message, balances);    }    /** Notifie le guichet que l'etat du button on-off a change     *     *  @param on vrai si l'etat est "on", faux si il est "off"     */    void switchChanged(boolean on)    {        cardReader.setVisible(on);                if (on)            atm.switchOn();        else            atm.switchOff();    }        /** Notifie le guichet qu'une carte a ete insere     */    void cardInserted()    {        atm.cardInserted();    }        /** Accessor pour le panneau GUIqui simule le guuichet     *     *  @return le panneau GUI     */    public GUI getGUI()    {        return gui;    }        /** Accessor pour la banque simulee     *     *  @return la banque simulee     */    public BanqueSimulee getSimulatedBank()    {        return simulatedBank;    }    /* Valeurs possibles pour le parametre mode de readInput()*/        /** Lire entree en mode NIP - permet au client d'entrer differents characteres,     *  et d'effacer la ligne si le client le souhaite; affiche des asterisques     */    public static final int PIN_MODE = 1;        /** Lire entree en mode montant - permet au client d'entrer differents characteres,     *  et d'effacer la ligne si le client le souhaite; affiche types utilises     */    public static final int AMOUNT_MODE = 2;        /** Lire entree en mode choix du menu - attend un chiffre en entree,     *  et retourne la valeur immediatement.     */    public static final int MENU_MODE = 3;        /** l'objet guichet simule     */    private Guichet atm;        /** Panneau operateur simule     */    private SimPanneauOperation operatorPanel;        /** Lecteur de carte simule     */    private SimLecteurCarte cardReader;        /** Ecran simule     */    private SimEcran display;        /** Clavier simule     */    private SimClavier keyboard;        /** Distributeur argent simule     */    private SimDistributeurArgent cashDispenser;        /** Fente enveloppe simule     */    private SimFenteEnveloppe envelopeAcceptor;        /** Imprimeur recu simule     */    private SimImprimeurRecu receiptPrinter;        /** Panneau contenant l'interface simulant le guichet     */    private GUI gui;        /** Banque simulee     */    private BanqueSimulee simulatedBank;        /** La seule et unique instance de cette classe     */    private static Simulation theInstance;}    