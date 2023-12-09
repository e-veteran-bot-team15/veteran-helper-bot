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
public class HelperRegistrationPlaceMessage extends MessageProcessor {

    private final BotStateManager botStateManager;
    private final HelperService helperService;

    @Override
    public SendMessage process(Update update) {
        botStateManager.setState(update.getMessage().getChatId(),BotState.VETERAN_INPUT_RESIDENCE_PLACE_MATCH);
        SendMessage sendMessage = createMessage(update, Messages.HELPER_USER_PLACE_RESIDENCE_MATCH);
        sendMessage.setReplyMarkup(createKeyboard(Buttons.YES_NO));
        helperService.sendHelperRegistrationPlace(update);

        return sendMessage;
    }
}
