package prgdm.fatecrl.projetofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Dan on 17/11/2015.
 */
public class EditarDicionario extends Activity {
    private Dicionario dicionario = new Dicionario();
    private EditText edtID, edtPalavra, edtSignificado;
    private Intent it;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        //edtID = (EditText)findViewById(R.id.edtID);
        edtPalavra = (EditText)findViewById(R.id.edtPalavra);
        edtSignificado = (EditText)findViewById(R.id.edtSignificado);

        it = getIntent();

        //edtID.setText( it.getStringExtra("putID") );
        edtPalavra.setText( it.getStringExtra("putPalavra") );
        edtSignificado.setText( it.getStringExtra("putSignificado") );

    }

    public void alterarClick(View v){
        DatabaseHandler bd = new DatabaseHandler(this);
        Dicionario dicionario = new Dicionario();
        //dicionario.setId(Integer.parseInt(edtID.getText().toString()));
        dicionario.setPalavra(edtPalavra.getText().toString());
        dicionario.setSignificado(edtSignificado.getText().toString());
        bd.atualizar(dicionario);
        Toast.makeText(this, "Dicionario atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelarClick(View v) {
        finish();
    }

    public void excluirClick(View v){
        DatabaseHandler bd2 = new DatabaseHandler(this);
        dicionario.setPalavra(edtPalavra.getText().toString());
        //dicionario.setId(Integer.parseInt(edtID.getText().toString()));
        bd2.deletar(dicionario);
        Toast.makeText(this, "Palavra deletada com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
