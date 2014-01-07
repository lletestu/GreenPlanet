package GreenPlanet.View.MainViewGame;

import GreenPlanet.Controller.GreenPlanetController;
import GreenPlanet.Controller.Listener.ComboBoxListener;
import GreenPlanet.Controller.Listener.ComboBoxListenerIA;
import GreenPlanet.Controller.Listener.EntryMouseListener;
import GreenPlanet.Controller.Listener.GoToWebsiteListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * Action Listener WindowGAMECONF menu/frame
 * Initialise and displays WindowGAMECONF menu/frame
 * STATUS: DONE
 * LAST UPDATE: 11/05/13
 * @author Jérémy
 */
public class WindowGAMECONF extends JFrame implements ActionListener {
    
    /**
     * WindowSPLASH source frame
     */
    private WindowSPLASH myFrame;
    
    /**
     * GreenPlanetController source object
     */
    private GreenPlanetController greenPlanetController;
    
    /**
     * "Launch the Game!" JButton
     */
    private JButton launchGame;
    
    /**
     * "Go to the website" JButton
     */
    private JButton goWebsite;
    
    /**
     * All the JTextFields of the WindowGAMECONF menu
     */
    private JTextField[] entrys;
    
    /**
     * Secondary JPanel
     */
    private JPanel inPane;
    
    /**
     * "inPane" additionnal JPanels
     */
    private JPanel[] panes;
    
    /**
     * Menu JLabels
     */
    private JLabel[] labels;
    
    /**
     * Labels of the gameType JComboBox
     */
    private String[] gameType;
    
    /**
     * JComboBox to select the type of the game
     */
    private JComboBox comboBox;
    
    /**
     * Labels of the AI JComboBox
     */
    private String[] iAType;
    
    /**
     * JComboBox to select the AI og the game
     */
    private JComboBox comboBoxIA;
    
    /**
     * Title font
     */
    private Font titleFont;
    
    /**
     * TextFields font
     */
    private Font fieldsFont;
    
    /**
     * Primary border
     */
    private Border primaryBorder;
    
    /**
     * Secondary border
     */
    private Border secondaryBorder;
    
    /**
     * TitleBorder of the menu
     */
    private TitledBorder pageTitle;
    
    /**
     * Theme color
     */
    private Color themeColor;
    
    /**
     * Type of the AI : "IA1" or "IA2"
     */
    private String ia;

    /**
     * Additionnal theme font
     */
    private Font font = new Font("Arial",Font.PLAIN,12);
    
    /**
     * Additionnal color font
     */
    private Color colorFont = new Color(66,67,69);

