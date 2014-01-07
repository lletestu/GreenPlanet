package GreenPlanet.View.MainViewGame;

import GreenPlanet.Controller.GreenPlanetController;
import GreenPlanet.Controller.Listener.ComboBoxBDDListener;
import GreenPlanet.Controller.Listener.EntryMouseListener;
import GreenPlanet.Controller.Listener.PasswordListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


/**
 * Action Listener WindowBDD menu/frame
 * Initialise and displays WindowBDD menu/frame
 * STATUS: DONE
 * LAST UPDATE: 11/05/13
 * @author Jérémy
 */
public class WindowBDD extends JFrame implements ActionListener {
    
    /**
     * WindowSPLASH source frame
     */
    private WindowSPLASH myFrame;
    
    /**
     * GreenPlanetController source object
     */
    private GreenPlanetController greenPlanetController;
    
    /**
     * "Submit!" JButton
     */
    private JButton nextStep;
    
    /**
     * "User Name" JTextField
     */
    private JTextField bddName;
    
    /**
     * "URL Name" JTextField
     */
    private JTextField urlName;
    
    /**
     * "Password" JPasswordField
     */
    private JPasswordField password;
    
    /**
     * Title font
     */
    private Font titleFont;
    
    /**
     * TextFields font
     */
    private Font fieldsFont;
    
    /**
     * Primaty border
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
     * Secondary JPanel
     */
    private JPanel inPane;
    
    /**
     * Menu JLabels
     */
    private JLabel[] labels;
    
    /**
     * "inPane" additionnal JPanels
     */
    private JPanel[] panes;
    
    /**
     * Active or not the BDD JComboBox
     */
    private JComboBox choice;
    
    /**
     * ComboBox labels
     */
    private String[] selection;
    
    /**
     * Additionnal theme font
     */
    private Font font = new Font("Arial",Font.PLAIN,12);
    
    /**
     * Additionnal color font
     */
    private Color colorFont = new Color(66,67,69);
    
    /**
     * True if the user doesnt want to active the BDD feature
     */
    private boolean skip = false;

    /**
     * Default constructor
     * Initialises the main components of the WindowBDD menu
     * @param myFrame is the WindowSPLASH source frame
     */
    public WindowBDD(WindowSPLASH myFrame) {
        this.greenPlanetController = myFrame.getGreenPlanetController();

        this.themeColor = new Color(255,240,206);
        this.myFrame = myFrame;
        this.password = new JPasswordField(14);
        this.nextStep = new JButton("Submit!");
        this.nextStep.addActionListener(new PasswordListener(this,myFrame));
        this.bddName = new JTextField();
        this.bddName.setToolTipText("User name for the BDD");
        this.urlName = new JTextField();
        this.urlName.setToolTipText("Tape the correct BDD URL");
        this.titleFont = new Font("Bazar",Font.BOLD,30);
        this.fieldsFont = new Font("Bazar",Font.BOLD,20);
        this.primaryBorder = BorderFactory.createLineBorder(themeColor,10);
        this.secondaryBorder = BorderFactory.createLineBorder(themeColor,3);
        this.pageTitle = BorderFactory.createTitledBorder(primaryBorder, "BDD LOGIN", TitledBorder.CENTER, TitledBorder.TOP, titleFont, new Color(84,84,84));

        this.selection = new String[2];
        this.selection[0] = "Active BDD";
        this.selection[1] = "Skip BDD";

        this.choice = new JComboBox(selection);
        this.choice.setMaximumSize(new Dimension(300,30));
        this.choice.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.choice.addActionListener(new ComboBoxBDDListener(this));
        this.choice.setFont(font);
        this.choice.setBackground(themeColor);
        this.choice.setForeground(colorFont);
        this.choice.setToolTipText("Active the BDD for the HighScores");

        this.inPane = new JPanel();
        this.inPane.setMaximumSize(new Dimension(300,150));
        this.inPane.setLayout(new BoxLayout(inPane, BoxLayout.Y_AXIS));
        this.inPane.setBackground(themeColor);
        this.inPane.setOpaque(true);
        this.inPane.setBorder(secondaryBorder);

        this.labels = new JLabel[3];
        this.labels[0] = new JLabel("USERNAME");
        this.labels[1] = new JLabel("PASSWORD");
        this.labels[2] = new JLabel("URL");

        for(int i=0; i<3; i++) {
            this.labels[i].setFont(fieldsFont);
            this.labels[i].setForeground(new Color(84,84,84));
        }

        this.panes = new JPanel[3];

        for(int j=0; j<3; j++) {
            this.panes[j] = new JPanel();
            this.panes[j].setLayout(new FlowLayout(FlowLayout.LEFT));
            this.panes[j].setOpaque(false);
        }
    }

