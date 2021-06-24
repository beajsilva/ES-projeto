package vista;

import modelo.DadosAplicacao;
import modelo.Evento;
import modelo.Modalidade;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListaEventosModalidades extends JFrame {
  private JPanel painelMenu;
  private JPanel painelConteudo;
  private JPanel painelEventos;
  private JPanel painelModalidades;
  private JLabel eventosTitulo;
  private JPanel painelEventosBotoes;
  private JButton btnAdicionarEvento;
  private JLabel modalidadesTitulo;
  private JPanel painelModalidadesBotoes;
  private JButton btnAdicionarModalidades;
  private JButton btnEventosModalidades;
  private JButton btnAtletas;
  private JButton btnRecordes;
  private JButton btnMedalhados;
  private JPanel painelEventosModalidades;
  private JTable tabelaEventos;
  private JTable tabelaModalidades;
  private DadosAplicacao dados;

  public ListaEventosModalidades() {
    super("Eventos e modalidades");
    dados = DadosAplicacao.getDadosAplicacao();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelEventosModalidades);
    pack();
    setVisible(true);

    btnAdicionarEvento.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new CriarEvento();
      }
    });

    tabelaEventos.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int row = tabelaEventos.rowAtPoint(e.getPoint());
        int col = tabelaEventos.columnAtPoint(e.getPoint());
        if (row >= 0 && col >= 0) {
          ArrayList<Evento> eventos = dados.getEventos();
          if(row < eventos.size()){
            String idEvento = eventos.get(row).getId();
            setVisible(false);
            new ProgramaEvento(idEvento);
          }
        }
      }
    });
    btnAdicionarModalidades.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new CriarModalidade();
      }
    });

    tabelaModalidades.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int row = tabelaModalidades.rowAtPoint(e.getPoint());
        int col = tabelaModalidades.columnAtPoint(e.getPoint());
        if (row >= 0 && col >= 0) {
          ArrayList<Modalidade> modalidades = dados.getModalidades();
          if (row < modalidades.size()) {
            String idModalidade = modalidades.get(row).getId();

            if(col == 4){
              dados.eliminarModalidade(idModalidade);
              setVisible(false);
              new ListaEventosModalidades();
            }
            if(col == 3){
              setVisible(false);
              new EditarModalidade(idModalidade);
            }
          }
        }
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

  public void criarTabelaEventos(){
    dados = DadosAplicacao.getDadosAplicacao();
    ArrayList<Evento> listaEventos = dados.getEventos();
    int numeroEventos = listaEventos.size();
    String[][] data = new String[numeroEventos][4];
    String colunas[] = {"Nome", "Local", "País", "Data"};

    for (int i = 0; i < listaEventos.size(); i++) {
      Evento evento = listaEventos.get(i);
      String row[] = {evento.getNome(), evento.getLocal(), evento.getPais(), evento.getDataInicio() + " - " + evento.getDataFim()};
      data[i] = row;
    }

    tabelaEventos = new JTable(data, colunas);
  }

  public void criarTabelaModalidades(){
    dados = DadosAplicacao.getDadosAplicacao();
    ArrayList<Modalidade> listaModalidades = dados.getModalidades();
    int numeroModalidades = listaModalidades.size();
    String[][] data = new String[numeroModalidades][5];
    String colunas[] = {"Nome", "Sexo", "Mínimos", "", ""};

    for (int i = 0; i < listaModalidades.size(); i++) {
      Modalidade modalidade = listaModalidades.get(i);
      String row[] = {modalidade.getNome(), modalidade.getSexo(), modalidade.getMinimo(), "editar", "eliminar"};
      data[i] = row;
    }

    tabelaModalidades = new JTable(data, colunas);
  }

  private void createUIComponents() {
    criarTabelaEventos();
    criarTabelaModalidades();
  }

  public static void main(String[] args) {
    new ListaEventosModalidades().setVisible(true);
  }
}

