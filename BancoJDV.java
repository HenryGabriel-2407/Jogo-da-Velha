/*  O QUE FALTA?
 * Abrir a interface
 * Criar a tabela
 * pegar os dados que está no arquivo txt
 * barra de menu
 */

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

public class BancoJDV{
    JFrame frame;
    JPanel panel;
    DefaultTableModel model;
    JTable tabela;
    BancoJDV(){
        frame = new JFrame("Listas de Jogadores");
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
        
        String[] colunas = {"Nome", "Pontuação"};
        model = new DefaultTableModel();
        tabela = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tabela);
        pegarDados();
    }
    public void pegarDados() {
        File arquivo = new File("C:\\Users\\henry\\OneDrive\\Documentos\\GitHub\\Jogo-da-Velha-Java\\jogadores.csv");
        try {
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(", ");
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
                new BancoJDV();
            }
        });
    }

}
