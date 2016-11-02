package fr.ducloyer.lotrtcg.android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import fr.ducloyer.lotrtcg.core.model.CardLoader;

public class LotrTcg extends Activity {

    private static final CardLoader cardLoader = CardLoader.getInstance();

    private ImageView card;
    private Button redButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        card = (ImageView) findViewById(R.id.card);
        redButton = (Button) findViewById(R.id.changeCardBtn);

        Bitmap bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream(cardLoader.loadCard(1176).getPicture()));
        card.setImageBitmap(bitmap);
        redButton.setOnClickListener(OnClickChangeLabel());
    }

    View.OnClickListener OnClickChangeLabel() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream(cardLoader.loadCard(1364).getPicture()));
                card.setImageBitmap(bitmap);
            }
        };
    }
}
