package Urna_Eleitoral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class UrnaEleitoral {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);

		String opcoes[] = { "Configurar a eleição", "Sair" };
		String opcoes2[] = { "Começar a eleição", "Teste de Segurança" };
		String opcoes3[] = { "Mudar número ou nome de um candidato", "Definir 0 votos para os candidatos",
				"Começar Votação" };
		int opcao, eleitores = 0, votonulo = 0, votovalido = 0, votosvaliporce, votosnulosporce, cont4 = 1;
		boolean repetir2 = true, repetir = true, valido = true, repetir3 = true;
		Map<Integer, Integer> mapaCandidatos = new LinkedHashMap<>();
		Map<Integer, String> mapaNomeCandidatos = new LinkedHashMap<>();
		while (repetir) {
			opcao = JOptionPane.showOptionDialog(null, "Qual opção você quer?",
					"MENU", 0, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

			if (opcao == 0) {

				boolean cadastrarLoop = true;
				do {
					System.out.println("Digite o nome do " + cont4 + "º candidato");
					String temp1 = sc.next();
					System.out.println("Digite o número do " + cont4 + "º candidato");
					Integer temp2 = sc.nextInt();
					int resposta = JOptionPane.showConfirmDialog(null,
							"O nome e o número do candidato estão certos?\nNome: " + temp1 + "\nNúmero: " + temp2,
							"MENU", JOptionPane.YES_NO_OPTION);
					if (resposta == JOptionPane.NO_OPTION) {
						cont4--;
					} else {
						if (mapaCandidatos.containsKey(temp2) || mapaNomeCandidatos.containsValue(temp1)) {
							JOptionPane.showMessageDialog(null, "Esse candidato já existe :/", "MENU",
									JOptionPane.ERROR_MESSAGE);
							cont4--;
						} else {
							mapaCandidatos.put(temp2, 0);
							mapaNomeCandidatos.put(temp2, temp1);
							int respostaTemp = JOptionPane.showConfirmDialog(null, "Deseja adicionar mais candidatos?",
									"MENU", JOptionPane.YES_NO_OPTION);
							if (respostaTemp == JOptionPane.NO_OPTION) {
								cadastrarLoop = false;
							}
						}
					}
					cont4++;
				} while (cadastrarLoop);

				System.out.println("Digite o número de eleitores");
				eleitores = sc.nextInt();

				if (eleitores <= 1 || mapaCandidatos.size() <= 1) {
					System.out.println(
							"Número de eleitores/candidatos menores que o suficiente.\nCadastre-os novamente.");
				} else {
					int opcao2 = JOptionPane.showOptionDialog(null, "Qual opção você quer?",
							"MENU", 0, JOptionPane.QUESTION_MESSAGE, null, opcoes2, opcoes2[0]);

					repetir2 = true;
					while (repetir2) {
						if (opcao2 == 0) {
							for (int i = 0; i < eleitores; i++) {
								valido = false;
								String votosteste = JOptionPane
										.showInputDialog("Digite seu candidato\n Caso seu voto seja nulo, digite 0");

								Integer votoAtual = Integer.parseInt(votosteste);
								if (mapaCandidatos.containsKey(votoAtual)) {
									mapaCandidatos.put(votoAtual, mapaCandidatos.get(votoAtual) + 1);
									valido = true;
								}

								if (valido) {
									System.out.println("Voto computado :)");
									votovalido++;
								} else {
									System.out.println("Voto computado :)");
									votonulo++;
								}
							}

							votosvaliporce = (votovalido * 100) / eleitores; // faz a porcentagem dos votos validos e
																				// nulos.
							votosnulosporce = (votonulo * 100) / eleitores;
							JOptionPane.showMessageDialog(null,
									"Porcentagem de votos válidos: " + votosvaliporce
											+ "%\nPorcentagem de votos nulos: " + votosnulosporce + "%\nHouve: "
											+ eleitores + " votos!!!",
									":)", JOptionPane.INFORMATION_MESSAGE);

							int maiorQuantiVotos = 0; // verifica qual o candidato com mais votos dentro do vetor
							int keyCandidatoVencedor = 0;
							for (Integer key : mapaCandidatos.keySet()) {
								if (mapaCandidatos.get(key) > maiorQuantiVotos) {
									maiorQuantiVotos = mapaCandidatos.get(key);
									keyCandidatoVencedor = key;
								}
							}

							// verifica se houve empate na votação e guarda os indices de empate dos
							// candidatos
							ArrayList<Integer> keysNomes = new ArrayList<Integer>();
							for (Integer key : mapaCandidatos.keySet()) {
								if (maiorQuantiVotos == mapaCandidatos.get(key)) {
									keysNomes.add(key);
								}
							}

							if (keysNomes.size() > 1) {
								JOptionPane.showMessageDialog(null, "Houve um empate entre os candidatos!!!!", ":P",
										JOptionPane.INFORMATION_MESSAGE);
								System.out.print("Os candidatos empatados foram: ");

								int ultIndex = keysNomes.size() - 1;
								for (int i = 0; i < keysNomes.size(); i++) {
									System.out.print(mapaNomeCandidatos.get(keysNomes.get(i)) + "\n Com: "
											+ maiorQuantiVotos + " número de votos.");
									// Adiciona uma vírgula se não for o último nome
									if (i < ultIndex) {
										System.out.print(", ");
									}
								}
							} else {
								System.out.println("O vencedor foi o candidato...");
								for (int c = 5; c > 0; c--) { // contagem regressiva para mostrar o candidato vencedor
									Thread.sleep(1000);
									if (c > 1) {
										System.out.print(c + ",");
									} else {
										System.out.print(c);
									}
								}

								JOptionPane.showMessageDialog(null,
										mapaNomeCandidatos.get(keyCandidatoVencedor) + " número: "
												+ keyCandidatoVencedor + "\nCom " + maiorQuantiVotos
												+ " número de votos!!!\nParabéns :) ",
										":)", JOptionPane.INFORMATION_MESSAGE);
							}
							System.out.println();
							System.out.println("Tabela com os nomes dos candidatos e dos votos.");
							cont4 = 0;
							for (Integer key : mapaCandidatos.keySet()) {
								cont4++;
								System.out.println(cont4 + "º Candidato nome: " + mapaNomeCandidatos.get(key) + " com "
										+ mapaCandidatos.get(key)
										+ " número(s) de votos!!");
							}

							repetir = false;
							repetir2 = false;
							sc.close();

						} else {
							JOptionPane.showMessageDialog(null,
									"Esse teste tem o objetivo de verificar os candidatos cadastrados no sistema!!!",
									"MENU", JOptionPane.INFORMATION_MESSAGE);
							cont4 = 0;
							for (Integer key : mapaCandidatos.keySet()) {
								cont4++;
								System.out.println(
										+cont4 + "º Candidato nome: " + mapaNomeCandidatos.get(key) + " número: "
												+ key + " votos: " + mapaCandidatos.get(key));
							}
							repetir3 = true;
							while (repetir3) {
								int opcao3 = JOptionPane.showOptionDialog(null, "Você deseja?",
										"MENU", 0, JOptionPane.QUESTION_MESSAGE, null, opcoes3, opcoes3[0]);
								if (opcao3 == 0) {
									String opcoes4[] = { "Mudar número de um candidato", "Mudar nome de um candidato" };
									int opcao4 = JOptionPane.showOptionDialog(null, "Você deseja?",
											"MENU", 0, JOptionPane.QUESTION_MESSAGE, null, opcoes4, opcoes4[0]);
									if (opcao4 == 0) {
										String numCandidatoTemp = JOptionPane
												.showInputDialog("Digite o número do candidato que você deseja mudar");
										String numCandidatoAtualizadoTemp = JOptionPane
												.showInputDialog("Digite um novo número para esse candidato");
										Integer numCandidato = Integer.parseInt(numCandidatoTemp);
										Integer numCandidatoAtualizado = Integer.parseInt(numCandidatoAtualizadoTemp);
										String nomeTemporario = mapaNomeCandidatos.get(numCandidato);
										mapaCandidatos.remove(numCandidato);
										mapaNomeCandidatos.remove(numCandidato);
										mapaCandidatos.put(numCandidatoAtualizado, 0);
										mapaNomeCandidatos.put(numCandidatoAtualizado, nomeTemporario);
										System.out.println("Número atualizado!!! =)");
									} else {
										String keyCandidatoTemp = JOptionPane
												.showInputDialog(
														"Digite o número do candidato que você deseja mudar o nome");
										Integer keyCandidato = Integer.parseInt(keyCandidatoTemp);
										String nomeAtualizado = JOptionPane
												.showInputDialog("Digite um novo nome para esse candidato");
										mapaCandidatos.remove(keyCandidato);
										mapaCandidatos.put(keyCandidato, 0);
										mapaNomeCandidatos.remove(keyCandidato);
										mapaNomeCandidatos.put(keyCandidato, nomeAtualizado);
									}
								} else if (opcao3 == 1) {
									cont4 = 0;
									System.out.println();
									for (Integer key : mapaCandidatos.keySet()) {
										mapaCandidatos.put(key, 0);
										cont4++;
										System.out.println(+cont4 + "º Candidato nome: " + mapaNomeCandidatos.get(key)
												+ " número: "
												+ key + " votos: " + mapaCandidatos.get(key));
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
