package fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot.DatabaseConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    HikariDataSource hikariDataSource;

    private HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }

    public Account getAccountFromDatabaseFromId(String id) throws SQLException {
        PreparedStatement s = hikariDataSource.getConnection().prepareStatement("SELECT * FROM accounts WHERE DiscordId = "+id);
        ResultSet rs = s.executeQuery();
        Account a = new Account();
        rs.next();
        a.setName(rs.getString(1));
        rs.next();
        a.setDiscordId(rs.getString(2));
        rs.next();
        a.setWarns(rs.getString(3));
        rs.next();
        a.setBanned(rs.getBoolean(4));
        rs.next();
        a.setSanctions(rs.getString(5));
        rs.next();
        a.setRoles(rs.getString(6));
        rs.next();
        a.setMoneyCount(rs.getInt(7));
        return a;
    }

    public Database(DatabaseConfig dc){
        HikariConfig hc = new HikariConfig();
        hc.setMaximumPoolSize(dc.maxPoolSize);
        hc.setPoolName("BOTPOOL");
        hc.setUsername(dc.user);
        hc.setPassword(dc.password);
        hc.setJdbcUrl("jdbc:mariadb://"+dc.serverAdress+":"+dc.port+"/"+dc.databaseName);
        hikariDataSource = new HikariDataSource(hc);
    }
}
