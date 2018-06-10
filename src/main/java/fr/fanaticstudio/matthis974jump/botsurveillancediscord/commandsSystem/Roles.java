package fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem;


import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.util.ArrayList;
import java.util.List;

public enum Roles {
    FONDATEUR(8),
    RESP_ADMIN(7),
    ADMIN(6),
    RESP_MODO(5),
    MODO(4),
    USER_DONNATOR(3),
    USER_FRIEND(2),
    USER_VIP(1),
    SIMPLE_USER(0);
    String roleId;
    int level;

    Roles(int level) {
        this.level = level;
    }

    private static boolean containsString(List<String> l, String s) {
        for (String str :
                l) {
            if (s.equalsIgnoreCase(str)) return true;
        }
        return false;
    }

    public static Roles getUserLevel(Member member) {
        List<String> rolesId = new ArrayList<>();
        for (Role r : member.getRoles()) rolesId.add(r.getId());
        for (Roles r : values()) {
            if (containsString(rolesId, r.getRoleId())) {
                return r;
            }
        }
        return Roles.SIMPLE_USER;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public int getLevel() {
        return level;
    }
}
