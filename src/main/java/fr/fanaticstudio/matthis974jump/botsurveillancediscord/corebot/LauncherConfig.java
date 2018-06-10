package fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author By Matthis974Jump and Matheog974
 * @version 1.0-SNAPSHOT
 * Config of the luncher of the bot(s)
 */
public class LauncherConfig {
    /**
     * Objet de configuration de la base de donnee
     */
    public DatabaseConfig dc = new DatabaseConfig();
    /**
     * Max de memoire alouee
     *
     * @deprecated Pas encore utulisee
     */
    public int maxMemory = 1024;
    /**
     * Active le mode caca :)
     *
     * @deprecated Pas encore utulisee
     */
    public boolean wtfIsThat = false;
    /**
     * Liste des bots
     *
     * @see BotConfig
     */
    public List<BotConfig> bots = new ArrayList<>();

    /**
     * Le constructeur par defaud, definit la config par defaut
     */
    public LauncherConfig() {
        bots.add(new BotConfig());
    }
}
