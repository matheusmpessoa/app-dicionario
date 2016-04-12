package prgdm.fatecrl.projetofinal;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dan on 17/11/2015.
 */
public class ListarDicionario  extends ListActivity {

    String regDicionario;
    List<Dicionario> listDic;
    ArrayList<String> arrlDic;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrlDic = new ArrayList<String>();
        db = new DatabaseHandler(this);
        listDic = db.buscar();
        for (Dicionario dc : listDic) {
            regDicionario =  dc.getId()+" - " + dc.getPalavra()+" Significado: "+ dc.getSignificado();
            arrlDic.add(regDicionario);
        }
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,arrlDic);
        this.setListAdapter(dataAdapter);

    }
    @Override

    protected void onListItemClick(ListView l,View v, int position, long id){
        Intent it = new Intent(this,EditarDicionario.class);
        String pal = listDic.get(position).getPalavra();
        String sig = listDic.get(position).getSignificado();
        Toast.makeText(this,  pal + "-" + sig, Toast.LENGTH_SHORT).show();
        it.putExtra("palavra",pal);
        it.putExtra("tipo", sig);
        startActivity(it);
        finish();
    }

}
