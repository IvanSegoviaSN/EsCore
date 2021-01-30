
package esmine.escore;

import esmine.comandos.*;
import esmine.eventos.LoginEvento;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class Core extends JavaPlugin implements Listener {
    public static Core plugin;

    @Override
    public void onEnable() {
        Core.plugin = this;
        
        saveDefaultConfig();
        reloadConfig();
        
        Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
        Bukkit.getConsoleSender().sendMessage("§7[§a§lIN§7] §fesCore rev1.0 ha sido lanzado.");
        
        Bukkit.getPluginManager().registerEvents(this, (Plugin)new LoginEvento());
        
        this.getCommand("spawn").setExecutor(new ComandosSpawn(this));
        this.getCommand("setspawn").setExecutor(new ComandosSpawn(this));
        this.getCommand("prueba").setExecutor(new ComandosSpawn(this));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§7[§c§lOUT§7] §fesCore rev1.0 ha sido desactivado.");
    }
    
    public static Core getPlugin() {
        return (Core) getPlugin((Class) Core.class);
    }
    
    public Configuration obtenerConfig() {
        
    }
    
}
