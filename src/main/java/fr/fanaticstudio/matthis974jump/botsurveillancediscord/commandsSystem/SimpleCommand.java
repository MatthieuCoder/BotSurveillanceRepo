package fr.fanaticstudio.matthis974jump.botsurveillancediscord.commandsSystem;

import java.lang.reflect.Method;


/**
 * @author By Matthis974Jump and Matheog974
 * @version 1.0-SNAPSHOT
 * Classe comande simple
 */
public final class SimpleCommand {

    /**
     * Le nom et la description de la commande
     */
    private final String name, description;
    /**
     * L'objet instancie dans contenant la commande
     */
    private final Object object;
    /**
     * La methode associe a la commande
     */
    private final Method method;
    /**
     *
     */
    private final String[] aliasses;
    /**
     *
     */
    private final Roles permissionLevel;
    /**
     *
     */
    private final int cost;

    /**
     * Constructeur de l'objet command
     *  @param name        Nom de la commande
     * @param description Description de la commande
     * @param object      L'objet contenant la commande
     * @param method      La methode associe a la commande
     * @param permissionLevel
     */
    SimpleCommand(String name, String description, Object object, Method method, String[] aliasses, Roles permissionLevel, int cost) {
        super();
        this.name = name;
        this.description = description;
        this.object = object;
        this.method = method;
        this.aliasses = aliasses;
        this.permissionLevel = permissionLevel;
        this.cost = cost;
    }

    /**
     *
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return Le nom de la commande
     */
    public String getName() {
        return name;
    }

    /**
     * @return La description de la commande
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return L'objet contenant la commande
     */
    Object getObject() {
        return object;
    }

    /**
     * @return La methode associe a la commande
     */
    Method getMethod() {
        return method;
    }

    /**
     *
     */
    Roles getPermissionLevel() {
        return permissionLevel;
    }

    /**
     * @return
     */
    public String[] getAliasses() {
        return aliasses;
    }

}
