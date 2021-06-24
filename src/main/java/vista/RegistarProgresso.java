package vista;

import modelo.DadosAplicacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RegistarProgresso extends JFrame{
  private JLabel registarProgressoSubtitulo;
  private JPanel painelRegistarProgresso;
  private JTable tabelaRegistarProgresso;
  private JButton btnRegistarProgresso;
  private JButton btnCancelar;
  private DadosAplicacao dados;

  public RegistarProgresso(String idEtapa) {
    super("Recordes Mundiais");
    dados = DadosAplicacao.getDadosAplicacao();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelRegistarProgresso);
    pack();
    criarTabelaRegistarProgresso();
    setVisible(true);
  }

  private void criarTabelaRegistarProgresso(){
    final DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Atleta");
    model.addColumn("Marca");

    String row[] = {"", ""};
    model.addRow(row);

    tabelaRegistarProgresso.setModel(model);
  }
}
