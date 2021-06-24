package vista;

import modelo.Atleta;
import modelo.DadosAplicacao;
import modelo.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListaAtletas extends JFrame{
  private JPanel painelListaAtletas;
  private JPanel painelMenu;
  private JButton btnRecordes;
  private JButton btnMedalhados;
  private JButton btnAtletas;
  private JButton btnEventosModalidades;
  private JButton btnAdicionarAtleta;
  private JTable tabelaAtletas;
  private DadosAplicacao dados;

  public void criarTabela(){
    dados = DadosAplicacao.getDadosAplicacao();
    ArrayList<Atleta> listaAtletas = dados.getAtletas();
    int numeroAtletas = listaAtletas.size();
    String[][] data = new String[numeroAtletas][4];
    String colunas[] = {"Nome", "Nacionalidade", "Sexo", "Data nascimento", "Contacto"};

    for (int i = 0; i < listaAtletas.size(); i++) {
      Atleta atleta = listaAtletas.get(i);
      String row[] = {atleta.getNome(), atleta.getPais(), atleta.getSexo(), atleta.getDataNascimento(), atleta.getContacto()};
      data[i] = row;
    }

    tabelaAtletas = new JTable(data, colunas);
  }

  public ListaAtletas() {
    super("Lista de atletas");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(painelListaAtletas);
    pack();
    setVisible(true);

    btnEventosModalidades.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new ListaEventosModalidades();
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
    btnAdicionarAtleta.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new CriarAtleta();
      }
    });
    tabelaAtletas.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int row = tabelaAtletas.rowAtPoint(e.getPoint());
        int col = tabelaAtletas.columnAtPoint(e.getPoint());
        if (row >= 0 && col >= 0) {
          ArrayList<Atleta> atletas = dados.getAtletas();
          if(row < atletas.size()){
            String idAtleta = atletas.get(row).getId();
            setVisible(false);
            new InformacaoAtleta(idAtleta);
          }
        }
      }
    });
  }

  private void createUIComponents() {
    criarTabela();
  }
}
