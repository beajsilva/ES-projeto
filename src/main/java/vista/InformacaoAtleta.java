package vista;

import modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InformacaoAtleta extends JFrame{
  private JPanel painelConteudo;
  private JPanel painelInscricoesHistorico;
  private JTable tabelaInscricoes;
  private JPanel painelRecordes;
  private JTable tabelaRecordes;
  private JPanel painelMenu;
  private JButton btnRecordes;
  private JButton btnMedalhados;
  private JButton btnAtletas;
  private JButton btnEventosModalidades;
  private JLabel tituloAtleta;
  private JPanel painelInformacaoAtleta;
  private JButton btnEditarAtleta;
  private Atleta atleta;
  private DadosAplicacao dados;

  public InformacaoAtleta(String idAtleta) {
    super("Informação do atleta");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelInformacaoAtleta);
    pack();

    dados = DadosAplicacao.getDadosAplicacao();
    atleta = dados.getAtleta(idAtleta);
    tituloAtleta.setText(atleta.getNome().toUpperCase() + " - " + atleta.getSexo().toUpperCase().charAt(0));
    criarTabelaRecordes();
    setVisible(true);

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
    btnEditarAtleta.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new EditarAtleta(idAtleta);
      }
    });
  }

  private void createUIComponents() {
    criarTabelaInscricoes();
  }

  public void criarTabelaInscricoes(){
    dados = DadosAplicacao.getDadosAplicacao();
    ArrayList<Inscricao> listaInscricoes = dados.getInscricoes();
    int numeroInscricoes = listaInscricoes.size();
    String[][] data = new String[numeroInscricoes][5];
    String colunas[] = {"Evento", "Prova", "Marca", "Resultado", ""};

    for (int i = 0; i < listaInscricoes.size(); i++) {
      Inscricao inscricao = listaInscricoes.get(i);
      Evento evento = dados.getEventoComProva(inscricao.getIdProva());
      Prova prova = evento.getProvaComId(inscricao.getIdProva());
      String row[] = {evento.getNome(), prova.toString(), inscricao.getMarcaAtleta(), "TODO: RESULTADO", "    X"};
      data[i] = row;
    }

    tabelaInscricoes = new JTable(data, colunas);
  }

  private void criarTabelaRecordes(){
    final DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Prova");
    model.addColumn("Marca");

    String row[] = {"", ""};
    model.addRow(row);

    tabelaRecordes.setModel(model);
  }
}
