package src;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BotListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        System.out.println("message recieved");
        if(!event.getAuthor().isBot()) {
            String messageSent = event.getMessage().getContentRaw();

            event.getChannel().sendMessage("Received: " + messageSent).queue();
        }
    }
}
