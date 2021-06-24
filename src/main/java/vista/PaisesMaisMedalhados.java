package vista;

import modelo.DadosAplicacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaisesMaisMedalhados extends JFrame{
  private JPanel painelMenu;
  private JButton btnRecordes;
  private JButton btnMedalhados;
  private JButton btnAtletas;
  private JButton btnEventosModalidades;
  private JTable tabelaMedalhados;
  private JComboBox filtros;
  private JPanel painelMedalhados;
  private DadosAplicacao dados;

  public PaisesMaisMedalhados() {
    super("Países mais medalhados");
    dados = DadosAplicacao.getDadosAplicacao();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelMedalhados);
    pack();
    criarTabelaMedalhados();
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
  }

  private void criarTabelaMedalhados(){
    final DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Posição");
    model.addColumn("País");
    model.addColumn("Ouro");
    model.addColumn("Prata");
    model.addColumn("Bronze");
    model.addColumn("Medalhas");

    String row[] = {"", "", "", "", "", ""};
    model.addRow(row);

    tabelaMedalhados.setModel(model);
  }
}
