package fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities;

import fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot.LauncherConfig;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CacheSystem {
    private Database database;

    private Map<String,Account> loadedAccounts = new HashMap<>();

    public Account getAccountofUserById(String id) throws SQLException {
        if(loadedAccounts.containsKey(id)) return loadedAccounts.get(id);
        return database.getAccountFromDatabaseFromId(id);
    }

    public CacheSystem(LauncherConfig launcherConfig){
        this.database = new Database(launcherConfig.dc);
    }
}
