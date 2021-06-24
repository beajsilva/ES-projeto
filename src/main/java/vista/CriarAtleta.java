package vista;

import modelo.Atleta;
import modelo.DadosAplicacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarAtleta extends JFrame{
  private JTextField nomeAtleta;
  private JTextField nacionalidadeAtleta;
  private JTextField dataAtleta;
  private JTextField contactoAtleta;
  private JComboBox sexoAtleta;
  private JButton btnCriarAtleta;
  private JPanel painelCriarAtleta;
  private JButton btnCancelar;
  private JLabel msgErro;
  private Atleta atleta;

  public CriarAtleta() {
    super("Criar atleta");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelCriarAtleta);
    pack();
    setVisible(true);
    DadosAplicacao dados = DadosAplicacao.getDadosAplicacao();
    this.atleta = new Atleta();

    btnCriarAtleta.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isValid = true;
        if(nomeAtleta.getText().trim().length() == 0 || nacionalidadeAtleta.getText().trim().length() == 0 || dataAtleta.getText().trim().length() == 0 || sexoAtleta.getSelectedIndex() == -1 || contactoAtleta.getText().trim().length() == 0){
          isValid = false;
        }
        if (isValid) {
          atleta.setNome(nomeAtleta.getText());
          atleta.setPais(nacionalidadeAtleta.getText());
          atleta.setDataNascimento(dataAtleta.getText());
          atleta.setSexo(sexoAtleta.getSelectedItem().toString().toLowerCase());
          atleta.setContacto(contactoAtleta.getText());

          dados.adicionarAtleta(atleta);
          setVisible(false);
          new InformacaoAtleta(atleta.getId());
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
        new ListaAtletas();
      }
    });
  }
}
