/**
 * Created with IntelliJ IDEA.
 * User: JM
 * Date: 20/04/13
 * Time: 00:02
 * To change this template use File | Settings | File Templates.
 * 
 */
package GreenPlanet.Model.Player;

import greenplanetclient.Game;
import greenplanetclient.Player;
import java.util.ArrayList;

public class PlayerAlive {
    
    public ArrayList<Player> arrayAlivePlayer =  new ArrayList() ;
    
    public PlayerAlive(Game g)
    {
        for(Player p : g.getPlayers())
        {
            if(p.getState().equals(p.getState().ALIVE))
            {
                arrayAlivePlayer.add(p);
            }
        }
    }
    
    public ArrayList<Player> getListPlayerAlive()
    {
        return this.arrayAlivePlayer;
    }
    
}
