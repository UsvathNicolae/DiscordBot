package src;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.security.auth.login.LoginException;
import java.io.FileReader;

public class DiscordBot  {
    public static void main(String[] args) throws LoginException, InterruptedException {

        /*String token = System.getenv("DiscordToken");
        System.out.println(token);*/
        String token = "your token";

        JDA bot = JDABuilder.createDefault(token)
                .setActivity(Activity.playing("League of Legends"))
                .addEventListeners(new BotListeners())
                .build();
        bot.awaitReady();
    }


}