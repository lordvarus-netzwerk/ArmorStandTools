package com.gmail.St3venAU.plugins.ArmorStandTools;


import me.angeschossen.lands.api.integration.LandsIntegration;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandWorld;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

class LandsHook
{

    public static LandsIntegration api;

    private static Main plugin;

    public LandsHook(Main main) {
        plugin = main;
        LandsHook.api = new LandsIntegration(main);
    }

    public static boolean isPlotWorld(Location loc) {
        LandWorld world = api.getLandWorld(loc.getWorld());
        return world != null;
    }

    public static boolean checkPermission(Player player, Location location) {

        if(player.hasPermission("astools.lands.bypass")) {return true;}

        Land land = api.getLand(location);
        if(land != null)
        {
            if(land.getOwnerUID() == player.getUniqueId())
            {
                return true;
            }
            if(land.isTrusted(player.getUniqueId()))
            {
                return true;
            }
        }
        return false;
    }
}

