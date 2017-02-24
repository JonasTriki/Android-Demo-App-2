package no.triki.demoapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int bildeIndeks;
    ArrayList<SlideBilde> bilder;
    ImageView imgPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lager referanse til ting
        Button forrigeBilde = (Button)findViewById(R.id.forrigeBilde);
        forrigeBilde.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                bildeIndeks--;
                if (bildeIndeks < 0) bildeIndeks = bilder.size() - 1;
                visBilde(bildeIndeks);
            }
        });
        Button nesteBilde = (Button)findViewById(R.id.nesteBilde);
        nesteBilde.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                bildeIndeks++;
                if (bildeIndeks > bilder.size() - 1) bildeIndeks = 0;
                visBilde(bildeIndeks);
            }
        });
        imgPreview = (ImageView)findViewById(R.id.imgPreview);

        // Laster inn alle bildeelementene.
        bildeIndeks = 0;
        bilder = new ArrayList<>();
        Field[] bildeFields = R.drawable.class.getFields();
        for (int i = 0; i < bildeFields.length; i++) {
            Field bilde = bildeFields[i];
            if (bilde.getName().startsWith("slide_")) {
                try {
                    String tittel = "";
                    switch (bilde.getName().replace("slide_", "")) {
                        case "andre_i_sonen":
                            tittel = "Andre i sonen hehe";
                            break;
                        case "draw_me_like_one_of_your_french_girls":
                            tittel = "jaja, vettkje";
                            break;
                        case "facebook_post":
                            tittel = "Facerape? eller kanskje ikke ;)";
                            break;
                        case "gotta_catch_em_all":
                            tittel = "Pokemon fanboi #1";
                            break;
                        case "guitaren":
                            tittel = "Liker musikk";
                            break;
                    }
                    bilder.add(new SlideBilde(bilde.getInt(null), tittel));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        visBilde(0);
    }

    private void visBilde(int indeks) {
        try {
            SlideBilde bilde = bilder.get(indeks);
            imgPreview.setImageResource(bilde.getRessursId());
            setTitle(bilde.getTittel());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
