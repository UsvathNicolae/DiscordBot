package src;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import src.commands.BotCommands;

import javax.security.auth.login.LoginException;
import java.io.FileReader;

public class DiscordBot  {
    public static void main(String[] args) throws LoginException, InterruptedException {


        String token = "MTAwMjExNzY5MzI4Mjk4ODAzMg.GL0CGu.augNqsF12Rknsmv1dvmVdrIfsYBIs2oRHAvztg";

        JDA bot = JDABuilder.createDefault(token)
                .setActivity(Activity.playing("League of Legends"))
                .addEventListeners(new BotListeners())
                .addEventListeners(new BotCommands())
                .build().awaitReady();
        Guild guild = bot.getGuildById("1002114578181791817");//for guild commands
        if(guild != null){

            guild.upsertCommand("ping","Ping").queue();
            guild.upsertCommand("food", "Name your favorite food")
                    .addOption(OptionType.STRING, "name", "name of your favorite food", true)
                    .queue();
            guild.upsertCommand("sum","Adds two numbers together")
                    .addOption(OptionType.INTEGER, "operand1", "first number", true)
                    .addOption(OptionType.INTEGER, "operand2", "second number", true)
                    .queue();
            guild.upsertCommand("sub","Substracts second number from the first")
                    .addOptions(new OptionData(OptionType.INTEGER,"operand1", "first number",true).setRequiredRange(1,Integer.MAX_VALUE),
                                new OptionData(OptionType.INTEGER,"operand2", "second number",true).setRequiredRange(1,Integer.MAX_VALUE))
                    .queue();
        }

        //bot.upsertCommand("ping","Ping").queue();//for global commands
        //another way to add commands to bot
        /*CommandListUpdateAction commands =  bot.updateCommands();
        commands.addCommands(Commands.slash("ping","Ping"),
                Commands.slash("food", "Name your favorite food")
                        .addOption(OptionType.STRING, "name", "name of your favorite food", true));
        commands.queue();*/
    }


}