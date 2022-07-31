package src;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;

import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

public class BotModals extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if(event.getName().equals("sup")){
            TextInput name = TextInput.create("sup-name","Name", TextInputStyle.SHORT)
                    .setMinLength(1).setRequired(true).build();

            TextInput message = TextInput.create("sup-message", "Message", TextInputStyle.PARAGRAPH)
                    .setMinLength(10)
                    .setMaxLength(100)
                    .setRequired(true)
                    .setPlaceholder("Put a cool message here")
                    .build();

            Modal modal = Modal.create("sup-modal", "Say wassup")
                    .addActionRows(ActionRow.of(name), ActionRow.of(message))
                    .build();

            event.replyModal(modal).queue();

        }else if(event.getName().equals("multiply")){
            TextInput operand1 = TextInput.create("op1", "First number", TextInputStyle.SHORT)
                    .setPlaceholder("Enter a number")
                    .setMinLength(1)
                    .setRequired(true)
                    .build();

            TextInput operand2 = TextInput.create("op2", "Second number", TextInputStyle.SHORT)
                    .setPlaceholder("Enter a number")
                    .setMinLength(1)
                    .setRequired(true)
                    .build();

            Modal modal = Modal.create("mul-modal", "Multiply two numbers")
                    .addActionRows(ActionRow.of(operand1), ActionRow.of(operand2))
                    .build();

            event.replyModal(modal).queue();

        }

    }
}
