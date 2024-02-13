package Urna_Eleitoral;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class UrnaEleitoral {


	public static void main(String[] args) throws InterruptedException {
	Scanner sc = new Scanner (System.in);	
	
	String opcoes [] = {"Configurar a eleição", "Sair"};
	String opcoes2 [] = {"Começar a eleição", "Teste de Segurança"};
	String opcoes3 [] = {"Mudar número dos candidatos", "Definir 0 votos para os candidatos", "Começar Votação"};
	int opcao, eleitores=0, votonulo = 0, votovalido = 0, tamvetor, votosvaliporce, votosnulosporce;
	boolean repetir2 = true, repetir = true, valido = true, repetir3 = true;
	
	while (repetir) {
	opcao = JOptionPane.showOptionDialog(null, "Qual opção você quer?", 
			"MENU", 0, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
	
	if (opcao == 0) {
		
			System.out.println("Deseja cadastrar quantos candidatos?");
			tamvetor = sc.nextInt();
			int candidatos [] [] = new int [2] [tamvetor];
			String nomes [] = new String [tamvetor];
			int cont4=0;
			
			for (int i = 0; i < tamvetor; i++) {
			cont4++;
			System.out.println("Digite o nome do "+ cont4 + "º candidato");
			sc.nextLine();
			nomes [i] = sc.nextLine();
			System.out.println("Digite o número do "+ cont4 + "º candidato");
			candidatos [0] [i] = sc.nextInt();
			candidatos [1] [i] = 0;
			int resposta = JOptionPane.showConfirmDialog(null,"O nome e o número do candidato estão certos?\nNome: " + nomes[i] + "\nNúmero: " + candidatos[0][i], "MENU", JOptionPane.YES_NO_OPTION);
			
			if (resposta == JOptionPane.NO_OPTION)  {
				i--;
				cont4--;
													 }
												 }
			
			System.out.println("Digite o número de eleitores");
			eleitores = sc.nextInt();
			
			if (eleitores <=1 || tamvetor<=1) {
				System.out.println("Número de eleitores/candidatos menores que o suficiente.\nCadastre-os novamente.");
					} else {
						
						int opcao2 = JOptionPane.showOptionDialog(null, "Qual opção você quer?", 
						"MENU", 0, JOptionPane.QUESTION_MESSAGE, null, opcoes2, opcoes2[0]);
						
						repetir2 = true;
						while (repetir2) {
						if (opcao2 ==0 ) {
						for (int i = 0; i < eleitores; i++) { 
						valido = false;
						String votosteste = JOptionPane.showInputDialog("Digite seu candidato\n Caso seu voto seja nulo, digite 0");
						
						int votos = Integer.parseInt(votosteste);
							for (int j = 0; j < tamvetor; j++)  {
								if (votos == candidatos[0][j]) {
									valido = true;
									candidatos[1][j] = candidatos[1][j]+1;
																} 
																 }
							if (valido) {
								System.out.println("Voto computado :)");
								votovalido++;
							} else {
								System.out.println("Voto computado :)");
								votonulo++;
								   }
															   }
						
						votosvaliporce = (votovalido*100)/eleitores; //faz a porcentagem dos votos validos e nulos.
						votosnulosporce = (votonulo*100)/eleitores; 
						JOptionPane.showMessageDialog(null, "Porcentagem de votos válidos: " +votosvaliporce + "%\nPorcentagem de votos nulos: "+votosnulosporce + "%\nHouve: "+ eleitores+ " votos!!!", ":)", JOptionPane.INFORMATION_MESSAGE);
						
						int maior = 0; //verifica qual o candidato com mais votos dentro do vetor
						int indiceMaior = -1;
						for (int i = 0; i < tamvetor; i++) {
						    if (candidatos[1][i] > maior) {
						        maior = candidatos[1][i];
						        indiceMaior = i;
						    							  }		
														   }
						
						int cont3=0; // verifica se houve empate na votação e guarda os indices de empate dos candidatos
						ArrayList<Integer> compara = new ArrayList<Integer>();
						for (int i = 0; i < tamvetor; i++) {
							if (maior==candidatos[1][i]) {
								cont3++;
								compara.add(i);
														 }
														    } 
						
						if (cont3>1) {
							JOptionPane.showMessageDialog(null, "Houve um empate entre os candidatos!!!!", ":P", JOptionPane.INFORMATION_MESSAGE);
							System.out.print("Os candidatos empatados foram: ");
							
							int ultindex = compara.size() - 1;
								for (int i = 0; i < cont3; i++) {
										if (i == ultindex) {
											System.out.print(nomes[compara.get(i)]);
										} else {
											System.out.print(nomes[compara.get(i)] + ",");
											   }
																}
							} else {
								System.out.println("O vencedor foi o candidato...");
								 for (int c = 5; c > 0; c--) { // contagem regressiva para mostrar o candidato vencedor
								       Thread.sleep(1000);
								       if (c>1) {
								           System.out.print(c + ",");
								           		} else {
								           			System.out.print(c);
								           		   		}
								    						  }
			
								JOptionPane.showMessageDialog(null, nomes[indiceMaior]+ " número: "+ candidatos[0][indiceMaior] + "\nCom " + maior + " número de votos!!!\nParabéns :) ", 
								":)", JOptionPane.INFORMATION_MESSAGE);
									}
						System.out.println();
						System.out.println("Tabela com os nomes dos candidatos e dos votos.");
						cont4=0;
							for (int i=0; i < tamvetor; i++) {
								cont4++;
								System.out.println(cont4+"º Candidato nome: "+nomes[i]+ " com " + candidatos[1][i] + " número(s) de votos!!");
															 }

						repetir = false;
						repetir2 = false;
						sc.close();

						} else {
							JOptionPane.showMessageDialog(null, "Esse teste tem o objetivo de verificar os candidatos cadastrados no sistema!!!", "MENU", JOptionPane.INFORMATION_MESSAGE);
							cont4=0;
							for (int i = 0; i < tamvetor; i++) {
								cont4++;
								System.out.println(+ cont4 + "º Candidato nome: " + nomes[i] + " número: " +candidatos[0][i]+ " votos: " +candidatos[1][i]);
															   }
							repetir3 = true;
							while (repetir3) {
							int opcao3 = JOptionPane.showOptionDialog(null, "Você deseja?", 
									"MENU", 0, JOptionPane.QUESTION_MESSAGE, null, opcoes3, opcoes3[0]);
							if (opcao3 == 0) {
								repetir2 = false;
								repetir3 = false;
							} else if (opcao3 == 1) {
								cont4=0;
								System.out.println();
								for (int i = 0; i < tamvetor; i++) {
									candidatos[1][i] = 0;
									cont4++;
									System.out.println(+ cont4 + "º Candidato nome: " + nomes[i] + " número: " +candidatos[0][i]+ " votos: " +candidatos[1][i]);
																   }
							} else {
								repetir3 = false;
								opcao2 = 0;
								   }
							   				}
							   }
						}		
							}

				} else {
					repetir = false;
					   }
					}
	
																	   }

							}
