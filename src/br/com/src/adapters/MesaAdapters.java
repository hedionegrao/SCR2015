package br.com.src.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.scr.ui.R;
		
import br.com.scr.vo.*;

public class MesaAdapters extends BaseAdapter {
	
	private  Context ctx;
	private List<MesaVO> lista;
	
	public MesaAdapters (Context ctx, List<MesaVO> lista){
		this.ctx = ctx;
		this.lista = lista;		
	}
	
	@Override
	public int getCount() {
		
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		
		return lista.get(position) ;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGrup) {
		MesaVO  vo =(MesaVO) getItem(position);
		LayoutInflater layout = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = layout.inflate(R.layout.lista_mesas, null);
		
		TextView txtCodigo = (TextView)v.findViewById(R.id.txtCodigoMesa);
		txtCodigo.setText(""+vo.getId());
		TextView txtNome = (TextView)v.findViewById(R.id.txtNomeMesa);
		txtNome.setText(vo.getDescricao());
		
		return v;
		
	}

}
