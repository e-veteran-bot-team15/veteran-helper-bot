package hackathon.luxoft.eveterandemo.processor.impl;

import hackathon.luxoft.eveterandemo.Bot.BotState;
import hackathon.luxoft.eveterandemo.Bot.BotStateManager;
import hackathon.luxoft.eveterandemo.processor.Buttons;
import hackathon.luxoft.eveterandemo.processor.MessageProcessor;
import hackathon.luxoft.eveterandemo.processor.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartMessage extends MessageProcessor {

    private final BotStateManager botStateManager;

    @Override
    public SendMessage process(Update update) {
        botStateManager.setState(update.getMessage().getChatId(),BotState.GREETING);
        SendMessage sendMessage = createMessage(update, Messages.HELPER_USER_DETECTION);
        sendMessage.setReplyMarkup(createKeyboard(Buttons.USER_TYPE, true));

        return sendMessage;
    }
}
