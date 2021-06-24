package vista;

import modelo.DadosAplicacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Recordes extends JFrame{
  private JPanel painelRecordes;
  private JPanel painelMenu;
  private JButton btnRecordes;
  private JButton btnMedalhados;
  private JButton btnAtletas;
  private JButton btnEventosModalidades;
  private JTable tabelaRecordes;
  private DadosAplicacao dados;

  public Recordes() {
    super("Recordes Mundiais");
    dados = DadosAplicacao.getDadosAplicacao();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelRecordes);
    pack();
    criarTabelaRecordes();
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
    btnMedalhados.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new PaisesMaisMedalhados();
      }
    });
  }
  private void criarTabelaRecordes(){
    final DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Prova");
    model.addColumn("Sexo");
    model.addColumn("País");
    model.addColumn("Marca");

    String row[] = {"", "", "", ""};
    model.addRow(row);

    tabelaRecordes.setModel(model);
  }
}
