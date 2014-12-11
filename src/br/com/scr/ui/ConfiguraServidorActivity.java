package br.com.scr.ui;

import br.com.scr.utils.Configuracao;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfiguraServidorActivity extends Activity{
	
	private EditText edtServidor;
	private EditText edtPorta;
	private Configuracao config;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conf_servidor);
		final	SharedPreferences	preferencia = getSharedPreferences("config", Context.MODE_PRIVATE);	
		config = Configuracao.getInstance();
		
		
	

		 findViewById(R.id.btnSalvarConfig).setOnClickListener(new View.OnClickListener() {
			 
			 
			@Override
			public void onClick(View v) {
				
				Builder msg = new Builder(ConfiguraServidorActivity.this );
				 msg.setMessage("Deseja salvar a configuração?");
				 msg.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
					 
					 @Override
					 public void onClick ( DialogInterface arg0, int arg1){
					 SharedPreferences.Editor editor = preferencia.edit();	
						editor.putString("porta", edtPorta.getText().toString());
						editor.putString("servidor", edtServidor.getText().toString());
						editor.commit();
						config.setIP(edtServidor.getText().toString());
						config.setPorta(edtPorta.getText().toString());
						Toast.makeText(getApplicationContext(),	"Salvo com sucesso!", Toast.LENGTH_SHORT).show();
						finish();
					 }	
					 
				 });

				msg.setNegativeButton("Não!", null);
				msg.show();
				
			}
		});
		
		 findViewById(R.id.btnCancelarConfg).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),	"Encerrando!", Toast.LENGTH_SHORT).show();
				finish();
			}
		}); 
		
		edtPorta = (EditText) findViewById(R.id.edtPorta);
		edtServidor = (EditText) findViewById(R.id.edtServidor);	
		
		
		
		edtPorta.setText( preferencia.getString("porta", "8009")); 
		edtServidor.setText( preferencia.getString("servidor", "192.168.0.1")); 

	}
	
}
