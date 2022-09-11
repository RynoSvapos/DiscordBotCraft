package com.rynosvapos.discordbotcraft;


import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


public class Events implements EventListener {

    private final JavaPlugin plugin = JavaPlugin.getPlugin(DiscordBotCraft.class);

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent) {
            plugin.getLogger().info("[DiscordBotCraft] JDA API LOADED");
        }
    }
}