    /**
     * Constructs and displays the components for the WindowBDD menu
     */
    private void constructWindow() {
        myFrame.remove(myFrame.getLauncherB());

        choice.setSelectedItem("Active BDD");

        urlName.setPreferredSize(new Dimension(158,28));
        bddName.setPreferredSize(new Dimension(158,28));
        password.setPreferredSize(new Dimension(0,28));

        labels[0].setPreferredSize(new Dimension(100,28));
        labels[1].setPreferredSize(new Dimension(100,28));
        labels[2].setPreferredSize(new Dimension(100,28));

        bddName.setText("Click to change");
        bddName.addMouseListener(new EntryMouseListener(bddName));
        urlName.setText("jdbc:mysql://localhost:8889/GreenPlanet");


        panes[0].add(labels[0]);
        panes[0].add(bddName);
        panes[1].add(labels[1]);
        panes[1].add(password);
        panes[2].add(labels[2]);
        panes[2].add(urlName);

        inPane.setMaximumSize(new Dimension(300,200));
        inPane.add(panes[2]);
        inPane.add(panes[0]);
        inPane.add(panes[1]);

        inPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextStep.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextStep.setMaximumSize(new Dimension(200,30));

        myFrame.getGameOptions().setBorder(pageTitle);
        myFrame.getGameOptions().setAlignmentX(Component.CENTER_ALIGNMENT);
        myFrame.getGameOptions().add(choice);
        myFrame.getGameOptions().add(Box.createRigidArea(new Dimension(0,50)));
        myFrame.getGameOptions().add(inPane);
        myFrame.getGameOptions().add(Box.createRigidArea(new Dimension(0,50)));
        myFrame.getGameOptions().add(nextStep);

        myFrame.validate();
    }

    /**
     * Action performed
     * Creates the game JMenuBar and creates the WindowBDD menu
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        JMenuBar my = myFrame.createMenuBar();
        myFrame.setJMenuBar(my);
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
     * @return the nextStep
     */
    public JButton getNextStep() {
        return nextStep;
    }

    /**
     * @param nextStep the nextStep to set
     */
    public void setNextStep(JButton nextStep) {
        this.nextStep = nextStep;
    }

    /**
     * @return the bddName
     */
    public JTextField getBddName() {
        return bddName;
    }

    /**
     * @param bddName the bddName to set
     */
    public void setBddName(JTextField bddName) {
        this.bddName = bddName;
    }

    /**
     * @return the password
     */
    public JPasswordField getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(JPasswordField password) {
        this.password = password;
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
     * @return the choice
     */
    public JComboBox getChoice() {
        return choice;
    }

    /**
     * @param choice the choice to set
     */
    public void setChoice(JComboBox choice) {
        this.choice = choice;
    }

    /**
     * @return the selection
     */
    public String[] getSelection() {
        return selection;
    }

    /**
     * @param selection the selection to set
     */
    public void setSelection(String[] selection) {
        this.selection = selection;
    }

    /**
     * @return the urlName
     */
    public JTextField getUrlName() {
        return urlName;
    }

    /**
     * @param urlName the urlName to set
     */
    public void setUrlName(JTextField urlName) {
        this.urlName = urlName;
    }

    /**
     * @return the skip
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     * @param skip the skip to set
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public GreenPlanetController getGreenPlanetController() {
        return greenPlanetController;
    }
}
