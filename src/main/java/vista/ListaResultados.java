package vista;

import modelo.DadosAplicacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaResultados extends JFrame{
  private JPanel painelMenu;
  private JButton btnRecordes;
  private JButton btnMedalhados;
  private JButton btnAtletas;
  private JButton btnEventosModalidades;
  private JTable tabelaResultados;
  private JPanel painelListaResultados;
  private DadosAplicacao dados;

  public ListaResultados() {
    super("Resultados");
    dados = DadosAplicacao.getDadosAplicacao();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelListaResultados);
    pack();
    criarTabelaResultados();
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
  private void criarTabelaResultados(){
    final DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Posição");
    model.addColumn("Nome");
    model.addColumn("Nacionalidade");
    model.addColumn("Sexo");
    model.addColumn("Data nascimento");
    model.addColumn("Resultado");

    String row[] = {"", "", "", "", "", ""};
    model.addRow(row);

    tabelaResultados.setModel(model);
  }
}
