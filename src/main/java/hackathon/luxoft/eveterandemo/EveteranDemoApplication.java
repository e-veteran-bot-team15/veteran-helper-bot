package hackathon.luxoft.eveterandemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class EveteranDemoApplication {

	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(EveteranDemoApplication.class, args);

		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
		try {
			telegramBotsApi.registerBot(new VeteranBot());
		} catch (TelegramApiRequestException ignored) {
		} catch(TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}
}
