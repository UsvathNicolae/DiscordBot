package src;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BotListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if(!event.getAuthor().isBot()) {
            String messageSent = event.getMessage().getContentRaw();
            System.out.println(messageSent);
            event.getChannel().sendMessage("Received: " + messageSent).queue();
        }
    }
}
