package fr.fanaticstudio.matthis974jump.botsurveillancediscord;

import fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot.BotConfig;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot.LauncherConfig;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.AccountManager;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities.CacheSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author By Matthis974Jump and Matheog974
 * @version 1.0-SNAPSHOT
 * Bot main class
 */
public class MainSystem implements Runnable {

    /**
     * La lib YAML pour lire le fichier de config
     * http://yamlbeans.sourceforge.net/
     */
    private static Yaml yamlFactory = new Yaml();
    /**
     * Connection bdd
     */
    public static CacheSystem cacheSystem;
    /**
     * Le logger principale du luncher
     */
    public static Logger systemLogger = LoggerFactory.getLogger("Launcher System");
    /**
     * La liste des bots
     *
     * @see BotObject
     */
    private static List<BotObject> bots = new ArrayList<>();
    /**
     * La configuration du luncher de bots
     */
    private static LauncherConfig configLauncher = null;
    /**
     * Le scanner pour interagir avec la console
     */
    private static Scanner sc = new Scanner(System.in);
    /**
     * Liste des threads contenant les bots
     */
    private static List<Thread> threads = new ArrayList<>();

    /**
     * @param args Les arguments du programme
     *             Lit la configuration, instancie les threads,
     *             Charge les bots et les lance
     */
    public static void main(String... args) {
        System.setProperty("file.encoding", "UTF-8");
        try {
            configLauncher = loadConfiguration();
        } catch (FileNotFoundException e) {
            systemLogger.error("Error while loading the bot launcher configuration .Creating file");
            try {
                File f = new File("luncherConfig.yml");
                if (!f.createNewFile()) {
                    systemLogger.error("Cant create luncher config file");
                    System.exit(1);
                }
                FileWriter fw = new FileWriter(f);
                fw.write(serialize(new LauncherConfig()));
                fw.flush();
                fw.close();
                systemLogger.info("Complete the configuration file please.");
                System.exit(0);
            } catch (IOException e1) {
                systemLogger.error("Error while writing the config file.", e1);
            }
        }
        systemLogger.info("Here we go !, Lunching threads lunching system for " + configLauncher.bots.size() + " bot(s) !");

        systemLogger.info("Lunched terminal listener interface");

        for (BotConfig bc : configLauncher.bots) {
            systemLogger.info("Instancing bot " + bc.name);
            bots.add(new BotObject(bc));
        }
        for (BotObject bo : bots) {
            systemLogger.info("Post-Init bot " + bo.getName());
            bo.postInit();
            threads.add(new Thread(bo));
            systemLogger.info("Finish thread creating for bot " + bo.getName());
        }
        for (Thread thread : threads) {
            systemLogger.info("Init thread " + thread.getName());
            thread.start();
            systemLogger.info("Finished thread lunching for " + thread.getName() + " thread");
        }
    }

    /**
     * @return The luncher configuration object
     * @throws FileNotFoundException Une erreur est survenue
     * @see LauncherConfig
     */
    private static LauncherConfig loadConfiguration() throws FileNotFoundException {
        Object launcherConfiguration = yamlFactory.load(new FileInputStream(new File("luncherConfig.yml")));
        systemLogger.info("Loaded the launcherConfigFile ");
        return (LauncherConfig) launcherConfiguration;
    }

    /**
     * @param obj L'objet a serialiser
     * @return L'objet serialise
     */
    private static String serialize(Object obj) {
        return yamlFactory.dump(obj);
    }

    /**
     * Le listener pour la fenetre de termial
     */
    @Override
    public void run() {
        String entry = "";
        boolean isOk = true;
        while (isOk) {
            if (sc.hasNext()) entry = sc.next();
            if (entry.equalsIgnoreCase("end")) {
                isOk = false;
            }
        }
        for (BotObject bo :
                bots) {
            bo.stop();
        }
        for (Thread thread :
                threads) {
            thread.interrupt();
        }

        sc.close();
        systemLogger.info("Good bye !");
        System.exit(1);
    }
}
