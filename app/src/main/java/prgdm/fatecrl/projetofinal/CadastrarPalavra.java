package prgdm.fatecrl.projetofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dan on 17/11/2015.
 */

public class CadastrarPalavra extends Activity implements OnClickListener{


    protected static final int REQUEST_OK = 1;
    private Dicionario dicionario = new Dicionario();
    private EditText edtFala, edtSignificado;
    private Button btnFala;
    private DatabaseHandler bd;
    private List<Dicionario> resultadoDic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        findViewById(R.id.button1).setOnClickListener(this);
        edtFala = (EditText) findViewById(R.id.edtFala);
        edtSignificado = (EditText) findViewById(R.id.edtPalavra);
    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            startActivityForResult(i, REQUEST_OK);
        } catch (Exception e) {
            Toast.makeText(this, "Error ao inicializar o reconhecimento de voz.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_OK  && resultCode==RESULT_OK) {
            ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            ((TextView)findViewById(R.id.edtFala)).setText(thingsYouSaid.get(0));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nova_palavra, menu);
        return true;
    }

    public void gravarClick(View v){
            bd = new DatabaseHandler(this);
            dicionario.setPalavra(edtFala.getText().toString());
            dicionario.setSignificado(edtSignificado.getText().toString());
            DatabaseHandler bd = new DatabaseHandler(this);
            bd.inserir(dicionario);
            Toast.makeText(this, "Palavra cadastrada com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }

    public void cancelarClick(View v) {

        finish();
    }
}
