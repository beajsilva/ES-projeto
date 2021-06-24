package vista;

import modelo.DadosAplicacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaEtapas extends JFrame{
  private JPanel painelMenu;
  private JButton btnRecordes;
  private JButton btnMedalhados;
  private JButton btnAtletas;
  private JButton btnEventosModalidades;
  private JTable tabelaEtapas;
  private JPanel painelListaEtapas;
  private JLabel provaSubtitulo;
  private JLabel provaTitulo;
  private DadosAplicacao dados;

  public ListaEtapas() {
    super("Etapas da prova");
    dados = DadosAplicacao.getDadosAplicacao();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelListaEtapas);
    pack();
    criarTabelaEtapas();
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
  private void criarTabelaEtapas(){
    final DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Prova");
    model.addColumn("Sexo");
    model.addColumn("Ronda");
    model.addColumn("Data");
    model.addColumn("Atletas");
    model.addColumn("Resultados");

    String row[] = {"", "", "", "", "", ""};
    model.addRow(row);

    tabelaEtapas.setModel(model);
  }
}
