package fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp;

import fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot.LauncherConfig;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities.CacheSystem;

import java.sql.SQLException;

public class MainTest {
    private static CacheSystem cs = new CacheSystem(new LauncherConfig());

    public static void main(String[] args) throws SQLException {
        System.out.println(cs.getAccountofUserById("1").getName());
    }
}
