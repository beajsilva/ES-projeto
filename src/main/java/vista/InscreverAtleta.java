package vista;

import modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class InscreverAtleta extends JFrame{
  private JTextField marcaInscrever;
  private JComboBox provasInscrever;
  private JComboBox atletasInscrever;
  private JButton btnInscreverAtleta;
  private JLabel minimoProvaLabel;
  private JPanel painelInscreverAtleta;
  private JLabel msgErro;
  private JButton btnCancelar;
  private Inscricao inscricao;
  private DadosAplicacao dados;

  public InscreverAtleta(String idEvento) {
    super("Inscrever atleta");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelInscreverAtleta);
    pack();
    setVisible(true);
    dados = DadosAplicacao.getDadosAplicacao();
    this.inscricao = new Inscricao();

    criarAtletasModel();
    criarProvasModel(idEvento);

    btnInscreverAtleta.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isValid = true;
        if (atletasInscrever.getSelectedIndex() == -1 || provasInscrever.getSelectedIndex() == -1 || marcaInscrever.getText().trim().length() == 0) {
          isValid = false;
        }
        if (isValid) {
          String idAtleta = dados.getIdAtleta(atletasInscrever.getSelectedItem().toString());
          inscricao.setIdAtleta(idAtleta);

          String itemNome = provasInscrever.getSelectedItem().toString().split("-")[0];
          String itemSexo = provasInscrever.getSelectedItem().toString().split("-")[1].toUpperCase().equals("F") ? "feminino" : "masculino";
          Modalidade modalidade = dados.getModalidade(itemNome, itemSexo);
          String idProva = dados.getEvento(idEvento).getProva(modalidade.getId()).getId();
          inscricao.setIdProva(idProva);
          inscricao.setMarcaAtleta(marcaInscrever.getText());
          dados.adicionarInscricao(inscricao);
          setVisible(false);
          new ProgramaEvento(idEvento);
        } else {
          msgErro.setVisible(true);
          repaint();
        }
      }
    });
    provasInscrever.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
          String itemSelecionado = e.getItem().toString();
          String itemNome = itemSelecionado.split("-")[0];
          String itemSexo = itemSelecionado.split("-")[1].toUpperCase().equals("F") ? "feminino" : "masculino";
          Modalidade modalidade = dados.getModalidade(itemNome, itemSexo);
          minimoProvaLabel.setText("Mínimo da prova: " + modalidade.getMinimo());

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

  private void criarAtletasModel(){
    final DefaultComboBoxModel model = new DefaultComboBoxModel();
    ArrayList<Atleta> atletas = dados.getAtletas();
    for (int i = 0; i < atletas.size(); i++) {
      Atleta atleta = atletas.get(i);
      model.addElement(atleta.getNome());
    }

    atletasInscrever.setModel(model);
    atletasInscrever.setSelectedIndex(-1);

  }

  private void criarProvasModel(String idEvento){
    final DefaultComboBoxModel model = new DefaultComboBoxModel();
    Evento evento = dados.getEvento(idEvento);
    ArrayList<Prova> provas = evento.getProvas();
    for (int i = 0; i < provas.size(); i++) {
      Prova prova = provas.get(i);
      model.addElement(prova.toString());
    }
    if(provas.size() == 0){
      model.addElement("");
    }
    provasInscrever.setModel(model);
    provasInscrever.setSelectedIndex(-1);
  }
}
