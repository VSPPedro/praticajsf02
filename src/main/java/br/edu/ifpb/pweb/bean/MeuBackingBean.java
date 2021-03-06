package br.edu.ifpb.pweb.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import br.edu.ifpb.pweb.model.EstadoCivilEnum;

@ManagedBean(name = "bbean")
@RequestScoped
public class MeuBackingBean {
	private String mensagem = "Produzido no bbean";
	private String nome;
	private String faixa;
	private String senha;
	private String[] cidades = { "João Pessoa", "Campina Grande", "Taperoá", "Soledade", "Juarezinho" };
	private String nomeCidade;
	private int idade;
	private int cidade;
	private EstadoCivilEnum estCivil;

	private String selectedEstCivil; // +getter +setter
	private Map<String, String> estadoCivilMap; // +getter (no setter necessary)

	@PostConstruct
	public void init() {
		estadoCivilMap = new LinkedHashMap<String, String>();
		estadoCivilMap.put("SL", "Solteiro");
		estadoCivilMap.put("CS", "Casado");
		estadoCivilMap.put("DV", "Divorciado");
		estadoCivilMap.put("UE", "União Estável");
		estadoCivilMap.put("VO", "Viúvo");
	}

	public String getSelectedEstCivil() {
		return selectedEstCivil;
	}

	public void setSelectedEstCivil(String selectedEstCivil) {
		this.selectedEstCivil = selectedEstCivil;
	}

	public Map<String, String> getEstadoCivilMap() {
		return estadoCivilMap;
	}

	public void setEstadoCivilMap(Map<String, String> availableEstCivil) {
		this.estadoCivilMap = availableEstCivil;
	}

	public EstadoCivilEnum getEstCivil() {
		return estCivil;
	}

	public void setEstCivil(EstadoCivilEnum estCivil) {
		this.estCivil = estCivil;
	}

	public String getSenha() {
		return senha;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public String[] getCidades() {
		return cidades;
	}

	public void setCidades(String[] cidades) {
		this.cidades = cidades;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getFaixa() {
		return faixa;
	}

	public void setFaixa(String faixa) {
		this.faixa = faixa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public String maiusculas() {
		this.nome = this.nome.toUpperCase();
		return null;
	}

	public String minusculas() {
		this.nome = this.nome.toLowerCase();
		return null;
	}

	public EstadoCivilEnum[] getEstadosCivis() {
		return EstadoCivilEnum.values();
	}

	public String calculeFaixa() {
		if (this.idade >= 18 && this.idade <= 25) {
			this.faixa = "Jovem";
		} else if (this.idade >= 26 && this.idade <= 59) {
			this.faixa = "Adulto";
		} else if (this.idade > 59 && this.idade < 90) {
			this.faixa = "Idoso";
		}
		return null;
	}

	public String valideSenha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String msg = "Senha não confere!";
		FacesMessage.Severity nivel = FacesMessage.SEVERITY_ERROR;

		if (this.senha.equalsIgnoreCase("secret")) {
			msg = "Senha confere!";
			nivel = FacesMessage.SEVERITY_INFO;
		}

		FacesMessage facesMsg = new FacesMessage(nivel, msg, null);
		fc.addMessage("form4:senha", facesMsg);
		return null;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String selecioneCidade() {
		System.out.println("Cidade: " + cidade);
		System.out.println("Array cidade tamanho: " + cidades.length);

		if (this.cidade < cidades.length) {
			this.nomeCidade = cidades[cidade];
		}

		return null;
	}

	public void selecioneEstCivil() {
		// vazio!
	}

	public void selecioneEstCivilMapa() {
		// vazio!
		System.out.println("selectedEstCivil-chave: " + selectedEstCivil);
		if (selectedEstCivil != null && selectedEstCivil != "") {
			selectedEstCivil = estadoCivilMap.get(selectedEstCivil);
		}

		System.out.println("selectedEstCivil-valor: " + selectedEstCivil);
	}

	public void executeMudancaEstCivil(AjaxBehaviorEvent abe) {
		System.out.println("Processou ajax na fase: " + abe.getPhaseId() + " gerado por "
				+ ((UIComponent) abe.getSource()).getClientId());
	}
}
