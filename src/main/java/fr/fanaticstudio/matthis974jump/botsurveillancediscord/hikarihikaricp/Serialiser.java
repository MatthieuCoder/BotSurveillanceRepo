package fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities.Sanction;
import fr.fanaticstudio.matthis974jump.botsurveillancediscord.hikarihikaricp.entities.Warn;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Serialiser {
    private static Gson gson;

    public static void init() {
        gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    }

    public static String serializeList(List e) {
        if (gson == null) init();
        return gson.toJson(e);
    }

    public static ArrayList<Warn> deserializeWarnList(String list) {
        if (gson == null) init();
        Type listType = new TypeToken<ArrayList<Warn>>() {
        }.getType();
        return gson.fromJson(list, listType);
    }

    public static ArrayList<Sanction> deserializeSanctionList(String list) {
        if (gson == null) init();
        Type listType = new TypeToken<ArrayList<Sanction>>() {
        }.getType();
        return gson.fromJson(list, listType);
    }

    public static ArrayList<String> deserializeRoleList(String list) {
        if (gson == null) init();
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(list, listType);
    }
}
