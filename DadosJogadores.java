//Importação
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class DadosJogadores{
    JFrame frame;
    JPanel panel;
    DefaultTableModel model;
    JTable tabela;
    DadosJogadores(){
        //JFrame e JPanel
        frame = new JFrame("Listas de Jogadores");
        panel = new JPanel();
        panel.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setVisible(true);
        // Barra de menu e seu comportamento
        JMenuBar menu = new JMenuBar();
        JMenu fileJMenu = new JMenu("Menu");
        JMenuItem inicio = new JMenuItem("Inicio");
        JMenuItem sair = new JMenuItem("Sair");
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
        // Cria a tabela
        String[] colunas = {"Nome", "Pontuação"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(colunas);
        tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        pegarDados();

        fileJMenu.add(inicio);
        fileJMenu.add(sair);
        menu.add(fileJMenu);
        frame.setJMenuBar(menu);
        panel.add(scrollPane);
        frame.add(panel);
    }
    public void pegarDados() {
        File arquivo = new File("C:\\Users\\henry\\OneDrive\\Documentos\\GitHub\\Jogo-da-Velha-Java\\Banco_de_dados.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                model.addRow(dados);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DadosJogadores();
            }
        });
    }

}