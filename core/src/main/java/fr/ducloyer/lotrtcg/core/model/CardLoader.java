package fr.ducloyer.lotrtcg.core.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CardLoader {

    private static final Gson GSON = new Gson();
    private static final Map<Integer, Card> cards = new HashMap<>();

    static {
        try (InputStreamReader stream = new InputStreamReader(CardLoader.class.getResourceAsStream("/card/fellowship.json"));
             BufferedReader json = new BufferedReader(stream)) {

            List<Card> cardList = GSON.fromJson(json, new TypeToken<List<Card>>() {
            }.getType());
            cardList.forEach(c -> cards.put(c.getCollection(), c));
        } catch (IOException ioe) {
            log.error("Unable to close reader", ioe);
        }
    }

    public static Card loadCard(Name name) {
        return cards.get(name.getCollection());
    }
}
