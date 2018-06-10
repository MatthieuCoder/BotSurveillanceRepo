package fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot;


/**
 * @author By Matthis974Jump and Matheog974
 * @version 1.0-SNAPSHOT
 * La configuration de la base de donnee
 */
public class DatabaseConfig {
    /**
     * L'adresse de la base de donnee
     */
    public String serverAdress = "localhost";
    /**
     * Le port de la base de donnee
     */
    public int port = 3307;
    /**
     * Le nom de la base de donnee
     */
    public String databaseName = "botsdiscord";
    /**
     * L'utulisateur utulise pour la connection a la base de donnee
     */
    public String user = "xd";
    /**
     * L'utulisateur de la base de donnee
     */
    public String password = "xd";
    /**
     * La taille maximale de ma pool HikariCP
     */
    public int maxPoolSize = 20;
}
