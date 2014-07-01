/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.console;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import de.gamebasis.clientstatelistener.ServerConnectionManager;
import de.gamebasis.player.Player;
import de.gamebasislib.console.GameConsoleEvent;
import de.gamebasislib.console.GameConsoleMessage;
import de.gamebasislib.event.GameEventManager;

/**
 *
 * @author Justin
 */
public class GameConsole implements MessageListener<HostedConnection> {

    @Override
    public void messageReceived(HostedConnection source, Message message) {
        if (message instanceof GameConsoleMessage) {
            int clientID = source.getId();
            Player player = ServerConnectionManager.getPlayerByConnectionID(clientID);
            
            GameConsoleMessage gameconsolemessage = (GameConsoleMessage) message;
            
            //Event werfen
            GameConsoleEvent event = new GameConsoleEvent(gameconsolemessage.getMessage(), player);
            GameEventManager.raiseEvent(event);
            
            //GameConsole Protocoll abarbeiten
            GameConsoleProtocoll gameconsoleprotocoll = new GameConsoleProtocoll(gameconsolemessage, player, source);
            Thread thread = new Thread(gameconsoleprotocoll);
            thread.start();
        }
    }
    
}
