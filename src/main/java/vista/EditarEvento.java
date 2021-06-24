package vista;

import modelo.DadosAplicacao;
import modelo.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarEvento extends JFrame{
  private JTextField nomeEvento;
  private JTextField localEvento;
  private JTextField paisEvento;
  private JTextField dataInicioEvento;
  private JTextField dataFimEvento;
  private JComboBox provasEvento;
  private JButton btnEditarEvento;
  private JLabel nomeLabel;
  private JLabel localLabel;
  private JLabel paisLabel;
  private JLabel dataInicioLabel;
  private JLabel dataFimLabel;
  private JLabel provasLabel;
  private JLabel provasListaLabel;
  private JList provasLista;
  private JLabel editarEventoTitulo;
  private JPanel painelEditarEvento;
  private JLabel msgErro;
  private JButton btnCancelar;

  public EditarEvento(String idEvento) {
    super("Programa do evento");
    DadosAplicacao dados = DadosAplicacao.getDadosAplicacao();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelEditarEvento);
    pack();
    Evento evento = dados.getEvento(idEvento);
    nomeEvento.setText(evento.getNome());
    localEvento.setText(evento.getLocal());
    paisEvento.setText(evento.getPais());
    dataInicioEvento.setText(evento.getDataInicio());
    dataFimEvento.setText(evento.getDataFim());
    setVisible(true);
    btnEditarEvento.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isValid = true;
        if (nomeEvento.getText().trim().length() == 0 || localEvento.getText().trim().length() == 0 || paisEvento.getText().trim().length() == 0 || dataInicioEvento.getText().trim().length() == 0 || dataFimEvento.getText().trim().length() == 0) {
          isValid = false;
        }
        if (isValid) {
          evento.setNome(nomeEvento.getText());
          evento.setLocal(localEvento.getText());
          evento.setPais(paisEvento.getText());
          evento.setDataInicio(dataInicioEvento.getText());
          evento.setDataFim(dataFimEvento.getText());
          dados.exportarEvento(evento, false);

          setVisible(false);
          new ProgramaEvento(idEvento);
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
        new ProgramaEvento(idEvento);
      }
    });
  }
}
