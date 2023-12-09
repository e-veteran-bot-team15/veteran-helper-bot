package hackathon.luxoft.eveterandemo.processor;

import hackathon.luxoft.eveterandemo.Bot.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public abstract class MessageProcessor {
    abstract public SendMessage process(Update update) throws TelegramApiException;

    public SendMessage createMessage(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        return sendMessage;
    }

    public SendMessage createMessage(Update update,String text) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setText(text);

        return sendMessage;
    }

    public ReplyKeyboardMarkup createKeyboard(List<String> buttons) {
        var markup = new ReplyKeyboardMarkup();
        var keyboardRows = new ArrayList<KeyboardRow>();

        for(String button : buttons) {
            KeyboardRow row = new KeyboardRow();
            row.add(button);
            keyboardRows.add(row);
        }
        keyboardRows.add(createDefaultKeyboard());

        markup.setResizeKeyboard(true);
        markup.setKeyboard(keyboardRows);

        return markup;
    }

    public ReplyKeyboardMarkup createKeyboard(List<String> buttons, boolean noAdd) {
        var markup = new ReplyKeyboardMarkup();
        var keyboardRows = new ArrayList<KeyboardRow>();

        for(String button : buttons) {
            KeyboardRow row = new KeyboardRow();
            row.add(button);
            keyboardRows.add(row);
        }

        if(!noAdd) {
            keyboardRows.add(createDefaultKeyboard());
        }

        markup.setResizeKeyboard(true);
        markup.setKeyboard(keyboardRows);

        return markup;
    }

    public ReplyKeyboardMarkup createKeyboard() {
        var markup = new ReplyKeyboardMarkup();
        var keyboardRows = new ArrayList<KeyboardRow>();
        keyboardRows.add(createDefaultKeyboard());
        markup.setResizeKeyboard(true);
        markup.setKeyboard(keyboardRows);

        return markup;
    }

    public KeyboardRow createDefaultKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add("Назад");
        row.add("На початок");

        return row;
    }
}
