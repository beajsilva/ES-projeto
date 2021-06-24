package vista;

import modelo.DadosAplicacao;
import modelo.Evento;
import modelo.Modalidade;
import modelo.Prova;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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
  private DadosAplicacao dados;
  private ArrayList<String> listaModalidades;

  public EditarEvento(String idEvento) {
    super("Programa do evento");
    DadosAplicacao dados = DadosAplicacao.getDadosAplicacao();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelEditarEvento);
    pack();
    listaModalidades = new ArrayList<>();
    Evento evento = dados.getEvento(idEvento);
    nomeEvento.setText(evento.getNome());
    localEvento.setText(evento.getLocal());
    paisEvento.setText(evento.getPais());
    dataInicioEvento.setText(evento.getDataInicio());
    dataFimEvento.setText(evento.getDataFim());
    for (int i = 0; i < evento.getProvas().size(); i++) {
      Prova prova = evento.getProvas().get(i);
      Modalidade modalidade = prova.getModalidade();
      listaModalidades.add(modalidade.getNome() + "-" + modalidade.getSexo().toUpperCase().charAt(0));
    }
    criarProvasLista();
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
    provasEvento.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
          String itemSelecionado = e.getItem().toString();
          if(!listaModalidades.contains(itemSelecionado)){
            listaModalidades.add(itemSelecionado);
            criarProvasLista();
            repaint();
          }
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

  private void createUIComponents() {
    criarProvasComboBox();
    criarProvasLista();
  }

  private void criarProvasComboBox(){
    dados = DadosAplicacao.getDadosAplicacao();
    ArrayList<Modalidade> modalidades = dados.getModalidades();
    String[] modalidadesList = new String[modalidades.size()];
    for (int i = 0; i < modalidades.size(); i++) {
      Modalidade modalidade = modalidades.get(i);
      modalidadesList[i] = modalidade.getNome() + "-" + modalidade.getSexo().toUpperCase().charAt(0);
    }
    provasEvento = new JComboBox(modalidadesList);
    provasEvento.setSelectedIndex(-1);
  }

  private void criarProvasLista(){
    final DefaultListModel model = new DefaultListModel();
    if(listaModalidades == null || listaModalidades.size() == 0 ){
      model.addElement("");
      provasLista = new JList(model);
    } else {
      for (int i = 0; i < listaModalidades.size(); i++) {
        model.addElement(listaModalidades.get(i));
      }
      provasLista.setModel(model);
    }
  }
}
