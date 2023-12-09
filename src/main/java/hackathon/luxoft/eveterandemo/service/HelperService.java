package hackathon.luxoft.eveterandemo.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class HelperService {

    public void sendHelperName(Update update) {
        Message message = update.getMessage();
        System.out.println("SEND: " + message.getChatId() + " - " + message.getText());
    }

    public void sendHelperIPN(Update update) {
        Message message = update.getMessage();
        System.out.println("SEND1: " + message.getChatId() + " - " + message.getText());
    }

    public void sendHelperBirthday(Update update) {
        Message message = update.getMessage();
        System.out.println("SEND2: " + message.getChatId() + " - " + message.getText());
    }

    public void sendHelperSex(Update update) {
        Message message = update.getMessage();
        System.out.println("SEND2: " + message.getChatId() + " - " + message.getText());
    }

    public void sendHelperPassport(Update update) {
        Message message = update.getMessage();
        System.out.println("SEND2: " + message.getChatId() + " - " + message.getText());
    }

    public void sendHelperIDPassportNumber(Update update) {
    }

    public void sendHelperPassportNumber(Update update) {
    }

    public void sendHelperIssuedDate(Update update) {
    }

    public void sendHelperIssuedBy(Update update) {
    }

    public void sendHelperRegistrationPlace(Update update) {
    }

    public void sendHelperResidencePlace(Update update) {
    }

    public void sendEducationalLevel(Update update) {
    }

    public void sendSpeciality(Update update) {
    }

    public void sendPhone(Update update) {
    }

    public void sendEmail(Update update) {
    }

    public void sendSubmit(Update update) {
    }
}
