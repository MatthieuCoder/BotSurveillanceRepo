package fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities;

import net.dv8tion.jda.core.entities.Member;

public class Sanction {
    String reason;
    Member m;

    public Sanction(String reason, Member m) {
        this.reason = reason;
        this.m = m;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Member getM() {
        return m;
    }

    public void setM(Member m) {
        this.m = m;
    }
}
