/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.serverlistener;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import de.gamebasislib.console.GameConsoleMessage;
import de.gamebasislib.gameworld.GameWorldHeightMap;

/**
 *
 * @author Justin
 */
public class ServerListener implements MessageListener<HostedConnection> {

    public void messageReceived(HostedConnection source, Message message) {
        if (message instanceof GameWorldHeightMap) {
            //Das Terrain und damit die HeightMap wurde geändert
            GameWorldHeightMap helloMessage = (GameWorldHeightMap) message;
            
            int clientID = source.getId();
        } else if (message instanceof GameConsoleMessage) {
            GameConsoleMessage gameConsoleMessage = (GameConsoleMessage) message;
            int clientID = source.getId();
        }
    }
    
}
