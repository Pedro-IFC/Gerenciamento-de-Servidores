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

		System.out.print("Insira o IP do servidor: ");
		String ip = input.nextLine();
		while(ip.length()<=7) {
			System.out.print("Insira o IP do servidor: ");
			ip = input.nextLine();	
		}
		System.out.print("Insira o nome do servidor: ");
		String nome = input.nextLine();
		while(nome.length()<=0) {
			System.out.print("Insira o nome do servidor: ");
			nome = input.nextLine();
		}
		System.out.print("Insira o e-mail do responsável: ");
		String responsavel = input.nextLine();
		while(responsavel.length()<=0) {
			System.out.print("Insira o e-mail do responsável: ");
			responsavel = input.nextLine();
		}
		System.out.print("Insira o tempo de resposta: ");
		int tempo = input.nextInt();
		while(tempo<=0) {
			System.out.print("Insira o tempo de resposta: ");
			tempo = input.nextInt();
		}
		Servidor servidor = new Servidor(ip, tempo, nome, responsavel);
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
