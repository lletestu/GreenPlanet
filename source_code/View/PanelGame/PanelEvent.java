/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 04/05/13
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */

package GreenPlanet.View.PanelGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import GreenPlanet.Model.Player.PlayerData;
import GreenPlanet.Model.UtilityFunction.GetPlayer;
import greenplanetclient.Event;
import greenplanetclient.GameStateEnum;
import greenplanetclient.Player;

/**
 * Class to define panel event
 */
public class PanelEvent {

    /**
     * Main panel to display  information
     */
    JPanel panelEvent;
    /**
     * Text area for contain the header
     */
    JTextArea headerText = new JTextArea();
    /**
     * Text area for contain the event
     */
    JTextArea eventText = new JTextArea();

    /**
     * Represents the width of panel
     */
    private final int WIDTH = 810;
    /**
     * Represents the height of panel
     */
    private final int HEIGHT = 400;
    /**
     * Font for header
     */
    private Font fontHeader = new Font("Bazar", Font.BOLD, 30);
    /**
     * Font for data
     */
    //private Font fontData = new Font("Bazar", Font.PLAIN, 20); // for MAC
    private Font fontData = new Font("Arial", Font.PLAIN, 20); // for Windows
    /**
     * Color font header
     */
    private Color colorHeader = new Color(80, 150, 32);
    /**
     * Color font data
     */
    private Color colorData = new Color(66, 67, 69);

    /**
     * Enumeration of event type
     */
    private static enum enumTypeEvent {WORST_POLLUTER, BEST_GREEN, SOLAR_BONUS, WIND_BONUS, WATER_BONUS}

    /**
     * Hash map to select the current event type
     */
    private static final HashMap<String, enumTypeEvent> selectEventType = new HashMap<String, enumTypeEvent>() {
        {
            put("worst_polluter", enumTypeEvent.WORST_POLLUTER);
        }

        {
            put("best_green", enumTypeEvent.BEST_GREEN);
        }

        {
            put("solar_bonus", enumTypeEvent.SOLAR_BONUS);
        }

        {
            put("wind_bonus", enumTypeEvent.WIND_BONUS);
        }

        {
            put("water_bonus", enumTypeEvent.WATER_BONUS);
        }
    };

    /**
     * Constructor
     */
    public PanelEvent() {
        panelEvent = new JPanel(new BorderLayout());
        panelEvent.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panelEvent.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        panelEvent.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        panelEvent.setOpaque(false);

        initializeHeaderText();
        initializeDataText();

        panelEvent.add(headerText, BorderLayout.PAGE_START);
        panelEvent.add(eventText, BorderLayout.CENTER);
    }

    /**
     * This method allows to initialize header
     */
    private void initializeHeaderText() {
        headerText.setFont(fontHeader);
        headerText.setOpaque(false);
        headerText.setForeground(colorHeader);
        headerText.setSize(new Dimension(WIDTH, 50));
        headerText.setEditable(false);
        headerText.setText("EVENT\n");
    }

    /**
     * This method allows to initialize data
     */
    private void initializeDataText() {
        eventText.setFont(fontData);
        eventText.setOpaque(false);
        eventText.setForeground(colorData);
        eventText.setSize(new Dimension(WIDTH, 350));
        eventText.setEditable(false);
    }

    /**
     * This methos allows to update data
     * @param event represents all events on the current game
     * @param price represents the current price of the power
     * @param p represent all player presents int he current game
     * @param gameStateEnum represents the current state of the game
     * @param winnerPlayer represent the player that win this game
     */
    public void updateDataEvent(ArrayList<Event> event, float price, ArrayList<Player> p, GameStateEnum gameStateEnum, Object[] winnerPlayer) {
        eventText.setText("");

        if (!gameStateEnum.equals(GameStateEnum.RUNNING)) updateFinishGame(winnerPlayer);

        else {
            eventText.append("POWER PRICE : " + String.valueOf(price) + " $\n\n");
            appendData(event, p);
        }
    }

    /**
     * This method allows to append data on text area data
     * @param event represents all events on the current game
     * @param p represent all player presents int he current game
     */
    private void appendData(ArrayList<Event> event, ArrayList<Player> p) {
        String namePlayer;

        for (Event e : event) {

            switch (selectEventType.get(e.getType())) {
                case WORST_POLLUTER:
                    namePlayer = GetPlayer.getPlayerName(e.getPlayerId(), p);
                    eventText.append(namePlayer + " : " + e.getMessage() + "\n\n");
                    break;
                case BEST_GREEN:
                    namePlayer = GetPlayer.getPlayerName(e.getPlayerId(), p);
                    eventText.append(namePlayer + " : " + e.getMessage() + "\n\n");
                    break;
                case SOLAR_BONUS:
                    eventText.append("SOLAR BONUS : " + e.getMessage() + "\n\n");
                    break;
                case WIND_BONUS:
                    eventText.append("WIND BONUS : " + e.getMessage() + "\n\n");
                    break;
                case WATER_BONUS:
                    eventText.append("WATER BONUS : " + e.getMessage() + "\n\n");
                    break;
            }
        }

    }

    /**
     * This method allows to update panel when the game is finished
     * @param winnerPlayer represent the player that win this game
     */
    private void updateFinishGame(Object[] winnerPlayer) {
        Integer lastTurn = (Integer) winnerPlayer[0];
        PlayerData winPlayer = (PlayerData) winnerPlayer[1];

        headerText.setText("\n! GAME FINISHED !\n");
        eventText.append("AT TURN : " + String.valueOf(lastTurn) + "\n\n");
        eventText.append("WINNER : " + winPlayer.getName() + "\n\n");
        eventText.append("CASH : " + winPlayer.getCash() + " $" + "\n\n");
        eventText.append("POWER NEED : " + winPlayer.getPowerNeed() + "\n\n");
        eventText.append("GREEN RANK : " + winPlayer.getGreenRank() + "\n\n");
    }

    /**
     * Get the main panel
     * @return the current panel
     */
    public JPanel getPanelEvent() {
        return panelEvent;
    }
}
