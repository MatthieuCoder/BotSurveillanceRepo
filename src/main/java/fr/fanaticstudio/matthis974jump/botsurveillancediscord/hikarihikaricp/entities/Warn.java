package fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities;

public class Warn {
    String reason;
    String m;

    public Warn(String reason, String m) {
        this.reason = reason;
        this.m = m;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
}
