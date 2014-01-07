package GreenPlanet.View.MainViewGame;

import GreenPlanet.Controller.GreenPlanetController;
import GreenPlanet.Controller.Listener.ExitListener;
import GreenPlanet.Controller.Listener.GoToWebsiteListener;
import GreenPlanet.Images.Images;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * Splash window of the game
 * Initialise principal elements of the main window.
 * Two choices for the user : exit or play.
 * Also an ActionListener that create an new WindowSPLASH
 * STATUS: DONE
 * LAST UPDATE: 10/05/13
 * @author Jérémy
 */
public class WindowSPLASH extends JFrame implements ActionListener {

    /**
     * GreenPlanetController object : controller to create a new game
     */
    private GreenPlanetController greenPlanetController;
    
    /**
     * Background (main) panel
     */
    private JPanel fontPanel;
    
    /**
     * "Play" JButton
     */
    private JButton launcherB;
    
    /**
     * "Quit" JButton
     */
    private JButton leaveB;
    
    /**
     * Central panel: contains the additionnal menus of WindowBDD et WindowGAMECONF
     */
    private JPanel gameOptions;
    
    /**
     * Game JMenuBar, not visible until WindowBDD appears
     */
    private JMenuBar menuBar;
    
    /**
     * Menu of the menu bar
     */
    private JMenu menu;
    
    /**
     * Frame icon
     */
    private ImageIcon gpIcon;
    
    /**
     * Images object to get images ressources
     */
    private Images i = new Images();
    
    /**
     * Theme font
     */
    private Font font = new Font("Bazar",Font.PLAIN,12);
    
    /**
     * JButtons background color
     */
    private Color colorBackgroundButton =  new Color(253,238,195);
    
    /**
     * JButtons font color
     */
    private Color colorFontButton = new Color(66,67,69);
    
    /**
     * JMenu items background color
     */
    private Color colorJmenu = new Color(253,238,195);

    /**
     * Default constructor
     * @param greenPlanetController is the GreenPlanetController object
     */
    public WindowSPLASH(GreenPlanetController greenPlanetController) {
        this.greenPlanetController = greenPlanetController;
        this.defaultOptions();
        this.createWindow();
    }
    
