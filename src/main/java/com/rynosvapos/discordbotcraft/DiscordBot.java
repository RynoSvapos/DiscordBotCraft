package com.rynosvapos.discordbotcraft;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DiscordBot {
    private static JDA jda;
    private static JavaPlugin plugin;
    private static TextChannel txtChannel;
    private static boolean botOnline = false;

    public static void startBot(JavaPlugin plugin) throws LoginException, InterruptedException{
        String token = plugin.getConfig().getString("token");
        String guildID = plugin.getConfig().getString("guildID");
        String channelID = plugin.getConfig().getString("channelID");

        DiscordBot.plugin = plugin;

        if (token == null) return;
        if (guildID == null) return;
        if (channelID == null) return;


        String gameStatus = plugin.getConfig().getString("gameStatus");
        String serverStarting = plugin.getConfig().getString("serverStarting");

        jda = JDABuilder.createDefault(token)
                .addEventListeners(new Events())
                .build();

        jda.awaitReady();

        jda.getPresence().setActivity(Activity.playing(gameStatus));
        txtChannel = jda.getGuildById(guildID).getTextChannelById(channelID);

        txtChannel.sendMessage(serverStarting).queue();
        sendOpenMessage();
    }

    public static void clearMessage() {
        try {
            MessageHistory history = new MessageHistory(txtChannel);
            List<Message> mags;

            mags = history.retrievePast(10).completeAfter(1, TimeUnit.SECONDS);


            txtChannel.deleteMessages(mags).queue();
        } catch(Exception e) {
            return;
        }


    }

    public static void sendOpenMessage() {
        clearMessage();
        txtChannel.sendMessage("@everyone").queue();
        EmbedBuilder join = new EmbedBuilder();

        String embedOpenTitle = plugin.getConfig().getString("embedOpenTitle");
        String embedOpenAuthor = plugin.getConfig().getString("embedOpenAuthor");
        String embedOpenUrl = plugin.getConfig().getString("embedOpenUrl");
        String embedOpenIconUrl = plugin.getConfig().getString("embedOpenIconUrl");
        String embedOpenField = plugin.getConfig().getString("embedOpenField");
        String botAd = plugin.getConfig().getString("botAd");

        join.setColor(0x2effa4);
        join.setTitle(embedOpenTitle);
        join.setAuthor(embedOpenAuthor,
                embedOpenUrl,
                embedOpenIconUrl);
        join.addField("", embedOpenField, false);
        join.setFooter(botAd);


        txtChannel.sendMessageEmbeds(join.build()).queue();
    }



    public static void sendCloseMessage() {
        clearMessage();
        EmbedBuilder left = new EmbedBuilder();

        String embedClosedTitle = plugin.getConfig().getString("embedClosedTitle");
        String embedClosedAuthor = plugin.getConfig().getString("embedClosedAuthor");
        String embedClosedUrl = plugin.getConfig().getString("embedClosedUrl");
        String embedClosedIconUrl = plugin.getConfig().getString("embedClosedIconUrl");
        String embedClosedField = plugin.getConfig().getString("embedClosedField");
        String botAd = plugin.getConfig().getString("botAd");

        left.setColor(0xff0059);
        left.setTitle(embedClosedTitle);
        left.setAuthor(embedClosedAuthor,
                embedClosedUrl,
                embedClosedIconUrl);
        left.addField("", embedClosedField, false);
        left.setFooter(botAd);

        txtChannel.sendMessageEmbeds(left.build()).queue();
    }
}
