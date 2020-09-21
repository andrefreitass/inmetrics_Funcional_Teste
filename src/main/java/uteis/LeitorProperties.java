package uteis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LeitorProperties {
	File arquivo;
	public LeitorProperties(String arq){
		arquivo = new File(arq);
	}
	/**
	 * Método para obter properties do arquivo
	 */
	public Properties getProperties() {
		Properties props = new Properties();
		try{
			FileInputStream fis = new FileInputStream(arquivo.getAbsolutePath());
			props.load(fis);
			fis.close();
		}catch(IOException e){
			new AutomacaoBusinessException("Nâo foi possivel localizar o arquivo: " + arquivo);
		}
		return props;
	}
}
