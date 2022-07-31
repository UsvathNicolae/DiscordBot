package src;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;


public class ModalListeners extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {

        if(event.getModalId().equals("sup-modal")){
            try {
                String name = event.getValue("sup-name").getAsString();
                String message = event.getValue("sup-message").getAsString();

                Optional<Member> memberOptional = event.getGuild().getMembersByName(name, true).stream().findFirst();

                if(memberOptional.isPresent()){
                    event.reply("Sup " + memberOptional.get().getAsMention() + ", " + message).queue();
                }

                event.reply("Sup " + name + ", " + message).queue();

            }catch(NullPointerException e){
                event.reply("For some reason a name or message was not provided").queue();
            }


        }else if(event.getModalId().equals("mul-modal")){
            try {

                String num1 = event.getValue("op1").getAsString();
                String num2 = event.getValue("op2").getAsString();

                try{
                    int num1Int = Integer.parseInt(num1);
                    int num2Int = Integer.parseInt(num2);

                    int product = num1Int * num2Int;

                    event.reply("The product is: " + product).queue();

                }catch (NumberFormatException e){

                    event.reply("One of the numbers was not a number")
                            .setEphemeral(true).queue();

                }

            }catch (NullPointerException e){
                event.reply("For some reason a number was not provided")
                        .setEphemeral(true).queue();

        }

    }


    }
}
