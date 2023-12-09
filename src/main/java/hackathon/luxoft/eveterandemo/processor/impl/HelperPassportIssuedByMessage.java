package hackathon.luxoft.eveterandemo.processor.impl;

import hackathon.luxoft.eveterandemo.Bot.BotState;
import hackathon.luxoft.eveterandemo.Bot.BotStateManager;
import hackathon.luxoft.eveterandemo.processor.Buttons;
import hackathon.luxoft.eveterandemo.processor.MessageProcessor;
import hackathon.luxoft.eveterandemo.processor.Messages;
import hackathon.luxoft.eveterandemo.service.HelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class HelperPassportIssuedByMessage extends MessageProcessor {

    private final BotStateManager botStateManager;
    private final HelperService helperService;

    @Override
    public SendMessage process(Update update) {
        botStateManager.setState(update.getMessage().getChatId(),BotState.VETERAN_INPUT_INPUT_REGISTRATION_PLACE);
        SendMessage sendMessage = createMessage(update, Messages.HELPER_USER_PLACE_REGISTRATION);
        sendMessage.setReplyMarkup(createKeyboard());
        helperService.sendHelperIssuedBy(update);

        return sendMessage;
    }
}
