package fr.ducloyer.lotrtcg.core.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Throwables.propagate;
import static java.util.stream.Collectors.toMap;

public class CardLoader {

    private static final Gson GSON = new Gson();
    private final Map<Integer, Card> cards;
    private static CardLoader instance;

    private CardLoader() {
        try (BufferedReader json = new BufferedReader(new InputStreamReader(new FileInputStream(new File(getClass().getResource("/card/fellowship.json").toURI()))))) {
            List<Card> tmpCards = GSON.fromJson(json, new TypeToken<List<Card>>() {}.getType());
            cards = tmpCards.stream().collect(toMap( Card::getCollection, c -> c));
        }
        catch (IOException | URISyntaxException e) {
            throw propagate(e);
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
