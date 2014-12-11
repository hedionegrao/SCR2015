package br.com.scr.utils;

public class Configuracao {
	private String IP;
	private String Porta;
	
	
	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getPorta() {
		return Porta;
	}

	public void setPorta(String porta) {
		Porta = porta;
	}

	private static Configuracao instancia;
	
	private Configuracao(){		
	}
	
	public static synchronized Configuracao getInstance(){
		if (instancia == null){
			instancia = new Configuracao();
		}
		return   instancia;
	} 
	
	public String GetUrl (){
		return  "http://" + IP+":"+Porta+"/sgv/rest/";  
	}


}
