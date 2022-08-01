package src;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

public class ButtonListeners extends ListenerAdapter {

    private final String[] badWords = {"poop", "weener", "frick"};

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        for(String badWord : badWords) {
            if (event.getMessage().getContentRaw().contains(badWord)) {

                event.getChannel().sendMessage("You said a bad word! I am reporting you.").queue();

                TextChannel staffChannel = event.getJDA().getTextChannelById("1003611892725600316");

                if (staffChannel != null) {

                    Button removeButton = Button.danger("remove-message", "Remove Message");

                    Button ignoreButton = Button.secondary("ignore-alert", "Ignore alert");

                    Message message = new MessageBuilder()
                            .append(event.getMember().getEffectiveName())
                            .append(" said a bad word. Click the button below to remove it.")
                            .append("Message ID: " + event.getMessageId())
                            .setActionRows(ActionRow.of(removeButton, ignoreButton))
                            .build();

                    staffChannel.sendMessage(message).queue();

                }

            }
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        if(event.getButton().getId().equals("remove-message")){
            String[] content = event.getMessage().getContentRaw().split(" ");
            String messageID = content[content.length - 1];

            event.getGuild().getTextChannelById("1002114578785767466").deleteMessageById(messageID).queue();

            event.reply("Message deleted").queue();

        }else if (event.getButton().getId().equals("ignore-alert")){

            event.getMessage().delete().queue();

            event.reply("Alert deleted").setEphemeral(true).queue();

        }

    }
}
