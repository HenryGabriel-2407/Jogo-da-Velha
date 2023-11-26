//importação
import java.awt.Button;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class JogoDaVelhaInterface implements ActionListener{
    JogoDaVelhaInterface() { //Tela Inicial do Jogo
        JFrame frame = new JFrame("Jogo da Velha - Tela inicial");
        JPanel panel = new JPanel();
        JRadioButton otaku, amigos;
        ButtonGroup grupo_botoes = new ButtonGroup();
        Button botao_comecar = new Button("Vamos começar!");
        JLabel label = new JLabel();
        //Barra de menu e seus comportamentos
        JMenuBar menu = new JMenuBar();
        JMenu fileJMenu = new JMenu("Menu");
        JMenuItem dadosMenuItem = new JMenuItem("Dados");
        JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        }); //fecha o programa
        dadosMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DadosJogadores();
                frame.dispose();
            }
        }); // vai ao BancoJDV

        panel.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // opção para fechar o programa
        frame.setSize(600, 400); //tamanho da interface 
        frame.setLayout(null);
        frame.setVisible(true); 

        // Explicando como se joga
        JLabel funcionamentoJLabel = new JLabel("<html><div style='text-align: center;'>Olá! Bem vindo ao jogo da velha! <br> O jogador (X) é o primeiro a jogar em seguida o jogador (O) <br>(se for no modo otaku, o computador é o jogador O); <br> O objetivo é fazer uma linha, uma coluna, ou uma diagonal de seu símbolo. <br><br><br></div></html>");
        // adicionado JRadioButton para selecionar modo de jogo
        label.setText("Digite o modo de jogo:");
        otaku = new JRadioButton("Modo Otaku ");
        amigos = new JRadioButton("Modo Amigos ");
        grupo_botoes.add(amigos);
        grupo_botoes.add(otaku);

        //montagem do JFame
        fileJMenu.add(dadosMenuItem);
        fileJMenu.add(sair);
        menu.add(fileJMenu);
        panel.add(funcionamentoJLabel);
        panel.add(label);
        panel.add(otaku);
        panel.add(amigos);
        panel.add(botao_comecar);
        frame.setJMenuBar(menu);
        frame.add(panel);

        //Ao clicar no botão começar, abre um novo JFrame em suas classes, se caso não ser selecionado nem Modo Amigos e nem Modo Otaku aparece a mensagem para selecionar o modo de jogo.
        botao_comecar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(otaku.isSelected()){
                    new ModoOtaku(); 
                    frame.dispose();
                } else if (amigos.isSelected()){
                    new ModoAmigos();
                    frame.dispose();
                } else{
                    JOptionPane.showMessageDialog(frame, "Por favor, SELECIONE UM MODO DE JOGO!");
                }
            }
        });
    }
    public static void main(String[] args) {
        new JogoDaVelhaInterface();
    }
}
