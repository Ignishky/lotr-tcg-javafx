package fr.ducloyer.lotrtcg.core.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardLoader {

    private static final Gson GSON = new Gson();
    private final Map<Integer, Card> cards = new HashMap<>();
    private static CardLoader instance;

    private CardLoader() {
        InputStreamReader inputStreamReader = null;
        BufferedReader json = null;

        try {
            inputStreamReader = new InputStreamReader(getClass().getResourceAsStream("/card/fellowship.json"));
            json = new BufferedReader(inputStreamReader);

            List<Card> cards = GSON.fromJson(json, new TypeToken<List<Card>>() {}.getType());


            for (Card card : cards) {
                this.cards.put(card.getCollection(), card);
            }

        } finally {
            silentlyClose(inputStreamReader);
            silentlyClose(json);
        }
    }

    private void silentlyClose(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    public static CardLoader getInstance() {
        if (instance == null) {
            instance = new CardLoader();
        }
        return instance;
    }

    public Card loadCard(int number) {
        return cards.get(number);
    }
}