    /**
     * Action performed
     * Close the current frame, create and displays a new WindowSPLASH for a new game
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.dispose();
        WindowSPLASH newOne = new WindowSPLASH(new GreenPlanetController());
    }
    
    /**
     * Create the WindowSPLASH frame
     * @exception IOException
     */
    private void createWindow() {

        try {
            launcherB=new JButton("PLAY");
            launcherB.setMaximumSize(new Dimension(130,60));
            launcherB.setBackground(colorBackgroundButton);
            launcherB.setForeground(colorFontButton);
            launcherB.setFont(font);
            launcherB.addActionListener(new WindowBDD(this));

            leaveB=new JButton("QUIT");
            leaveB.setMaximumSize(new Dimension(130,60));
            leaveB.setBackground(colorBackgroundButton);
            leaveB.setForeground(colorFontButton);
            leaveB.setFont(font);
            leaveB.addActionListener(new ExitListener());
            

            fontPanel = new FontPanelSplashWindow("fontImg3.jpg","retroTitre.png","subtitles.png");
            fontPanel.setLayout(new BoxLayout(fontPanel, BoxLayout.Y_AXIS));

            gameOptions = new JPanel();
            gameOptions.setOpaque(false);
            gameOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameOptions.setLayout(new BoxLayout(gameOptions, BoxLayout.Y_AXIS));
            //gameOptions.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            gameOptions.setMaximumSize(new Dimension(350,this.getHeight()/2));

            launcherB.setAlignmentX(Component.CENTER_ALIGNMENT);
            leaveB.setAlignmentX(Component.CENTER_ALIGNMENT);

            fontPanel.add(Box.createRigidArea(new Dimension(0,this.getHeight()/4+80)));
            fontPanel.add(gameOptions);
            fontPanel.add(launcherB);
            fontPanel.add(Box.createRigidArea(new Dimension(0,10)));
            fontPanel.add(leaveB);

            this.setContentPane(fontPanel);
            this.setVisible(true);

        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    
    /**
     * Game frame default options
     */
    private void defaultOptions() {

        // Frame Default Options
        setFocusable(true);
        this.setTitle("GREEN PLANET");
        this.setSize(1280,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Icon for the frame
        gpIcon = new ImageIcon(i.getClass().getResource("logo3.png"));
        this.setIconImage(gpIcon.getImage());
    }
    
    /**
     * Create the JMenuBar of the game
     * @return the created JMenuBar
     */
    public JMenuBar createMenuBar() {
        JMenuItem menuItem;
        Icon icon;

        //Create the menu bar
        menuBar = new JMenuBar();
        menuBar.setBackground(colorBackgroundButton);
        menuBar.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        //Create the menu included in the menu bar
        menu = new JMenu("Menu");
        menu.setBackground(colorBackgroundButton);
        menu.getAccessibleContext().setAccessibleDescription("The game menu!");
        menu.setToolTipText("The game menu");

        //Create the menu items

        // "New Game"
        menuItem = new JMenuItem("New game");
        menuItem.setBackground(colorJmenu);
        menuItem.addActionListener(this);
        icon = new ImageIcon(i.getClass().getResource("icon1.png"));
        menuItem.setIcon(icon);
        menu.add(menuItem);

        //menu.addSeparator();
        JSeparator jSeparator = new JSeparator();
        jSeparator.setBackground(colorJmenu);
        menu.add(jSeparator);

        // "High Scores"
        menuItem = new JMenuItem("High Scores");
        menuItem.setBackground(colorJmenu);
        menuItem.addActionListener(new WindowHighScore(this));
        icon = new ImageIcon(i.getClass().getResource("icon4.png"));
        menuItem.setIcon(icon);
        menu.add(menuItem);

        // "Game rules"
        menuItem = new JMenuItem("Game rules");
        menuItem.setBackground(colorJmenu);
        menuItem.addActionListener(new WindowGAMERULES(this));
        icon = new ImageIcon(i.getClass().getResource("icon3.png"));
        menuItem.setIcon(icon);
        menu.add(menuItem);

        // "Go to the game website"
        menuItem = new JMenuItem("Go to the game website");
        menuItem.setBackground(colorJmenu);
        menuItem.addActionListener(new GoToWebsiteListener());
        icon = new ImageIcon(i.getClass().getResource("icon2.gif"));
        menuItem.setIcon(icon);
        menu.add(menuItem);

        // "Credits"
        menuItem = new JMenuItem("Credits");
        menuItem.setBackground(colorJmenu);
        menuItem.addActionListener(new WindowCREDITS(this));
        icon = new ImageIcon(i.getClass().getResource("emailIcon.png"));
        menuItem.setIcon(icon);
        menu.add(menuItem);

        // "Leave the game"
        JSeparator jSeparator2 = new JSeparator();
        jSeparator2.setBackground(colorJmenu);
        menu.add(jSeparator2);

        menuItem = new JMenuItem("Leave the game");
        menuItem.setBackground(colorJmenu);
        menuItem.addActionListener(new ExitListener());
        icon = new ImageIcon(i.getClass().getResource("icon5.png"));
        menuItem.setIcon(icon);
        menu.add(menuItem);

        menu.getPopupMenu().setBackground(colorJmenu);

        menuBar.add(menu);

        return menuBar;
    }
    
    /**
     * @return the fontPanel
     */
    public JPanel getFontPanel() {
        return fontPanel;
    }

    /**
     * @param fontPanel the fontPanel to set
     */
    public void setFontPanel(JPanel fontPanel) {
        this.fontPanel = fontPanel;
    }

    /**
     * @return the launcherB
     */
    public JButton getLauncherB() {
        return launcherB;
    }

    /**
     * @param launcherB the launcherB to set
     */
    public void setLauncherB(JButton launcherB) {
        this.launcherB = launcherB;
    }

    /**
     * @return the leaveB
     */
    public JButton getLeaveB() {
        return leaveB;
    }

    /**
     * @param leaveB the leaveB to set
     */
    public void setLeaveB(JButton leaveB) {
        this.leaveB = leaveB;
    }

    /**
     * @return the gameOptions
     */
    public JPanel getGameOptions() {
        return gameOptions;
    }

    /**
     * @param gameOptions the gameOptions to set
     */
    public void setGameOptions(JPanel gameOptions) {
        this.gameOptions = gameOptions;
    }

    /**
     * @param menuBar the menuBar to set
     */
    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }
    
    /**
     * @return the greenPlanetController
     */
    public GreenPlanetController getGreenPlanetController() {
        return greenPlanetController;
    }
}
