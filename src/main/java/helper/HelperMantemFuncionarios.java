package helper;

import pageObject.PageMantemFuncionarios;
import support.Inmetrics;
import uteis.Datapool;
import uteis.Log;

public class HelperMantemFuncionarios extends Inmetrics {

	private PageMantemFuncionarios page;
	public Datapool dp;
	private String casoTeste;

	public HelperMantemFuncionarios() {
		page = new PageMantemFuncionarios();
		dp = new Datapool("mantemFuncionarios.json");
	}

	public String getCasoTeste() {
		return casoTeste;
	}

	public void setCasoTeste(String casoTeste) {
		this.casoTeste = casoTeste;
	}

	public HelperMantemFuncionarios acionarBotaoNovoFuncionario() {
		aguardaElemento(page.BOTAO_NOVO_FUNCIONARIO);
		click(page.BOTAO_NOVO_FUNCIONARIO);
		return this;
	}

	public HelperMantemFuncionarios acionarBotaoFuncionarios() {
		aguardaElemento(page.BOTAO_FUNCIONARIOS);
		click(page.BOTAO_FUNCIONARIOS);
		return this;
	}

	public HelperMantemFuncionarios preencheCampoNome(String nome) {
		limparCampo(page.CAMPO_NOME);
		preencheCampo(page.CAMPO_NOME, nome);
		return this;
	}

	public HelperMantemFuncionarios preencheCampoCPF(String cpf) {
		limparCampo(page.CAMPO_CPF);
		preencheCampo(page.CAMPO_CPF, cpf);
		return this;
	}

	public HelperMantemFuncionarios selecionarSexo(String sexo) {
		click(page.COMBOBOX_SEXO);
		if (sexo.equalsIgnoreCase("Indiferente")) {
			click(page.OPCAO_INDIFERENTE);
		} else if (sexo.equalsIgnoreCase("Feminino")) {
			click(page.OPCAO_FEMININO);
		} else if (sexo.equalsIgnoreCase("Masculino")) {
			click(page.OPCAO_MASCULINO);
		} else {
			Log.info("Informe uma opção válida!");
		}
		return this;
	}

	public HelperMantemFuncionarios preencheCampoAdmissao(String admissao) {
		limparCampo(page.CAMPO_ADMISSAO);
		preencheCampo(page.CAMPO_ADMISSAO, admissao);
		return this;
	}

	public HelperMantemFuncionarios preencheCampoCargo(String cargo) {
		limparCampo(page.CAMPO_CARGO);
		preencheCampo(page.CAMPO_CARGO, cargo);
		return this;
	}

	public HelperMantemFuncionarios preencheCampoSalario(String salario) {
		limparCampo(page.CAMPO_SALARIO);
		preencheCampo(page.CAMPO_SALARIO, salario);
		return this;
	}

	public HelperMantemFuncionarios selecionarTipoContratacao(String contratacao) {

		if (contratacao.equalsIgnoreCase("clt")) {
			click(page.OPCAO_CLT);
		} else if (contratacao.equalsIgnoreCase("pj")) {
			click(page.OPCAO_PJ);
		} else {
			Log.info("Informe uma opção válida!");
		}
		return this;
	}

	public HelperMantemFuncionarios acionarBotaoEnviar() {
		click(page.BOTAO_ENVIAR);
		return this;
	}

	public HelperMantemFuncionarios acionarBotaoCancelar() {
		click(page.BOTAO_CANCELAR);
		anexaEvidencia(casoTeste);
		return this;
	}

	public HelperMantemFuncionarios pesquisar(String dado) {
		aguardaElemento(page.CAMPO_PESQUISAR);
		preencheCampo(page.CAMPO_PESQUISAR, dado);
		return this;
	}

	public HelperMantemFuncionarios acionarBotaoEditar() {
		click(page.BOTAO_EDITAR);
		return this;
	}

	public HelperMantemFuncionarios acionarBotaoExcluir() {
		click(page.BOTAO_EXCLUIR);
		return this;
	}

	public HelperMantemFuncionarios validaMensagem(String baseline) {
		aguardaElemento(page.MENSAGEM);
		pontoVerificacao(page.MENSAGEM, "innerText", baseline);
		anexaEvidencia(casoTeste);
		return this;
	}

	public HelperMantemFuncionarios validaMensagemPoupUp(String baseline) {
		pontoVerificacao(baseline);
		anexaEvidencia(casoTeste);
		return this;
	}
	
	public HelperMantemFuncionarios validaCamposObrigatorios() {
		atributoRequirePresente(page.CAMPO_NOME);
		atributoRequirePresente(page.CAMPO_CPF);
		atributoRequirePresente(page.COMBOBOX_SEXO);
		atributoRequirePresente(page.CAMPO_ADMISSAO);
		atributoRequirePresente(page.CAMPO_CARGO);
		atributoRequirePresente(page.CAMPO_SALARIO);
		atributoRequirePresente(page.OPCAO_CLT);
		atributoRequirePresente(page.OPCAO_PJ);
		anexaEvidencia(casoTeste);
		return this;
	}


	

}
