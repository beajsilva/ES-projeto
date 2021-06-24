package vista;

import modelo.Atleta;
import modelo.DadosAplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarAtleta extends JFrame{
  private JTextField nomeAtleta;
  private JTextField nacionalidadeAtleta;
  private JTextField dataAtleta;
  private JTextField contactoAtleta;
  private JComboBox sexoAtleta;
  private JButton btnEditarAtleta;
  private JPanel painelEditarAtleta;
  private JLabel msgErro;
  private JButton btnCancelar;
  private Atleta atleta;

  public EditarAtleta(String idAtleta) {
    super("Editar atleta");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelEditarAtleta);
    pack();
    setVisible(true);
    DadosAplicacao dados = DadosAplicacao.getDadosAplicacao();
    this.atleta = dados.getAtleta(idAtleta);

    nomeAtleta.setText(atleta.getNome());
    nacionalidadeAtleta.setText(atleta.getPais());
    dataAtleta.setText(atleta.getDataNascimento());
    System.out.println('s' + atleta.getSexo());
    if (atleta.getSexo().equals("feminino")){
      sexoAtleta.setSelectedIndex(0);
    } else {
      sexoAtleta.setSelectedIndex(1);
    }
    contactoAtleta.setText(atleta.getContacto());

    btnEditarAtleta.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isValid = true;
        if (nomeAtleta.getText().trim().length() == 0 || nacionalidadeAtleta.getText().trim().length() == 0 || dataAtleta.getText().trim().length() == 0 || sexoAtleta.getSelectedIndex() == -1 || contactoAtleta.getText().trim().length() == 0) {
          isValid = false;
        }
        if (isValid) {
          atleta.setNome(nomeAtleta.getText());
          atleta.setPais(nacionalidadeAtleta.getText());
          atleta.setDataNascimento(dataAtleta.getText());
          atleta.setSexo(sexoAtleta.getSelectedItem().toString().toLowerCase());
          atleta.setContacto(contactoAtleta.getText());
          dados.exportarAtleta(atleta, false);
          setVisible(false);
          new InformacaoAtleta(idAtleta);
        } else {
          msgErro.setVisible(true);
          repaint();
        }
      }
    });
    btnCancelar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new InformacaoAtleta(idAtleta);
      }
    });
  }
}
