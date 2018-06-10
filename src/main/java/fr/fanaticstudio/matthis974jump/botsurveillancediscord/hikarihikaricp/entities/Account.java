package fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities;


import fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.Serialiser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Account {

    private String name;


    private String discordId;


    private String warns;


    private boolean isBanned;


    private String sanctions;


    private String roles;


    private int moneyCount;

    public Account(String name, String discordId, List<Warn> warns, boolean isBanned, List<Sanction> sanctions, List<String> roles, Date lastConnection, Date lastMessage, int moneyCount) {

        this.name = name;
        this.discordId = discordId;
        this.warns = Serialiser.serializeList(warns);
        this.isBanned = isBanned;
        this.sanctions = Serialiser.serializeList(sanctions);
        this.roles = Serialiser.serializeList(roles);
        this.moneyCount = moneyCount;
    }
    public Account(String name, String discordId,String warns, boolean isBanned, String sanctions, String roles, int moneyCount) {

        this.name = name;
        this.discordId = discordId;
        this.warns = warns;
        this.isBanned = isBanned;
        this.sanctions = sanctions;
        this.roles = roles;
        this.moneyCount = moneyCount;
    }

    public Account() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public List<Warn> getWarns() {
        return Serialiser.deserializeWarnList(this.warns);
    }

    public void setWarns(List<Warn> warns) {
        this.warns = Serialiser.serializeList(warns);
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public List<Sanction> getSanctions() {
        return Serialiser.deserializeSanctionList(this.sanctions);
    }

    public void setSanctions(List<Sanction> sanctions) {
        this.sanctions = Serialiser.serializeList(sanctions);
    }

    public List<String> getRoles() {
        return Serialiser.deserializeRoleList(this.roles);
    }

    public void setRoles(List<String> roles) {
        this.roles = Serialiser.serializeList(roles);
    }

    public int getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(int moneyCount) {
        this.moneyCount = moneyCount;
    }

    public void addWarn(Warn w) {
        ArrayList al = Serialiser.deserializeWarnList(warns);
        al.add(w);
        Serialiser.serializeList(al);
    }

    public void setWarns(String warn) {
        this.warns = warn;
    }

    public  void setSanctions(String sanctions){
        this.sanctions = sanctions;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
