package br.com.scr.ui;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import br.com.scr.json.MesaJSON;
import br.com.scr.utils.Configuracao;
import br.com.scr.utils.HTTPUtils;
import br.com.scr.vo.MesaVO;
import br.com.src.adapters.MesaAdapters;

public class ListaMesas extends ListActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 Configuracao config = Configuracao.getInstance() ;
		String json = HTTPUtils.acessar(config.GetUrl()+"TStatusPedidoContorller/Mesas/");
		
		MesaJSON mesa =  new MesaJSON();
		List<MesaVO> lista = mesa.ListaMesa(json);
		setListAdapter(new MesaAdapters(getBaseContext(),lista ));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		MesaVO vo = (MesaVO)l.getAdapter().getItem(position);
		Toast.makeText(getBaseContext(), vo.getId()+"" , Toast.LENGTH_SHORT); 
	}
}
