package com.rynosvapos.discordbotcraft;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class DiscordBotCraft extends JavaPlugin {

    public static Server server = Bukkit.getServer();

    @Override
    public void onEnable() {
        getLogger().info(Color.GREEN + "[DiscordBotCraft] Enabled");
        try {
            getConfig().options().copyDefaults(true);
            saveConfig();
            DiscordBot.startBot(this);
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onDisable() {
        DiscordBot.sendCloseMessage();
        getLogger().info(Color.RED + "[DiscordBotCraft] Disabled");
    }


}
