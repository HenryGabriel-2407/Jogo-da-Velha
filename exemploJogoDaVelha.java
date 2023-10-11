import java.util.Scanner;

public class exemploJogoDaVelha{
    public static void main(String[] args) {
        char[][] tabuleiro = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        exibirJogo(tabuleiro);

        while (true) {
            jogadaJogador(tabuleiro);
            exibirJogo(tabuleiro);
            if (verificarVitoria(tabuleiro, 'X')) {
                System.out.println("Você ganhou!");
                break;
            }
            if (tabuleiroCheio(tabuleiro)) {
                System.out.println("Empate!");
                break;
            }

            jogadaComputador(tabuleiro);
            exibirJogo(tabuleiro);
            if (verificarVitoria(tabuleiro, 'O')) {
                System.out.println("Computador ganhou!");
                break;
            }
            if (tabuleiroCheio(tabuleiro)) {
                System.out.println("Empate!");
                break;
            }
        }
    }

    public static void exibirJogo(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            if (i < 2) {
                System.out.print("\n---------\n");
            }
        }
        System.out.println();
    }

    public static void jogadaJogador(char[][] tabuleiro) {
        Scanner scanner = new Scanner(System.in);
        int linha, coluna;
        do {
            System.out.println("Sua jogada (linha coluna): ");
            linha = scanner.nextInt();
            coluna = scanner.nextInt();
        } while (linha < 0 || linha > 2 || coluna < 0 || coluna > 2 || tabuleiro[linha][coluna] != ' ');
        tabuleiro[linha][coluna] = 'X';
    }

    public static void jogadaComputador(char[][] tabuleiro) {
        int[] melhorJogada = minimax(tabuleiro, 'O');
        tabuleiro[melhorJogada[0]][melhorJogada[1]] = 'O';
    }

    public static int[] minimax(char[][] tabuleiro, char jogador) {
        int[] melhorJogada = new int[]{-1, -1};
        int melhorPontuacao = (jogador == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int pontuacaoAtual;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    tabuleiro[i][j] = jogador;
                    pontuacaoAtual = (jogador == 'O') ? minimaxMax(tabuleiro) : minimaxMin(tabuleiro);
                    if ((jogador == 'O' && pontuacaoAtual > melhorPontuacao) || (jogador == 'X' && pontuacaoAtual < melhorPontuacao)){
                        melhorPontuacao = pontuacaoAtual;
                        melhorJogada[0] = i;
                        melhorJogada[1] = j;
                    }

                    tabuleiro[i][j] = ' '; // Desfaz a jogada
                }
            }
        }
        return melhorJogada;
    }

    public static int minimaxMax(char[][] tabuleiro) {
        if (verificarVitoria(tabuleiro, 'O')) {
            return 1;
        } else if (verificarVitoria(tabuleiro, 'X')) {
            return -1;
        } else if (tabuleiroCheio(tabuleiro)) {
            return 0;
        }
        int melhorPontuacao = Integer.MIN_VALUE;
        int pontuacaoAtual;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    tabuleiro[i][j] = 'O';
                    pontuacaoAtual = minimaxMin(tabuleiro);
                    tabuleiro[i][j] = ' '; // Desfaz a jogada
                    melhorPontuacao = Math.max(melhorPontuacao, pontuacaoAtual);
                }
            }
        }
        return melhorPontuacao;
    }

    public static int minimaxMin(char[][] tabuleiro) {
        if (verificarVitoria(tabuleiro, 'O')) {
            return 1;
        } else if (verificarVitoria(tabuleiro, 'X')) {
            return -1;
        } else if (tabuleiroCheio(tabuleiro)) {
            return 0;
        }

        int melhorPontuacao = Integer.MAX_VALUE;
        int pontuacaoAtual;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    tabuleiro[i][j] = 'X';
                    pontuacaoAtual = minimaxMax(tabuleiro);
                    tabuleiro[i][j] = ' '; // Desfaz a jogada
                    melhorPontuacao = Math.min(melhorPontuacao, pontuacaoAtual);
                }
            }
        }

        return melhorPontuacao;
    }

    public static boolean verificarVitoria(char[][] tabuleiro, char jogador) {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true; // Linhas
            }
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {
                return true; // Colunas
            }
        }
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true; // Diagonal principal
        }
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true; // Diagonal secundária
        }
        return false;
    }

    public static boolean tabuleiroCheio(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}