//  O que falta: verificar diagonais e empate; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class ModoAmigos{
    JFrame frame;
    JPanel panel;
    boolean turnoX = true;
    JButton patos[] = new JButton[9];
    int pontuacao = 9;
    int empate = 1;
    String nome_Amigo1, nome_Amigo2;
    ModoAmigos(){
        nome_Amigo1 = JOptionPane.showInputDialog(null, "Digite o seu nome, jogador X:");
        nome_Amigo2 = JOptionPane.showInputDialog(null, "Digite seu nome, jogador O: ");
        frame = new JFrame("Garoto de programa com amigos???");
        JMenuBar menu = new JMenuBar();
        JMenu fileJMenu = new JMenu("Menu");
        JMenuItem inicio = new JMenuItem("Tela inicial");
        JMenuItem sair = new JMenuItem("Sair");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3,3, 5, 5));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for(int i = 0; i < 9; i++){
            patos[i] = new JButton(" ");
            patos[i].setFont(new Font("Arial", Font.PLAIN, 40));
            panel.add(patos[i]);
        }

        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        inicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JogoDaVelhaInterface();
                frame.dispose();
            }
        });
        for (int i = 0; i < 9; i++) {
            patos[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton pato = (JButton) e.getSource();
                    if(turnoX){
                        pato.setText("X");
                    } else{
                        pato.setText("O");
                    } 
                    pato.setEnabled(false);
                    turnoX = !turnoX;
                    pontuacao--;
                    empate++;
                    if (verificarJogo() == true && turnoX == true){
                        JOptionPane.showMessageDialog(frame, nome_Amigo1 + " venceu a partida!!!");
                        armazenarPontuacaoX(nome_Amigo1, pontuacao);
                    } else if (verificarJogo() == true && turnoX == false){
                        JOptionPane.showMessageDialog(frame, nome_Amigo2 + " venceu a partida!!!");
                        armazenarPontuacaoY(nome_Amigo2, pontuacao);
                    } else if (empate == 9){
                            JOptionPane.showMessageDialog(frame, nome_Amigo1 + " e "+ nome_Amigo2 + " empataram a partida!!!");
                            pontuacao = 1;
                            armazenarPontuacaoX(nome_Amigo1, pontuacao);
                            armazenarPontuacaoY(nome_Amigo2, pontuacao);
                            limpar();
                    }
                }
            });
        }
        fileJMenu.add(inicio);
        fileJMenu.add(sair);
        menu.add(fileJMenu);
        frame.setJMenuBar(menu);
        frame.add(panel, BorderLayout.CENTER);
    }     
    

    public boolean verificarJogo(){
        //verificar linha
        for(int i = 0; i < 9; i+=3){
            if(patos[i].getText().equals(patos[i+1].getText()) && patos[i].getText().equals(patos[i+2].getText()) && !patos[i].isEnabled()){
                limpar();
                return true;
            }
        }
        //verificar coluna
        for(int i = 0; i < 9; i++){
            if(patos[i].getText().equals(patos[i+3].getText()) && patos[i].getText().equals(patos[i+6].getText()) && !patos[i].isEnabled()){
                limpar();
                return true;
            }
        }
        //verficar diagonais
        if (!patos[0].getText().equals(" ") && patos[0].getText().equals(patos[4].getText()) && patos[0].getText().equals(patos[8].getText()) && !patos[0].isEnabled()) {
            limpar();
            return true;
        } else if (!patos[2].getText().equals(" ") && patos[2].getText().equals(patos[4].getText()) && patos[2].getText().equals(patos[6].getText()) && !patos[2].isEnabled()) {
            JOptionPane.showMessageDialog(frame, patos[2].getText() + " Ganhou!!!");
            limpar();
            return true;
        }
        return false;
    }
    public void limpar(){
        for(int i=0; i<9; i++) {
            patos[i].setEnabled(true);
            patos[i].setText(" ");
        }
        turnoX = true;
    }
    //Armazenar o nome e a pontuação para bancoJogo.java
    public void armazenarPontuacaoX(String nome_Amigos1, int pontuacao){
        pontuacao = pontuacao * 100;
        File arquivo = new File("C:\\Users\\henry\\OneDrive\\Documentos\\GitHub\\Jogo-da-Velha-Java\\Banco_de_dados.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;
            StringBuilder conteudo = new StringBuilder();
            boolean nomeEncontrado = false;
            while((linha = br.readLine()) != null){
                if(linha.startsWith(nome_Amigo1 + ",")){
                    nomeEncontrado = true;
                    String[] partes = linha.split(",");
                    int pontuacaoExistente = Integer.parseInt(partes[1].trim());
                    pontuacao += pontuacaoExistente;
                    linha = nome_Amigos1 + "," + pontuacao;
                }
                conteudo.append(linha).append("\n");
            }
            br.close();
            if (!nomeEncontrado) {
                conteudo.append(nome_Amigos1).append(",").append(pontuacao).append("\n");
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));
            bw.write(conteudo.toString());
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void armazenarPontuacaoY(String nome_Amigos2, int pontuacao){
        pontuacao = pontuacao * 100;
        File arquivo = new File("C:\\Users\\henry\\OneDrive\\Documentos\\GitHub\\Jogo-da-Velha-Java\\Banco_de_dados.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;
            StringBuilder conteudo = new StringBuilder();
            boolean nomeEncontrado = false;
            while((linha = br.readLine()) != null){
                if(linha.startsWith(nome_Amigo2 + ",")){
                    nomeEncontrado = true;
                    String[] partes = linha.split(",");
                    int pontuacaoExistente = Integer.parseInt(partes[1].trim());
                    pontuacao += pontuacaoExistente;
                    linha = nome_Amigos2 + "," + pontuacao;
                }
                conteudo.append(linha).append("\n");
            }
            br.close();
            if (!nomeEncontrado) {
                conteudo.append(nome_Amigos2).append(",").append(pontuacao).append("\n");
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));
            bw.write(conteudo.toString());
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ModoAmigos();
    }
}