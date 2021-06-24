package vista;

import modelo.DadosAplicacao;
import modelo.Evento;
import modelo.Modalidade;
import modelo.Prova;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProgramaEvento extends JFrame {
  private JPanel painelMenu;
  private JButton btnRecordes;
  private JButton btnMedalhados;
  private JButton btnAtletas;
  private JButton btnEventosModalidades;
  private JButton btnEditarEvento;
  private JButton eliminarEvento;
  private JButton btnInscreverAtleta;
  private JTable tabelaProvas;
  private JLabel eventoTitulo;
  private JLabel eventoSubtitulo;
  private JPanel painelBotoesEvento;
  private JPanel painelConteudoEvento;
  private JPanel painelProgramaEvento;
  private DadosAplicacao dados;
  private Evento evento;

  public ProgramaEvento(String idEvento) {
    super("Programa do evento");
    DadosAplicacao dados = DadosAplicacao.getDadosAplicacao();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelProgramaEvento);
    pack();
    evento = dados.getEvento(idEvento);
    eventoTitulo.setText(evento.getNome());
    eventoSubtitulo.setText(evento.getPais() + " " + evento.getDataInicio() + " - " + evento.getDataFim());
    criarProvasLista();
    setVisible(true);

    btnEditarEvento.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new EditarEvento(idEvento);
      }
    });
    eliminarEvento.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dados.eliminarEvento(idEvento);
        setVisible(false);
        new ListaEventosModalidades();
      }
    });
    btnInscreverAtleta.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new InscreverAtleta(idEvento);
      }
    });
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
    btnMedalhados.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new PaisesMaisMedalhados();
      }
    });
  }

  private void criarProvasLista(){
    final DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Prova");
    model.addColumn("Sexo");
    model.addColumn("");
    model.addColumn("");

    for (int i = 0; i < evento.getProvas().size(); i++) {
      Prova prova = evento.getProvas().get(i);
      String row[] = {prova.getModalidade().getNome(), prova.getModalidade().getSexo(), "Atletas", "Resultados"};
      model.addRow(row);
    }
    tabelaProvas.setModel(model);
  }
}
