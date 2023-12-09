package hackathon.luxoft.eveterandemo.processor;

import hackathon.luxoft.eveterandemo.Bot.BotState;
import hackathon.luxoft.eveterandemo.Bot.BotStateManager;
import hackathon.luxoft.eveterandemo.processor.impl.*;
import hackathon.luxoft.eveterandemo.service.HelperService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

@Component
public class UpdateProcessor {

    private final BotStateManager stateMan = new BotStateManager();
    private final HelperService helperService = new HelperService();

    private final HashMap<String, MessageProcessor> messageTypes = new HashMap<>();
    {
        messageTypes.put("/start", new StartMessage(stateMan));
        messageTypes.put("Я ветеран", new StartMessage(stateMan));
        messageTypes.put("Я родич ветерана", new StartMessage(stateMan));
        messageTypes.put("Помічник ветерана", new HelperMessage(stateMan));
        messageTypes.put("Паспорт-книжечка", new HelperPassportMessage(stateMan, helperService));
        messageTypes.put("ID-картка", new HelperIDPassportMessage(stateMan, helperService));
        messageTypes.put("Так", new RequestRegsidencePlaceMessage(stateMan));
        messageTypes.put("Ні", new HelperRegsidencePlaceMessage(stateMan, helperService));
        messageTypes.put("Вища", new HelperEducationalLevelMessage(stateMan, helperService));
        messageTypes.put("Неповна вища", new HelperEducationalLevelMessage(stateMan, helperService));
        messageTypes.put("Середня спеціальна", new HelperEducationalLevelMessage(stateMan, helperService));
        messageTypes.put("Загальна", new HelperEducationalLevelMessage(stateMan, helperService));
        messageTypes.put("Немає освіти", new HelperEducationalLevelMessage(stateMan, helperService));
        messageTypes.put("Відправити анкет", new HelperSubmitMessage(stateMan, helperService));
    };

    private final HashMap<BotState, MessageProcessor> stateTypes = new HashMap<>();

    {
        stateTypes.put(BotState.VETERAN_HELPER, new HelperNameMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_INPUT_IPN, new HelperIPNMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_INPUT_BIRTHDAY, new HelperBirthdayMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_INPUT_SEX, new HelperSexMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_IDPASSPORT, new HelperIDPassportNumberMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_PASSPORT, new HelperPassportNumberMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_INPUT_ISSUED_DATE, new HelperPassportIssuedDateMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_INPUT_ISSUED_BY, new HelperPassportIssuedByMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_INPUT_RESIDENCE_PLACE, new RequestRegsidencePlaceMessage(stateMan));
        stateTypes.put(BotState.VETERAN_INPUT_INPUT_REGISTRATION_PLACE, new HelperRegistrationPlaceMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_INPUT_EDUCATIONAL_LEVEL, new HelperEducationalLevelMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_INPUT_SPECIALITY, new HelperSpecialityMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_INPUT_PHONE, new HelperPhoneNumberMessage(stateMan, helperService));
        stateTypes.put(BotState.VETERAN_INPUT_EMAIL, new HelperEmailMessage(stateMan, helperService));
    }

    public SendMessage process(Update update) throws TelegramApiException {
        MessageProcessor messageProcessor = messageTypes.getOrDefault(update.getMessage().getText(), null);
        if(messageProcessor != null) {
            return messageProcessor.process(update);
        }

        messageProcessor = stateTypes.getOrDefault(stateMan.getState(update.getMessage().getChatId()), new DefaultMessage());
        return messageProcessor.process(update);
    }
}
