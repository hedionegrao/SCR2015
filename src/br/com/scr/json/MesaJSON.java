package br.com.scr.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.scr.vo.MesaVO;

public class MesaJSON {

	public List<MesaVO> ListaMesa(String json) {
		List<MesaVO> lista = new ArrayList<MesaVO>();
		try {

			JSONObject jsonObject = new JSONObject(json);
			JSONArray resultados = jsonObject.getJSONArray("result");

			JSONObject jsonObjectRaiz = resultados.getJSONObject(0);
			JSONObject fildes = jsonObjectRaiz.getJSONObject("fields");
			JSONArray itens = fildes.getJSONArray("FItems");

			for (int i = 0; i < itens.length(); i++) {
				if (!itens.isNull(i)) {
					JSONObject type = itens.getJSONObject(i);
					if (type != null) {
						JSONObject entidade = type.getJSONObject("fields");

						if (entidade != null) {

							MesaVO mesa = new MesaVO(
									entidade.getInt("FCodigo"),
									entidade.getString("FDescricao"));
							lista.add(mesa);
						}
					}

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

}
