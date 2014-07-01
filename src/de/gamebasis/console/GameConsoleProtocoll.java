/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.console;

import com.jme3.math.Vector3f;
import com.jme3.network.HostedConnection;
import de.gamebasislib.console.GameConsoleMessage;
import de.gamebasislib.player.IPlayer;

/**
 *
 * @author Justin
 */
public class GameConsoleProtocoll implements Runnable {
    
    protected GameConsoleMessage gameconsolemessage = null;
    protected IPlayer player = null;
    protected HostedConnection conn = null;
    
    public GameConsoleProtocoll (GameConsoleMessage message, IPlayer player, HostedConnection conn) {
        this.gameconsolemessage = message;
        this.player = player;
        this.conn = conn;
    }
    
    @Override
    public void run () {
        String message = this.gameconsolemessage.getMessage();
        
        String[] params = message.split(" ");
        
        if (params[0].equals("setOwnPosition")) {
            if (params.length >= 3) {
                int x = Integer.parseInt(params[1]);
                int y = Integer.parseInt(params[2]);
                int z = 100;
                
                //Neue Playerposition setzen
                player.setPlayerPos(new Vector3f(x, y, z));
                
                this.conn.send(new GameConsoleMessage("PlayerPos set to " + x + ", " + y + ", " + z + "!"));
            }
        }
    }
    
}
