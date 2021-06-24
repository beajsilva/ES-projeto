package vista;

import modelo.DadosAplicacao;
import modelo.Modalidade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarModalidade extends JFrame{
  private JTextField nomeModalidade;
  private JComboBox sexoModalidade;
  private JTextField minimoModalidade;
  private JLabel nomeModalidadeLabel;
  private JLabel sexoModalidadeLabel;
  private JLabel minimoModalidadeLabel;
  private JButton btnEditarModalidade;
  private JPanel painelEditarModalidade;
  private JLabel msgErro;
  private JButton btnCancelar;
  private Modalidade modalidade;

  public EditarModalidade(String idModalidade) {
    super("Editar modalidade");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelEditarModalidade);
    pack();
    setVisible(true);
    DadosAplicacao dados = DadosAplicacao.getDadosAplicacao();
    this.modalidade = dados.getModalidade(idModalidade);

    nomeModalidade.setText(modalidade.getNome());
    if (modalidade.getSexo().equals("feminino")){
      sexoModalidade.setSelectedIndex(0);
    } else {
      sexoModalidade.setSelectedIndex(1);
    }
    minimoModalidade.setText(modalidade.getMinimo());

    btnEditarModalidade.addActionListener(new ActionListener() {
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
          dados.exportarModalidade(modalidade, false);
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
