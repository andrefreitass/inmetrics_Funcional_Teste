package helper;

import pageObject.PageLogin;
import support.Inmetrics;
import uteis.Datapool;

public class HelperLogin extends Inmetrics {

	private PageLogin page;
	public Datapool dp;
	private String casoTeste;

	public HelperLogin() {
		page = new PageLogin();
		dp = new Datapool("login.json");
	}

	public String getCasoTeste() {
		return casoTeste;
	}

	public void setCasoTeste(String casoTeste) {
		this.casoTeste = casoTeste;
	}

	public HelperLogin preencheUsuario(String usuario) {
		aguardaElemento(page.CAMPO_USUARIO);
		preencheCampo(page.CAMPO_USUARIO, usuario);
		return this;
	}

	public HelperLogin preencheSenha(String senha) {
		preencheCampo(page.CAMPO_SENHA, senha);
		return this;
	}

	public HelperLogin acionarBotaoEntre() {
		click(page.BOTAO_ENTRE);
		return this;
	}

	public HelperLogin acionarBotaoSair() {
		click(page.BOTAO_SAIR);
		return this;
	}

	public HelperLogin validaCamposObrigatorios(String baseline) {
		validaObrigatoriedadeCampos(baseline, casoTeste);
		return this;
	}

	public HelperLogin validaMensagem(String baseline) {
		aguardaElemento(page.MENSAGEM_ERRO);
		pontoVerificacao(page.MENSAGEM_ERRO, "innerText", baseline);
		anexaEvidencia(casoTeste);
		return this;
	}

	public HelperLogin validaLoginSucesso() {
		aguardaElemento(page.BOTAO_SAIR);
		anexaEvidencia(casoTeste);
		return this;
	}

	public HelperLogin validaLogout() {
		aguardaElemento(page.TITULO_LOGIN);
		anexaEvidencia(casoTeste);
		return this;
	}

	/**
	 * Metodo padrão utilizado para realizar login
	 */
	public void realizarLogin(String usuario, String senha) {
		acessaAplicacao();
		preencheUsuario(usuario);
		preencheSenha(senha);
		acionarBotaoEntre();
		aguardaElemento(page.BOTAO_SAIR);
	}

}
