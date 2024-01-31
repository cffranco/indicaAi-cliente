package br.net.mecsoft.response;


public class ProfissionalResponse {
	
	private Long id;
    private String nome;
    private String email;
    private String celular;
    private int numeroIndicacao;
    private ProfissaoResponse profissao;
    private String endereco;
    private Double saldo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public int getNumeroIndicacao() {
		return numeroIndicacao;
	}
	public void setNumeroIndicacao(int numeroIndicacao) {
		this.numeroIndicacao = numeroIndicacao;
	}
	public ProfissaoResponse getProfissao() {
		return profissao;
	}
	public void setProfissao(ProfissaoResponse profissao) {
		this.profissao = profissao;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
    
    

}
