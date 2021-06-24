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

public class CriarEvento extends JFrame{
  private JTextField nomeEvento;
  private JTextField localEvento;
  private JTextField paisEvento;
  private JTextField dataInicioEvento;
  private JTextField dataFimEvento;
  private JComboBox provasEvento;
  private JButton btnCriarEvento;
  private JList provasLista;
  private JPanel painelCriarEvento;
  private JLabel msgErro;
  private JButton btnCancelar;
  private Evento evento;
  private DadosAplicacao dados;
  private ArrayList<String> listaModalidades;

  public CriarEvento() {
    super("Criar evento");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelCriarEvento);
    pack();
    setVisible(true);
    dados = DadosAplicacao.getDadosAplicacao();
    listaModalidades = new ArrayList<>();
    this.evento = new Evento();
    btnCriarEvento.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isValid = true;
        if(nomeEvento.getText().trim().length() == 0 || localEvento.getText().trim().length() == 0 || paisEvento.getText().trim().length() == 0 || dataInicioEvento.getText().trim().length() == 0 || dataFimEvento.getText().trim().length() == 0){
          isValid = false;
        }
        if (isValid) {
          evento.setNome(nomeEvento.getText());
          evento.setLocal(localEvento.getText());
          evento.setPais(paisEvento.getText());
          evento.setDataInicio(dataInicioEvento.getText());
          evento.setDataFim(dataFimEvento.getText());

          for (int i = 0; i < listaModalidades.size(); i++) {
            String mod = listaModalidades.get(i);
            String itemNome = mod.split("-")[0];
            String itemSexo = mod.split("-")[1].toUpperCase().equals("F") ? "feminino" : "masculino";
            Modalidade modalidade = dados.getModalidade(itemNome, itemSexo);
            Prova prova = new Prova(evento.getId(), modalidade);
            evento.adicionarProva(prova);
          }

          dados.adicionarEvento(evento);
          evento.gerarPrograma();
          setVisible(false);
          new ProgramaEvento(evento.getId());
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
        new ListaEventosModalidades();
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
