package helper;

import pageObject.PageCadastroUsuario;
import support.Inmetrics;
import uteis.Datapool;

public class HelperCadastroUsuario extends Inmetrics{

	private PageCadastroUsuario page;
	public Datapool dp;
	private String casoTeste;
	
	
	public HelperCadastroUsuario() {
		page = new PageCadastroUsuario();
		dp = new Datapool("cadastroUsuario.json");
	}
	
	public String getCasoTeste() {
		return casoTeste;
	}

	public void setCasoTeste(String casoTeste) {
		this.casoTeste = casoTeste;
	}
	
	public HelperCadastroUsuario preencheUsuario(String usuario) {
		aguardaElemento(page.CAMPO_USUARIO);
		preencheCampo(page.CAMPO_USUARIO, usuario);
		return this;
	}
	
	public HelperCadastroUsuario preencheSenha(String senha) {
		preencheCampo(page.CAMPO_SENHA, senha);
		return this;
	}
	
	public HelperCadastroUsuario preencheConfirmacaoSenha(String senha) {
		preencheCampo(page.CAMPO_CONFIRMACAO_SENHA, senha);
		return this;
	}
	
	public HelperCadastroUsuario acionarBotaoCadastrar() {
		click(page.BOTAO_CADASTRAR);
		return this;
	}
	
	public HelperCadastroUsuario acionarBotaoCadastraSeRodape() {
		aguardaElemento(page.BOTAO_CADASTRASE_RODAPE);
		click(page.BOTAO_CADASTRASE_RODAPE);
		return this;
	}
	
	public HelperCadastroUsuario acionarBotaoCadastraSeCabecalho() {
		aguardaElemento(page.BOTAO_CADASTRASE_CABECALHO);
		click(page.BOTAO_CADASTRASE_CABECALHO);
		return this;
	}
	
	public HelperCadastroUsuario validaCamposObrigatorios(String baseline) {
		validaObrigatoriedadeCampos(baseline, casoTeste);
		return this;
	}
	
	public HelperCadastroUsuario validaDadosInvalidos(String baseline) {
		anexaEvidencia(casoTeste);
		return this;
	}
	
	public HelperCadastroUsuario validaSenhasNaoCombinam(String baseline) {
		aguardaElemento(page.SENHAS_NAO_COMBINAM);
		pontoVerificacao(page.SENHAS_NAO_COMBINAM, "innerText", baseline);
		anexaEvidencia(casoTeste);
		return this;
	}
	
	public HelperCadastroUsuario validaUsuarioCadastrado(String baseline) {
		aguardaElemento(page.USUARIO_CADASTRADO);
		pontoVerificacao(page.USUARIO_CADASTRADO, "innerText", baseline);
		anexaEvidencia(casoTeste);
		return this;
	}
	
	public HelperCadastroUsuario validaLoginSucesso() {
		aguardaElemento(page.TITULO_LOGIN);
		anexaEvidencia(casoTeste);
		return this;
	}
}
