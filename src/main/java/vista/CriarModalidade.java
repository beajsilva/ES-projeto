package vista;

import modelo.DadosAplicacao;
import modelo.Modalidade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarModalidade extends JFrame{
  private JTextField nomeModalidade;
  private JComboBox sexoModalidade;
  private JTextField minimoModalidade;
  private JButton btnCriarModalidade;
  private JLabel criarModalidadeTitulo;
  private JLabel nomeModalidadeLabel;
  private JLabel minimoModalidadeLabel;
  private JLabel sexoModalidadeLabel;
  private JPanel painelCriarModalidade;
  private JButton btnCancelar;
  private JLabel msgErro;
  private Modalidade modalidade;

  public CriarModalidade() {
    super("Criar modalidade");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelCriarModalidade);
    pack();
    setVisible(true);
    DadosAplicacao dados = DadosAplicacao.getDadosAplicacao();
    this.modalidade = new Modalidade();

    btnCriarModalidade.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isValid = true;
        if (nomeModalidade.getText().trim().length() == 0 || sexoModalidade.getSelectedIndex() == -1 || minimoModalidade.getText().trim().length() == 0) {
          isValid = false;
        }
        if (isValid) {
          modalidade.setNome(nomeModalidade.getText());
          modalidade.setSexo(sexoModalidade.getSelectedItem().toString().toLowerCase());
          modalidade.setMinimo(minimoModalidade.getText());
          dados.adicionarModalidade(modalidade);
          setVisible(false);
          new ListaEventosModalidades();
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
        new ListaEventosModalidades();
      }
    });
  }
}
