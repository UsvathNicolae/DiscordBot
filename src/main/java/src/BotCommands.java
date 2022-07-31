package src;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

public class BotCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if(event.getName().equals("ping")){
            event.deferReply().queue();
            event.getHook().sendMessage("pong").setEphemeral(true).queue(); //setEphemeral() true - only you can see the command and response from the bot
        }else if (event.getName().equals("food")){

            OptionMapping option = event.getOption("name");
            if(option == null){
                event.reply("For some reason a food name was not provided").queue();
                return;
            }

            String favouriteFood = option.getAsString();

            event.reply("Your favorite food is " + favouriteFood).queue();

        }else if(event.getName().equals("sum")){
            OptionMapping operand1 = event.getOption("operand1");
            OptionMapping operand2 = event.getOption("operand2");
            if(operand1 == null || operand2 == null){
                event.reply("For some reason a number was not provided").queue();
                return;
            }

            int sum = operand1.getAsInt() + operand2.getAsInt();

            event.reply("The sum is " + sum).queue();
        }else if(event.getName().equals("sub")) {
            OptionMapping operand1 = event.getOption("operand1");
            OptionMapping operand2 = event.getOption("operand2");
            if (operand1 == null || operand2 == null) {
                event.reply("For some reason a number was not provided").queue();
                return;
            }

            int dif = operand1.getAsInt() - operand2.getAsInt();

            event.reply("The difference is " + dif).queue();
        }

    }
}
