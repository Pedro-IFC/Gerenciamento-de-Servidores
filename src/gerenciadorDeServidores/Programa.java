package gerenciadorDeServidores;

import java.time.Duration;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.lang.Thread;

public class Programa {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		String ip = "172.18.0.1";
		int tempo = 5;
		Servidor servidor = new Servidor(ip, tempo);
		input.close();
		servidor.setUltimoAtivo(LocalTime.now());
		servidor.setPrimeiraVerificacao(LocalTime.now());
		for(int x = 1; x>0; x++) {
			int intervalo = tempo;
			long tempoPerdido = 0;
			servidor.setContadorExecoes();
			LocalDateTime inicio = LocalDateTime.now();
			int show = 0;
			try {
				if (!InetAddress.getByName(servidor.getIp()).isReachable(servidor.getTempo())) {
					servidor.setContadorParadas();
					Duration duracao = Duration.between(inicio, LocalDateTime.now());
					tempoPerdido = (long)duracao.getSeconds() - (long)tempo;
					show= 1;
				}else {
					servidor.setUltimoAtivo(LocalTime.now());
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro no Try");
			}
			long tempoSobrando = (intervalo*1000 - tempoPerdido); 
			tempoSobrando = (long)(tempoSobrando > 0 ? (tempoSobrando) : 0);
			try {
				Thread.sleep((long)tempoSobrando);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            e.printStackTrace();
	        }
			if(show==1) {
				servidor.saida(x);
			}
		}
		
	}

}
