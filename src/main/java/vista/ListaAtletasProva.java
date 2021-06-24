package vista;

import modelo.DadosAplicacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaAtletasProva extends JFrame{
  private JPanel painelMenu;
  private JButton btnRecordes;
  private JButton btnMedalhados;
  private JButton btnAtletas;
  private JButton btnEventosModalidades;
  private JPanel painelListaAtletasProva;
  private JTable tabelaAtletasProva;
  private DadosAplicacao dados;

  public ListaAtletasProva() {
    super("Atletas da prova");
    dados = DadosAplicacao.getDadosAplicacao();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelListaAtletasProva);
    pack();
    criarTabelaAtletasProva();
    setVisible(true);

    btnEventosModalidades.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new ListaEventosModalidades();
      }
    });
    btnAtletas.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new ListaAtletas();
      }
    });
    btnRecordes.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Recordes();
      }
    });
    btnMedalhados.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new PaisesMaisMedalhados();
      }
    });
  }
  private void criarTabelaAtletasProva(){
    final DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Nome");
    model.addColumn("Nacionalidade");
    model.addColumn("Sexo");
    model.addColumn("Data nascimento");
    model.addColumn("Mínimos");

    String row[] = {"", "", "", "", ""};
    model.addRow(row);

    tabelaAtletasProva.setModel(model);
  }
}
