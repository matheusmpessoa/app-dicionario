package prgdm.fatecrl.projetofinal;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Dan on 17/11/2015.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //lembrar de trocar para o meu menu que no caso é main_menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_novapalavra:
                Intent it = new Intent(this,CadastrarPalavra.class);
                startActivity(it);
                break;
            case R.id.action_listardicionario:
                Intent it1 = new Intent(this,ListarDicionario.class);
                startActivity(it1);
                break;
            case R.id.action_editardicionario:
                Intent it2 = new Intent(this,EditarDicionario.class);
                startActivity(it2);
                break;

        }
        return true;
    }

}