//  O que falta: verificar empate;
// importação
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.awt.*;

public class ModoOtaku{
    // Declaração
    JFrame frame;
    JPanel panel;
    boolean turnoX = true;
    int pontuacao = 10;
    String nome__otaku;
    JButton patos[] = new JButton[9];
    Random random = new Random();
    int jogadaComputador;
    int empate = 1;
    ModoOtaku(){
        //Criar a janela e barra de menu
        nome__otaku = JOptionPane.showInputDialog(null, "Digite o seu nome Naruto:");
        frame = new JFrame("Jutsu solitário!");
        JMenuBar menu = new JMenuBar();
        JMenu fileJMenu = new JMenu("Menu");
        JMenuItem inicio = new JMenuItem("Tela inicial");
        JMenuItem sair = new JMenuItem("Sair");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
        //Aparência do jogo
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,3, 5, 5));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for(int i = 0; i < 9; i++){
            patos[i] = new JButton(" ");
            patos[i].setFont(new Font("Arial", Font.PLAIN, 40));
            panel.add(patos[i]);
        }
        // Ações com os botões
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
        //Jogadas
        
        for (int i = 0; i < 9; i++) {
            patos[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton pato = (JButton) e.getSource();
                    if(turnoX){
                        pato.setText("X");
                        pato.setEnabled(false);
                    } else{
                        do {
                            jogadaComputador = random.nextInt(9);
                        } while (!patos[jogadaComputador].isEnabled());
                        patos[jogadaComputador].setText("O");
                        patos[jogadaComputador].setEnabled(false);
                    }
                    turnoX = !turnoX;
                    pontuacao--;
                    empate++;
                    
                    if(verificarJogo() || verificarColuna()){
                        if(turnoX){
                            JOptionPane.showMessageDialog(frame, "Parabéns "+nome__otaku+". Você ganhou "+pontuacao * 100+" pontos!");
                            armazenarPontuacao(nome__otaku, pontuacao);
                        } else if(!turnoX){
                            JOptionPane.showMessageDialog(frame, "Computador ganhou!");
                        }
                        pontuacao = 10;
                        limpar();
                    } else if(verificarEmpate()){
                        JOptionPane.showMessageDialog(frame, "Empate! Você ganhou 100 pontos!");
                        armazenarPontuacao(nome__otaku, 1);
                        pontuacao = 10;
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
        for(int i = 0; i < 9; i += 3){
            if(patos[i].getText().equals(patos[i+1].getText()) && patos[i].getText().equals(patos[i+2].getText()) && !patos[i].isEnabled()){
                limpar();
                return true;
            }
        //verificar diagonal principal
            else if (patos[0].getText().equals(patos[4].getText()) && patos[0].getText().equals(patos[8].getText()) && !patos[0].isEnabled()) {
                limpar();
                return true;
            } 
        // verificar diagonal secundária
            else if (patos[2].getText().equals(patos[4].getText()) && patos[2].getText().equals(patos[6].getText()) && !patos[2].isEnabled()) {
                limpar();
                return true;
            }
            else{
                return false;
            }            
        } //verificar coluna
        return false;
    }
    public boolean verificarColuna(){
        for (int i = 0; i < 9; i++) {
            if(patos[i].getText().equals(patos[i+3].getText()) && patos[i].getText().equals(patos[i+6].getText()) && !patos[i].isEnabled()){
                limpar();
                return true;
            }
        }
        return false;
    }
    public boolean verificarEmpate(){
        int cont = 0;
        for (int i = 0; i < 9; i++) {
            if (!patos[i].isEnabled()) {
                cont++;
            }
            if(cont >= 8 && verificarColuna() == false && verificarJogo() == false){
                cont = 0;
                return true;
            }
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
    public void armazenarPontuacao(String nome__otaku, int pontuacao){
        pontuacao = pontuacao * 100;
        File arquivo = new File("C:\\Users\\henry\\OneDrive\\Documentos\\GitHub\\Jogo-da-Velha-Java\\Banco_de_dados.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;
            StringBuilder conteudo = new StringBuilder();
            boolean nomeEncontrado = false;
            while((linha = br.readLine()) != null){
                if(linha.startsWith(nome__otaku + ",")){
                    nomeEncontrado = true;
                    String[] partes = linha.split(",");
                    int pontuacaoExistente = Integer.parseInt(partes[1].trim());
                    pontuacao += pontuacaoExistente;
                    linha = nome__otaku + "," + pontuacao;
                }
                conteudo.append(linha).append("\n");
            }
            br.close();
            if (!nomeEncontrado) {
                conteudo.append(nome__otaku).append(",").append(pontuacao).append("\n");
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));
            bw.write(conteudo.toString());
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {    
        new ModoOtaku();
    }
}