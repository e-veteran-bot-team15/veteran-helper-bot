package hackathon.luxoft.eveterandemo;

import hackathon.luxoft.eveterandemo.processor.UpdateProcessor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class VeteranBot extends TelegramLongPollingBot {

    private final UpdateProcessor updateProcessor = new UpdateProcessor();

    @Override
    public String getBotUsername() {
        return "E_veteran_team15_Bot";
    }

    @Override
    public String getBotToken() {
        return "6773569835:AAFzgLxaHQPD0z-PqAkEQ4j56DXysKpiXyI";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            Message message = update.getMessage();

            if(message.hasText()) {
                try {
                    this.execute(updateProcessor.process(update));
                } catch(TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}