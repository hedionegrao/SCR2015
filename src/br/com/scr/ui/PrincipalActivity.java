package br.com.scr.ui;

import java.util.List;

import br.com.scr.vo.MesaVO;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends Activity {
	
	private Button btnNovoPedido;
	private Button btnMesa;
	private List<MesaVO> mesas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);

		 btnNovoPedido = (Button) findViewById(R.id.btnNovoPedido);
		 btnMesa = (Button) findViewById(R.id.btnMesa);
		 
		 btnMesa.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					startActivity(new Intent(getBaseContext(),ListaMesas.class));

					

				}
			});
		 
		 
}
	@Override
	protected void onDestroy() {		
		super.onDestroy();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
}
