package app.projeto;

import android.app.Activity;
import android.os.Bundle;
import app.servico.ServicoWebClient;

public class PersonalTraining extends Activity {

    private ServicoWebClient servicoWeb;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //  servicoWeb = new ServicoWebClient();
    }
}
