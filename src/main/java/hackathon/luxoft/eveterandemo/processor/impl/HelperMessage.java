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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
@RequiredArgsConstructor
public class HelperMessage extends MessageProcessor {

    private final BotStateManager botStateManager;

    @Override
    public SendMessage process(Update update) {
        botStateManager.setState(update.getMessage().getChatId(),BotState.VETERAN_HELPER);
        SendMessage sendMessage = createMessage(update, Messages.HELPER_USER_NAME);
        sendMessage.setReplyMarkup(createKeyboard());

        return sendMessage;
    }
}
