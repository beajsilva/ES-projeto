package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DadosAplicacao {

  private ArrayList<Evento> eventos;
  private ArrayList<Atleta> atletas;
  private ArrayList<Modalidade> modalidades;
  private ArrayList<Inscricao> inscricoes;

  private static final DadosAplicacao instance = new DadosAplicacao();

  public static DadosAplicacao getDadosAplicacao(){
    return instance;
  }

  private DadosAplicacao() {
    eventos = new ArrayList<>();
    modalidades = new ArrayList<>();
    atletas = new ArrayList<>();
    inscricoes = new ArrayList<>();
    importar();
  }

  public ArrayList<Evento> getEventos() {
    return eventos;
  }

  private void importarEventos() {
    try {
      File myObj = new File("eventos.txt");
      if(myObj.exists()) {
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          if(data.trim().length() == 0){
            continue;
          }
          Evento newEvento = stringFromFileToEvento(data);
          eventos.add(newEvento);
        }
        System.out.println("Sucessfully uploaded " + eventos.size() + " events.");
        myReader.close();
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void importarModalidades() {
    try {
      File myObj = new File("modalidades.txt");
      if(myObj.exists()) {
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          if(data.trim().length() == 0){
            continue;
          }
          Modalidade newModalidade = stringFromFileToModalidade(data);
          modalidades.add(newModalidade);

        }
        System.out.println("Sucessfully uploaded " + modalidades.size() + " modalidades.");
        myReader.close();
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void importarAtletas() {
    try {
      File myObj = new File("atletas.txt");
      if(myObj.exists()) {
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          if(data.trim().length() == 0){
            continue;
          }
          Atleta newAtleta = stringFromFileToAtleta(data);
          atletas.add(newAtleta);
        }
        System.out.println("Sucessfully uploaded " + atletas.size() + " atletas.");
        myReader.close();
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void exportarEvento(Evento evento, boolean newEvento) {

    if(newEvento) {
      try {
        FileWriter myWriter = new FileWriter("eventos.txt", true);
        myWriter.write(eventoToStringForFile(evento));
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }else{
      try {
        Path path = Paths.get("eventos.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (int i = 0; i < lines.size(); i++) {
          if (getIdFromFile(lines.get(i).toString()).equals(evento.getId())) {
            lines.set(i, eventoToStringForFile(evento));
            System.out.println("Successfully replace line in the file.");
            break;
          }
        }
        Files.write(path, lines, StandardCharsets.UTF_8);

      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
  }

  public void exportarModalidade(Modalidade modalidade, boolean newModalidade){
    if(newModalidade) {
      try {
        FileWriter myWriter = new FileWriter("modalidades.txt", true);
        myWriter.write(modalidadeToStringForFile(modalidade));
        myWriter.close();
        System.out.println("Successfully wrote modalidade to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }else{
      try {
        Path path = Paths.get("modalidades.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (int i = 0; i < lines.size(); i++) {
          if (getIdFromFile(lines.get(i).toString()).equals(modalidade.getId())) {
            lines.set(i, modalidadeToStringForFile(modalidade));
            System.out.println("Successfully replace line in the file.");
            break;
          }
        }
        Files.write(path, lines, StandardCharsets.UTF_8);

      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
  }

  public void exportarAtleta(Atleta atleta, boolean newAtleta){
    if(newAtleta) {
      try {
        FileWriter myWriter = new FileWriter("atletas.txt", true);
        System.out.println(atletaToStringForFile(atleta));
        myWriter.write(atletaToStringForFile(atleta));
        myWriter.close();
        System.out.println("Successfully wrote atleta to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }else{
      try {
        Path path = Paths.get("atletas.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (int i = 0; i < lines.size(); i++) {
          if (getIdFromFile(lines.get(i).toString()).equals(atleta.getId())) {
            lines.set(i, atletaToStringForFile(atleta));
            System.out.println("Successfully replace line in the file.");
            break;
          }
        }
        Files.write(path, lines, StandardCharsets.UTF_8);

      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }

    }
  }

  public void importar() {
    importarEventos();
    importarModalidades();
    importarAtletas();
  }

  private String eventoToStringForFile(Evento evento){
    /*
    String provas = "";
    for (int i = 0; i < evento.getProvas().size(); i++) {
      //provas += evento.getProvas().get(i) + '.';
    }
    */
    String ret = "";
    ret += evento.getId();
    ret += ',' + evento.getNome();
    ret += ',' + evento.getLocal();
    ret += ',' + evento.getPais();
    ret += ',' + evento.getDataInicio();
    ret += ',' + evento.getDataFim();
    //ret += ',' + provas;
    ret += ";\n";

    return ret;
  }

  private String modalidadeToStringForFile(Modalidade modalidade){
    String ret = "";
    ret += modalidade.getId();
    ret += ',' + modalidade.getNome();
    ret += ',' + modalidade.getMinimo();
    ret += ',' + modalidade.getSexo();
    ret += ";\n";
    return ret;
  }

  private String atletaToStringForFile(Atleta atleta){
    String ret = "";
    ret += atleta.getId();
    ret += ',' + atleta.getNome();
    ret += ',' + atleta.getPais();
    ret += ',' + atleta.getSexo();
    ret += ',' + atleta.getDataNascimento();
    ret += ',' + atleta.getContacto();
    ret += ";\n";
    return ret;
  }

  private Evento stringFromFileToEvento(String stringFromFile){
    String[] splitted = stringFromFile.split(",");
    if(splitted.length >= 7){
      splitted[6].split(".");
      //lidar com provas
      //ArrayList<Prova> provasList = new ArrayList<>(Arrays.asList(provas));
    }
    splitted[splitted.length-1] = splitted[splitted.length-1].split(";")[0];
    Evento newEvento = new Evento(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4], splitted[5], null);

    return newEvento;
  }

  private Modalidade stringFromFileToModalidade(String stringFromFile){
    String[] splitted = stringFromFile.split(",");
    splitted[splitted.length-1] = splitted[splitted.length-1].split(";")[0];
    Modalidade newModalidade = new Modalidade(splitted[0], splitted[1], splitted[2], splitted[3]);
    return newModalidade;
  }

  private Atleta stringFromFileToAtleta(String stringFromFile){
    String[] splitted = stringFromFile.split(",");
    splitted[splitted.length-1] = splitted[splitted.length-1].split(";")[0];
    Atleta newAtleta = new Atleta(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4], splitted[5]);
    return newAtleta;
  }

  private String getIdFromFile(String stringFromFile){
    return stringFromFile.split(",")[0].toString();
  }

  public ArrayList<Atleta> getAtletas() {
    return atletas;
  }

  public ArrayList<Modalidade> getModalidades() {
    return modalidades;
  }

  public void adicionarEvento(Evento evento) {
    eventos.add(evento);
    exportarEvento(evento, true);
  }

  public void eliminarEvento(String idEvento) {
    Evento evento = getEvento(idEvento);
    eventos.remove(evento);
  }

  public Evento getEvento(String idEvento){
    for (int i = 0; i < eventos.size(); i++) {
      if (eventos.get(i).getId() == idEvento){
        return eventos.get(i);
      }
    }
    return null;
  }

  public void adicionarModalidade(Modalidade modalidade) {
    modalidades.add(modalidade);
    exportarModalidade(modalidade, true);
  }

  public void eliminarModalidade(String idModalidade) {
    Modalidade modalidade = getModalidade(idModalidade);
    modalidades.remove(modalidade);
  }

  public Modalidade getModalidade(String idModalidade) {
    for (int i = 0; i < modalidades.size(); i++) {
      if (modalidades.get(i).getId() == idModalidade){
        return modalidades.get(i);
      }
    }
    return null;
  }

  public Modalidade getModalidade(String nome, String sexo) {
    for (int i = 0; i < modalidades.size(); i++) {
      if (modalidades.get(i).getNome().toLowerCase().equals(nome.toLowerCase()) && modalidades.get(i).getSexo().toLowerCase().equals(sexo.toLowerCase())){
        return modalidades.get(i);
      }
    }
    return null;
  }

  public void adicionarAtleta(Atleta atleta) {
    atletas.add(atleta);
    exportarAtleta(atleta, true);
  }

  public Atleta getAtleta(String idAtleta) {
    for (int i = 0; i < atletas.size(); i++) {
      if (atletas.get(i).getId() == idAtleta){
        return atletas.get(i);
      }
    }
    return null;
  }

  public String getIdAtleta(String nome) {
    for (int i = 0; i < atletas.size(); i++) {
      if (atletas.get(i).getNome() == nome){
        return atletas.get(i).getId();
      }
    }
    return null;
  }

  public ArrayList<Inscricao> getInscricoes() {
    return inscricoes;
  }

  public void adicionarInscricao(Inscricao inscricao){
    inscricoes.add(inscricao);
  }

  public void eliminarInscricao(String idAtleta, String idProva){
    Inscricao inscricao = getInscricao(idAtleta, idProva);
    inscricoes.remove(inscricao);
  }

  public Inscricao getInscricao(String idAtleta, String idProva){
    for (int i = 0; i < inscricoes.size(); i++) {
      if (inscricoes.get(i).getIdAtleta() == idAtleta && inscricoes.get(i).getIdProva() == idProva){
        return inscricoes.get(i);
      }
    }
    return null;
  }

  public Evento getEventoComProva(String idProva){
    for (int i = 0; i < eventos.size(); i++) {
      Evento evento = eventos.get(i);
      for (int j = 0; j < evento.getProvas().size(); j++) {
        Prova prova = evento.getProvas().get(j);
        if (prova.getId().equals(idProva)){
          return evento;
        }
      }
    }
    return null;
  }
}