/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esmine.escore;
import java.io.File;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
/**
 *
 * @author ivans
 */
public class GestorConfig {
    private Core c;
    private static GestorConfig instancia = new GestorConfig();
    private File archivo;
    private FileConfiguration archivoConf;
    
    public static GestorConfig getInstance() {
        return instancia;
    }
    public void gestorArchivo(Core p) {
        this.c = p;
        this.archivo = new File("EsCore", "config.yml");
        boolean archivoExiste = archivoCreado();
        
        if (!archivoExiste)
            crearArchivo();
        
        this.archivoConf = (FileConfiguration) YamlConfiguration.loadConfiguration(this.archivo);
    }

    private boolean archivoCreado() {
        return this.archivo.exists();
    }

    private void crearArchivo() {
        try {
            this.archivo.createNewFile();
            seteo("mundo", "EsMine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void guardarArchivo() {
        try {
            this.archivoConf.save(this.archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private boolean estaSetado(String path) {
        return this.archivoConf.isSet(path);
    }
    
    private void seteo(String path, Object obj) {
        this.archivoConf.set(path, obj);
    }
}
