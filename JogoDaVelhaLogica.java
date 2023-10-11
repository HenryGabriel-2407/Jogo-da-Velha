import java.util.*;
import java.util.Random;
import java.io.*;
public class JogoDaVelhaLogica{
    public static void main(String[] args) {
        Scanner pato = new Scanner (System.in);
        boolean menu = true;
        while (menu) {
            System.out.println();
            System.out.print("Digite [1]Vs Computador; [2]2 jogadores; [3]Funcionamento; [4]Sair: ");
            int jogadores = pato.nextInt();
            switch (jogadores) {
                case 1:
                    vsComputador(pato);
                    break;
                case 2:
                    doisJogadores(pato);
                    break;
                case 3:
                    funcionamento();
                    break;
                case 4:
                    System.out.println("Tchau!!!");
                    menu = false;
                    break;
                default:
                    System.out.println("Opção Inválida.");
                    continue;
            }
        }
    }

    public static void exibirJogo(char[][] matriz){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matriz[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            if (i<2) {
                System.out.print("\n==========\n");
            } 
        }
        System.out.println();
    }

    public static void vsComputador(Scanner pato){
        System.out.println("Vem no x1, otário.\n");
        int linha, coluna;
        char [][] matriz = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
        int cont = 0;
        boolean andamento = true;
        while(andamento){
            if (cont % 2 == 0) {
                System.out.println("Digite as posições jogador (X) [linha x coluna]: ");
                linha = pato.nextInt();
                coluna = pato.nextInt();
                if (matriz[linha][coluna] == ' ') { matriz[linha][coluna] = 'X';} 
                else{
                    System.out.println("Posição Ocupada! Tente Novamente.");
                    continue;
                }
            }
            if(cont % 2 != 0){
                Random papagaio = new Random();
                linha = papagaio.nextInt(3);
                coluna = papagaio.nextInt(3);
                if (linha >= 0 && coluna >= 0) {
                    if (matriz[linha][coluna] == ' ') {matriz[linha][coluna] = 'O';}
                    else{continue;}
                }
                else{continue;}
            }
            if(cont % 2 != 0){
                exibirJogo(matriz);
            }
            cont++;
            if (cont >= 5) {
                if (verificarJogo(matriz)){
                    exibirJogo(matriz);
                    if (cont % 2 != 0) {
                        System.out.print("Digite seu nome, vencedor: ");
                        String player = pato.next();
                        int pontuacao = (9 - cont) * 100;
                        criarCSV(player, pontuacao);
                    }
                    andamento = false;
                }
                else if (cont == 9) {
                    exibirJogo(matriz);
                    andamento = false;
                    System.out.println("Empate!!!!");
                    System.out.print("Digite seu nome, jogador (X): ");
                    String player = pato.next();
                    int pontuacao = 50;
                    criarCSV(player, pontuacao);
                }
            }
        }
        main(null);
    }
    
    public static void doisJogadores(Scanner pato){
        int linha, coluna;
        char [][] matriz = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
        exibirJogo(matriz);
        int cont = 0;
        boolean andamento = true;
        while (andamento) {
            if (cont % 2 == 0) {
                System.out.println("Digite jogador (X):");
                linha  = pato.nextInt();
                coluna = pato.nextInt();
                if(matriz[linha][coluna] == ' '){ matriz[linha][coluna] = 'X';} 
                else{
                    System.out.println("Posição já ocupada! Escolha outra posição:");
                    continue;
                }
            }
            else{
                System.out.println("Digite jogador (O):");
                linha  = pato.nextInt();
                coluna = pato.nextInt();
                if(matriz[linha][coluna] == ' '){matriz[linha][coluna] = 'O';} 
                else{
                    System.out.println("Posição já ocupada! Escolha outra posição:");
                    continue;
                }
            }
            exibirJogo(matriz);
            cont++;
            if (cont >= 5) {
                if (verificarJogo(matriz)) {
                    andamento = false;
                    System.out.print("Digite seu nome, vencedor: ");
                    String player = pato.next();
                    int pontuacao = (9 - cont) * 100;
                    criarCSV(player, pontuacao);
                }
                else if (cont == 9) {
                    System.out.println("Empate!!!!");
                    andamento = false;
                    System.out.print("Digite seu nome, jogador (X): ");
                    String player = pato.next();
                    int pontuacao = 50;
                    criarCSV(player, pontuacao);
                    System.out.print("Digite seu nome, jogador (O): ");
                    player = pato.next();
                    criarCSV(player, pontuacao);
                }
            }
        }
        main(null);
    }

    public static boolean verificarJogo(char[][] matriz){
        Scanner pato = new Scanner(System.in);
        //venceu linha
        for (int i = 0; i < 3; i++) {
            if (matriz[i][0] == matriz[i][1] && matriz[i][0] == matriz[i][2] && matriz[i][0] != ' ') {
                if (matriz[i][0] == 'O') {
                    System.out.println("Jogador (O) és o vencedor!!");
                    pato.nextLine();
                    return true;
                } if (matriz[i][0] == 'X') {
                    System.out.println("Jogador (X) és o vencedor!!");
                    pato.nextLine();
                    return true;
                }
            }
        }
        //venceu coluna
        for (int j = 0; j < 3; j++) {
            if (matriz[0][j] == matriz[1][j] && matriz[0][j] == matriz[2][j] && matriz[2][j] != ' ') {
                if (matriz[j][0] == 'O') {
                    System.out.println("Jogador (O) és o vencedor!!");
                    pato.nextLine();
                    return true;
                } if (matriz[j][0] == 'X'){
                    System.out.println("Jogador (X) és o vencedor!!");
                    pato.nextLine();
                    return true;
                }
            }
        }
        //venceu diagonal
        if ((matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2] && matriz[0][0] != ' ') || (matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0] && matriz[2][0] != ' ')){
            if (matriz[1][1] == 'O') {
                    System.out.println("Jogador (O) és o vencedor!!");
                    pato.nextLine();
                    return true;
                } if (matriz[1][1] == 'X'){
                    System.out.println("Jogador (X) és o vencedor!!");
                    pato.nextLine();
                    return true;
                }
        }
        return false;
    }

    public static void criarCSV(String player, int pontuacao){
        String nomeArquivo = "C:\\Users\\henry\\OneDrive\\Documentos\\GitHub\\Jogo-da-Velha-Java\\jogadores.csv";
        try (Writer writer = new FileWriter(nomeArquivo, true)) {
            if (new File(nomeArquivo).length() == 0) {
                writer.write("Nome, Pontos\n");
            }
            writer.write("\n" + player + ", " + pontuacao + "\n");
            writer.close();
            System.out.println("Arquivo CSV criado com sucesso!\n");
        } catch (IOException e){e.printStackTrace();}
    }
    public static void funcionamento(){
        System.out.println();
        System.out.println("FUNCIONAMENTO DO JOGO DA VELHA: \n\tO jogador (X) é o primeiro a jogar em seguida o jogador (O), no caso contra o computador (O).\n\tO jogador irá digitar a posição por linha x coluna.\n\tEx: Digite jogador (X):\n\t0 (é a posição da linha)\n\t1 (é a posição da coluna)\n\tResultado:\n ");
        char [][] matriz = {{' ','X',' '},{' ',' ',' '},{' ',' ',' '}};
        exibirJogo(matriz);
        System.out.println("E quem completar uma linha, coluna, ou diagonal com mesmo caractere vence o jogo\n");
        main(null);
    }
}