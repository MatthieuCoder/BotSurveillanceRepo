package fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author By Matthis974Jump and Matheog974
 * @version 1.0-SNAPSHOT
 * La configuration generale d'un bot
 */
public class BotConfig {
    /**
     * Le token pour la connection au bot avec JDA
     */
    public String token = "Token ICI";
    /**
     * Le nom (id) du bot
     */
    public String name = "Je te surveille :)";
    /**
     * La liste de mots interdits
     */
    public List<String> disallowedWords = new ArrayList<>();
    /**
     * Le Jeu du bot !
     */
    public String game = "Coucou !";
    /**
     * Le tag (prefix) du bot
     */
    public String tag = "=";
    /**
     * Le role requis pour les comandes modérateur
     */
    public String modoRole = "<ID OF MODOROLE>";

    /**
     * Le constructeur par defaud, definit la config par defaut
     */

    BotConfig() {
        disallowedWords.add("Fuck :)");
        disallowedWords.add("Fuck :)");
    }
}
