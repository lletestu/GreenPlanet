package GreenPlanet.Controller.Listener;

import GreenPlanet.Controller.GreenPlanetController;
import GreenPlanet.Model.ManagerBD;
import GreenPlanet.View.MainViewGame.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Action Listener
 * Verify the user inputs then go to the next step.
 * STATUS: DONE
 * LAST UPDATE: 11/05/13
 * @author Jérémy
 */
public class PasswordListener implements ActionListener {
    
    /**
     * WindowBDD source frame
     */
    private WindowBDD myWindow;
    
    /**
     * WindowSPLASH source frame
     */
    private WindowSPLASH myFrame;
    
    /**
     * GreenPlanetController source object
     */
    private GreenPlanetController greenPlanetController;
    
    /**
     * JPassword input
     */
    private char[] input;
    
    /**
     * WindowGAMECONF new menu
     */
    private WindowGAMECONF newOne;
    
    /**
     * BDD url
     */
    private String url;
    
    /**
     * BDD user name
     */
    private String userName;
    
    /**
     * ManagerBD object
     */
    private ManagerBD managerBD;
    
    /**
     * Default constructor
     * @param myWindow is the current object WindowBDD used in the program
     * @param myFrame is the current object WindowSPLASH used in the program
     */
    public PasswordListener(WindowBDD myWindow, WindowSPLASH myFrame) {
        this.myWindow = myWindow;
        this.myFrame = myFrame;
        this.greenPlanetController = myWindow.getGreenPlanetController();
        this.managerBD = greenPlanetController.getManagerBD();
    }
    
    /**
     * Action performed
     * If the user wants to be connected with his BDD:
     *  1) Verify the user inputs
     *  2) Go to the next step if inputs corrects and connection to the BDD activated
     * Else skip this step and go next
     * @param ae is the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        input = myWindow.getPassword().getPassword();
        url = myWindow.getUrlName().getText();
        userName = myWindow.getBddName().getText();

        if (myWindow.isSkip() == false) {
            boolean conection = connectionBD();
            if(conection){
                greenPlanetController.setBdActivate(true);
                newOne = new WindowGAMECONF(myFrame, greenPlanetController);
                System.out.println("connected");
            }

        } else {
            greenPlanetController.setBdActivate(false);
            newOne = new WindowGAMECONF(myFrame, greenPlanetController);
            System.out.println("no connected");
        }
    }
    
    /**
     * Convert Char to String
     * @return the converted String
     */
    private String concatenateChartToString() {
        String passwordStr = "";

        for(int i=0 ; i<input.length;++i){
            passwordStr += input[i];
        }
        return passwordStr;
    }
    
    /**
     * Connect the program to a BDD
     * @return true if the connection has been established
     */
    private boolean connectionBD() {
        String passwordStr = concatenateChartToString();
        boolean conection = false;

        try {
            managerBD.createBD(url, userName, passwordStr);
            conection = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),"Invalid Fields");
            conection = false;
        }
        return conection;
    }
}
