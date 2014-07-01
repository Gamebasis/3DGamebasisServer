/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.clientstatelistener;

import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;
import de.gamebasis.server.ServerMain;
import de.gamebasis.player.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class ServerConnectionManager implements ConnectionListener {
    
    protected ServerMain servermain = null;
    protected Server server = null;
    
    protected List<HostedConnection> connections = new ArrayList<HostedConnection>();
    protected static HashMap<Integer,Player> playerlist = new HashMap<Integer,Player>();
    
    public ServerConnectionManager (ServerMain servermain, Server server) {
        this.servermain = servermain;
        this.server = server;
    }
    
    public ServerConnectionManager (Server server) {
        this.server = server;
    }

    public void connectionAdded(Server server, HostedConnection conn) {
        this.connections.add(conn);
        Player player = new Player(conn);
        
        ServerConnectionManager.playerlist.put(conn.getId(), player);
        Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, "Client #" + conn.getId() + " connected.");
    }

    public void connectionRemoved(Server server, HostedConnection conn) {
        this.connections.remove(conn);
        ServerConnectionManager.playerlist.remove(conn.getId());
        
        Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, "Client #" + conn.getId() + " disconnected.");
    }
    
    public static Player getPlayerByConnectionID (int connID) {
        return ServerConnectionManager.playerlist.get(connID);
    }
    
    public static void setPlayerByConnectionID (int connID, Player player) {
        ServerConnectionManager.playerlist.put(connID, player);
    }
    
}