    /**
     * Default constructor
     * Initialises the main components of the WindowGAMECONF menu
     * @param myFrame is the WindowSPLASH source frame
     * @param greenPlanetController is the GreenPlanetController source object
     */
    public WindowGAMECONF(WindowSPLASH myFrame, GreenPlanetController greenPlanetController) {
        this.greenPlanetController = greenPlanetController;

        this.themeColor = new Color(253,238,195);
        this.myFrame = myFrame;

        this.gameType = new String[2];
        this.gameType[0] = "Offline";
        this.gameType[1] = "Online";

        this.comboBox = new JComboBox(gameType);
        this.comboBox.setMaximumSize(new Dimension(300,30));
        this.comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.comboBox.addActionListener(new ComboBoxListener(this));
        this.comboBox.setFont(font);
        this.comboBox.setBackground(themeColor);
        this.comboBox.setForeground(colorFont);
        this.comboBox.setToolTipText("Choose the type of game");

        this.iAType = new String[2];
        this.iAType[0] = "IA1";
        this.iAType[1] = "IA2";

        this.comboBoxIA = new JComboBox(iAType);
        this.comboBoxIA.setMaximumSize(new Dimension(300,30));
        this.comboBoxIA.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.comboBoxIA.addActionListener(new ComboBoxListenerIA(this));
        this.comboBoxIA.setFont(font);
        this.comboBoxIA.setBackground(themeColor);
        this.comboBoxIA.setForeground(colorFont);
        this.comboBoxIA.setToolTipText("Choose the IA that plays the game");

        this.titleFont = new Font("Bazar",Font.BOLD,30);
        this.fieldsFont = new Font("Bazar",Font.BOLD,20);
        this.primaryBorder = BorderFactory.createLineBorder(themeColor,10);
        this.secondaryBorder = BorderFactory.createLineBorder(themeColor,3);
        this.pageTitle = BorderFactory.createTitledBorder(primaryBorder, "GAME OPTIONS", TitledBorder.CENTER, TitledBorder.TOP, titleFont, new Color(84,84,84));

        this.inPane = new JPanel();
        this.inPane.setMaximumSize(new Dimension(300,150));
        this.inPane.setLayout(new BoxLayout(inPane, BoxLayout.Y_AXIS));
        this.inPane.setBackground(themeColor);
        this.inPane.setOpaque(true);
        this.inPane.setBorder(secondaryBorder);

        this.panes = new JPanel[4];
        for(int i=0; i<4; i++) {
            this.panes[i] = new JPanel();
            this.panes[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            this.panes[i].setOpaque(false);
            this.panes[i].setFont(font);
        }

        this.labels = new JLabel[4];
        this.labels[0] = new JLabel("GAME ID");
        this.labels[1] = new JLabel("PSEUDO");
        this.labels[2] = new JLabel("NB BOTS");
        this.labels[3] = new JLabel("IA");

        for(int j=0; j<4; j++) {
            this.labels[j].setFont(fieldsFont);
            this.labels[j].setForeground(new Color(84,84,84));
        }

        this.entrys = new JTextField[4];
        for(int k=0; k<4; k++) {
            this.entrys[k] = new JTextField("Click to change");
            this.entrys[k].setPreferredSize(new Dimension(200,30));
            this.entrys[k].addMouseListener(new EntryMouseListener(this.entrys[k]));
        }
        this.entrys[0].setToolTipText("Game ID");
        this.entrys[1].setToolTipText("MAXIMUM 12 characters");
        this.entrys[2].setToolTipText("MAXIMUM 24 BOTS");

        launchGame = new JButton("Launch the Game!");
        launchGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        launchGame.setMaximumSize(new Dimension(200,30));
        launchGame.addActionListener(new WindowINGAME(this));

        goWebsite = new JButton("Go to the website");
        goWebsite.setAlignmentX(Component.CENTER_ALIGNMENT);
        goWebsite.setMaximumSize(new Dimension(200,30));
        goWebsite.addActionListener(new GoToWebsiteListener());

        constructWindow();
    }

    /**
     * Constructs and displays the components for the WindowGAMECONF menu
     */
    private void constructWindow() {
        myFrame.getGameOptions().removeAll();

        comboBox.setSelectedItem("Offline");
        comboBoxIA.setSelectedItem("IA1");
        entrys[0].setEnabled(false);

        for(int l=0; l<3; l++) {
            panes[l].add(labels[l]);
            panes[l].add(entrys[l]);
            inPane.add(panes[l]);
        }

        panes[3].add(labels[3]);
        panes[3].add(comboBoxIA);
        inPane.add(panes[3]);

        for(int m=0; m<4; m++) {
            labels[m].setPreferredSize(new Dimension(80,28));
            entrys[m].setPreferredSize(new Dimension(160,28));
        }

        myFrame.getGameOptions().setBorder(pageTitle);
        myFrame.getGameOptions().setAlignmentX(Component.CENTER_ALIGNMENT);
        myFrame.getGameOptions().add(comboBox);
        myFrame.getGameOptions().add(Box.createRigidArea(new Dimension(0,25)));
        myFrame.getGameOptions().add(inPane);
        myFrame.getGameOptions().add(Box.createRigidArea(new Dimension(0,25)));
        myFrame.getGameOptions().add(goWebsite);
        myFrame.getGameOptions().add(Box.createRigidArea(new Dimension(0,10)));
        myFrame.getGameOptions().add(launchGame);

        myFrame.validate();
    }

    /**
     * Action performed
     * Creates the WindowGAMECONF menu
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        constructWindow();
    }

    /**
     * @return the myFrame
     */
    public WindowSPLASH getMyFrame() {
        return myFrame;
    }

    /**
     * @param myFrame the myFrame to set
     */
    public void setMyFrame(WindowSPLASH myFrame) {
        this.myFrame = myFrame;
    }

    /**
     * @return the pageTitle
     */
    public TitledBorder getPageTitle() {
        return pageTitle;
    }

    /**
     * @param pageTitle the pageTitle to set
     */
    public void setPageTitle(TitledBorder pageTitle) {
        this.pageTitle = pageTitle;
    }

    /**
     * @return the launchGame
     */
    public JButton getLaunchGame() {
        return launchGame;
    }

    /**
     * @param launchGame the launchGame to set
     */
    public void setLaunchGame(JButton launchGame) {
        this.launchGame = launchGame;
    }

    /**
     * @return the goWebsite
     */
    public JButton getGoWebsite() {
        return goWebsite;
    }

    /**
     * @param goWebsite the goWebsite to set
     */
    public void setGoWebsite(JButton goWebsite) {
        this.goWebsite = goWebsite;
    }

    /**
     * @return the gameType
     */
    public String[] getGameType() {
        return gameType;
    }

    /**
     * @param gameType the gameType to set
     */
    public void setGameType(String[] gameType) {
        this.gameType = gameType;
    }

    /**
     * @return the comboBox
     */
    public JComboBox getComboBox() {
        return comboBox;
    }

    /**
     * @param comboBox the comboBox to set
     */
    public void setComboBox(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    /**
     * @return the entrys
     */
    public JTextField[] getEntrys() {
        return entrys;
    }

    /**
     * @param entrys the entrys to set
     */
    public void setEntrys(JTextField[] entrys) {
        this.entrys = entrys;
    }

    /**
     * @return the inPane
     */
    public JPanel getInPane() {
        return inPane;
    }

    /**
     * @param inPane the inPane to set
     */
    public void setInPane(JPanel inPane) {
        this.inPane = inPane;
    }

    /**
     * @return the panes
     */
    public JPanel[] getPanes() {
        return panes;
    }

    /**
     * @param panes the panes to set
     */
    public void setPanes(JPanel[] panes) {
        this.panes = panes;
    }

    /**
     * @return the labels
     */
    public JLabel[] getLabels() {
        return labels;
    }

    /**
     * @param labels the labels to set
     */
    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    /**
     * @return the titleFont
     */
    public Font getTitleFont() {
        return titleFont;
    }

    /**
     * @param titleFont the titleFont to set
     */
    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }

    /**
     * @return the fieldsFont
     */
    public Font getFieldsFont() {
        return fieldsFont;
    }

    /**
     * @param fieldsFont the fieldsFont to set
     */
    public void setFieldsFont(Font fieldsFont) {
        this.fieldsFont = fieldsFont;
    }

    /**
     * @return the primaryBorder
     */
    public Border getPrimaryBorder() {
        return primaryBorder;
    }

    /**
     * @param primaryBorder the primaryBorder to set
     */
    public void setPrimaryBorder(Border primaryBorder) {
        this.primaryBorder = primaryBorder;
    }

    /**
     * @return the secondaryBorder
     */
    public Border getSecondaryBorder() {
        return secondaryBorder;
    }

    /**
     * @param secondaryBorder the secondaryBorder to set
     */
    public void setSecondaryBorder(Border secondaryBorder) {
        this.secondaryBorder = secondaryBorder;
    }

    /**
     * @return the themeColor
     */
    public Color getThemeColor() {
        return themeColor;
    }

    /**
     * @param themeColor the themeColor to set
     */
    public void setThemeColor(Color themeColor) {
        this.themeColor = themeColor;
    }

    /**
     * @return the ia
     */
    public String getIa() {
        return ia;
    }

    /**
     * @param ia the ia to set
     */
    public void setIa(String ia) {
        this.ia = ia;
    }

    public GreenPlanetController getGreenPlanetController() {
        return greenPlanetController;
    }

    /**
     * @return the comboBoxIA
     */
    public JComboBox getComboBoxIA() {
        return comboBoxIA;
    }

    /**
     * @param comboBoxIA the comboBoxIA to set
     */
    public void setComboBoxIA(JComboBox comboBoxIA) {
        this.comboBoxIA = comboBoxIA;
    }
}
