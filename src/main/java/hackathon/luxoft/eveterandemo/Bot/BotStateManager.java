package hackathon.luxoft.eveterandemo.Bot;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class BotStateManager {
    public HashMap<Long,BotState> stateHashMap = new HashMap<>();

    public BotState getState(Long chatId) {
        return stateHashMap.getOrDefault(chatId, BotState.NONE);
    }

    public BotState setState(Long chatId, BotState state) {
        return stateHashMap.put(chatId, state);
    }
}
