package de.gamebasis.server;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.JmeContext;
import de.gamebasis.clientstatelistener.ServerConnectionManager;
import de.gamebasis.console.GameConsole;
import de.gamebasis.gamepluginsync.GamePluginListMessage;
import de.gamebasis.pluginsystem.GamePluginManager;
import de.gamebasis.serverlistener.ServerListener;
import de.gamebasislib.console.GameConsoleMessage;
import de.gamebasislib.database.Database;
import de.gamebasislib.gameworld.GameWorldHeightMap;
import de.gamebasislib.player.PlayerPosMessage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * test
 * @author normenhansen
 */
public class ServerMain extends SimpleApplication {
    
    protected Server server = null;
    protected int port = 6143;
    
    protected ServerConnectionManager serverconnectionlistener = null;
    protected GameConsole gameconsole = new GameConsole();

    public static void main(String[] args) {
        //Neue Instanz erstellen
        ServerMain app = new ServerMain();
        
        Serializer.registerClass(GameWorldHeightMap.class);
        Serializer.registerClass(PlayerPosMessage.class);
        Serializer.registerClass(GameConsoleMessage.class);
        Serializer.registerClass(GamePluginListMessage.class);
        
        //Server im Headless Mode starten
        /*
        * A Headless SimpleApplication executes the simpleInitApp() method
        * and runs the update loop normally. But the application
        * does not open a window, and it does not listen to user input.
        * This is the typical behavior for a server application.  
        */
        app.start(JmeContext.Type.Headless); // headless type for servers!
    }

    @Override
    public void simpleInitApp() {
        try {
            //Server starten
            this.server = Network.createServer(this.port);
            this.server.start();
            
            //MessageListener hinzufügen
            this.server.addMessageListener(new ServerListener(), GameWorldHeightMap.class);
            this.server.addMessageListener(this.gameconsole, GameConsoleMessage.class);
            
            //ConnectionListener hinzufügen
            this.serverconnectionlistener = new ServerConnectionManager(this, this.server);
            
            //Gameplugins laden
            GamePluginManager.loadGamePlugins("./ext/server");
            GamePluginManager.simpleInitApp(this);
        } catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
        //PlayerPos setzen
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    @Override
    public void destroy() {
        //Server beenden und GameWorld speichern
        this.server.close();
        Database.getInstance().close();
        super.destroy();
    }
}
