package gerenciadorDeServidores;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Servidor {
	
	private String ip;
	private int tempo;
	private String email="teste";
	private String nome = "teste";
	private LocalTime primeiraVerificacao;
	private int contadorParadas = 0;
	private int contadorExecoes = 0;
	private LocalTime ultimoAtivo;
	private LocalTime ultimaVerificacao;
	private int tempoAtividade=0;
	public void saida(int x) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime atual = LocalTime.now();
		System.out.println("Ocorrência: " + x + " - " + LocalDate.now() + " " + atual.format(formatador));
		System.out.println("Nome: " + this.getNome() + " - responsável: " + this.getEmail() + " - " + this.getIp());
		System.out.println("Última verificação ativa: " + getUltimoAtivo().format(formatador));
		System.out.println("Primeira verificação inatividade: " + this.getPrimeiraVerificacao().format(formatador));
		System.out.println("Tempo em atividade: " + (int)((this.getContadorExecoes() - this.getContadorParadas())*5));
		System.out.println("Tempo em inatividade: "+(int)(this.getContadorParadas()*5));
	}
	public Servidor(String ip, int tempo, String nome, String responsavel) {
		this.setIp(ip);
		this.setTempo(tempo);
		this.setPrimeiraVerificacao(LocalTime.now());
		this.setEmail(responsavel);;
		this.setNome(nome);;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalTime getPrimeiraVerificacao() {
		return primeiraVerificacao;
	}
	public void setPrimeiraVerificacao(LocalTime primeiraVerificacao) {
		this.primeiraVerificacao = primeiraVerificacao;
	}
	public int getContadorParadas() {
		return contadorParadas;
	}
	public void setContadorParadas() {
		this.contadorParadas+=1;
	}
	public LocalTime getUltimoAtivo() {
		return ultimoAtivo;
	}
	public void setUltimoAtivo(LocalTime ultimoAtivo) {
		this.ultimoAtivo = ultimoAtivo;
	}
	public LocalTime getUltimaVerificacao() {
		return ultimaVerificacao;
	}
	public void setUltimaVerificacao(LocalTime ultimaVerificacao) {
		this.ultimaVerificacao = ultimaVerificacao;
	}
	public int getTempoAtividade() {
		return tempoAtividade;
	}
	public void setTempoAtividade(int tempoAtividade) {
		this.tempoAtividade = tempoAtividade;
	}
	public int getContadorExecoes() {
		return contadorExecoes;
	}
	public void setContadorExecoes() {
		this.contadorExecoes+=1;
	}
	
}
