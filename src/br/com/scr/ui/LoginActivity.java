package br.com.scr.ui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.scr.utils.*;



/*
 import com.google.gson.Gson;
 import com.google.gson.JsonArray;
 import com.google.gson.JsonElement;
 import com.google.gson.JsonParser;
 */
public class LoginActivity extends Activity implements Runnable {
	private JSONObject jsonObject;
	private ProgressDialog pd;
	private String valor;
	private Button btnConfirmar;
	private Button btnCancelar;
	private EditText edtLogin;
	private EditText edtSenha;
	private Button btnConfigurar;
	private	SharedPreferences	preferencia;

	private Configuracao config;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		edtLogin = (EditText) findViewById(R.id.edtLogin);
		edtSenha = (EditText) findViewById(R.id.edtSenha);
		btnCancelar = (Button) findViewById(R.id.btnCancelar);
		btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
		btnConfigurar = (Button) findViewById(R.id.btnConf);
		
		preferencia = getSharedPreferences("config", Context.MODE_PRIVATE);	
		config = Configuracao.getInstance();
		config.setIP(preferencia.getString("servidor", "192.168.0.1"));
		config.setPorta(preferencia.getString("porta", "8080"));
		 
		btnConfirmar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pd = ProgressDialog.show(LoginActivity.this, "Agurde",
						"Cadastrando", true, true);

				threadAuxiliar = new Thread(LoginActivity.this);
				threadAuxiliar.start();

			}
		});
		btnCancelar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"Encerrando!", Toast.LENGTH_SHORT).show();
				LoginActivity.this.finish();
			}
		});
		btnConfigurar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getBaseContext(),ConfiguraServidorActivity.class));
			//	Toast.makeText(getApplicationContext(),	"Encerrando!", Toast.LENGTH_SHORT).show();
			}
		});

	}

	@Override
	public void run() {

		try {
			String json = HTTPUtils
					.acessar(config.GetUrl()+" TStatusPedidoContorller/login/"
							+ edtLogin.getText().toString()
							+ "/"
							+ edtSenha.getText().toString() + "/");

			jsonObject = new JSONObject(json);
			JSONArray resultados = jsonObject.getJSONArray("result");

			String valor = resultados.getString(0);
			Boolean resultado = Boolean.parseBoolean(valor);
			if (resultado == true) {
				startActivity(new Intent(getBaseContext(),
						PrincipalActivity.class));
				LoginActivity.this.finish();
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		h.sendEmptyMessage(0);

	}

	private Handler h = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			try {
				threadAuxiliar.sleep(2000);
			} catch (InterruptedException ex) {
			}
			pd.dismiss();
			Toast t = Toast.makeText(getBaseContext(), jsonObject.toString(),
					Toast.LENGTH_SHORT);
			t.show();

		}
	};

	private Thread threadAuxiliar;

}
