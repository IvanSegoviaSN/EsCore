/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esmine.comandos;

import esmine.escore.Core;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author ivans
 */
public class OnCommands implements CommandExecutor {
    
    private World w; private float yaw, pit;
   
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {
        
        if (!(cs instanceof Player)) {
            cs.sendMessage("§cEste comando debe ejecutarse desde el cliente.");
            return false;
            
        } else {
            String comando = cmd.getName();
            Player p = ((Player) cs);
            
            if (comando.equalsIgnoreCase("spawn") && (args == null || args.length < 1)) {
                this.w = p.getWorld();
                p.teleport(getLocSpawn());
                
                cs.sendMessage("§cHas sido teletransportado al iniciooo.");
                return true;
                
            } else if (comando.equalsIgnoreCase("setspawn") && (args == null || args.length < 1)) {
                this.w = p.getWorld();
                
                this.pit = p.getLocation().getPitch();
                this.yaw = p.getLocation().getYaw();
                
                this.w.setSpawnLocation(getLocSpawn());
                cs.sendMessage("§cSpawn colocado con exito: " + w.getName() + " " + pit + " " + yaw);
                
                Core.getPlugin().getConfig().set("gestionSpawn.x", p.getLocation().getBlockX());
                Core.getPlugin().getConfig().set("gestionSpawn.y", p.getLocation().getBlockY());
                Core.getPlugin().getConfig().set("gestionSpawn.z", p.getLocation().getBlockZ());
                Core.getPlugin().getConfig().set("gestionSpawn.yaw", p.getLocation().getYaw());
                Core.getPlugin().getConfig().set("gestionSpawn.pit", p.getLocation().getPitch());
                
                Core.getPlugin().saveConfig();
                Core.getPlugin().reloadConfig();
                
                return true;
                
            } else if (comando.equalsIgnoreCase("prueba") && (args == null || args.length < 1)) {

                cs.sendMessage("§cComando " + cmd.getName() + ": exitooo");
                cs.sendMessage(ChatColor.translateAlternateColorCodes('&', Core.getPlugin().getConfig().getString("mensajes.ejecucion")));
                return true;
                
            } else {
                if (args.length > 0) {
                    System.out.println("§cEste comando no necesita ningun argumento.");
                    return false;
                }
                
                return false; // FIN
            }
        }
    }
    
    // Llega el objeto world y envia las coordenadas del spawn.
    public Location getLocSpawn(){
        return new Location((this.w),
                (Core.getPlugin().getConfig().getDouble("gestionSpawn.x")),
                (Core.getPlugin().getConfig().getDouble("gestionSpawn.y")),
                (Core.getPlugin().getConfig().getDouble("gestionSpawn.z")),
                (Core.getPlugin().getConfig().getLong("gestionSpawn.yaw")),
                (Core.getPlugin().getConfig().getLong("gestionSpawn.pit")));
    }
    
}
