/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gamebasis.player;

import com.jme3.math.Vector3f;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;
import de.gamebasislib.player.IPlayer;
import de.gamebasislib.player.PlayerPos;
import de.gamebasislib.player.PlayerPosMessage;

/**
 *
 * @author Justin
 */
public class Player implements IPlayer {
    
    protected PlayerPos playerpos = null;
    protected HostedConnection conn = null;
    
    protected int playerID = 0;
    
    public Player (HostedConnection conn) {
        this.conn = conn;
    }
    
    public void setPlayerPos (Vector3f pos) {
        this.playerpos = new PlayerPos(pos.getX(), pos.getY(), pos.getZ());
        this.conn.send(new PlayerPosMessage(this.playerpos, this.playerID, this));
    }
    
    public void init () {
        //Login Message schicken
    }
    
    public PlayerPos getPlayerPos () {
        return this.playerpos;
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
    
}
