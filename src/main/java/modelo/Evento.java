package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Evento {
  private String id;
  private String nome;
  private String dataInicio;
  private String dataFim;
  private String local;
  private String pais;
  private ArrayList<Prova> provas;

  public Evento() {
    this.provas = new ArrayList<Prova>();
    this.id = UUID.randomUUID().toString();
  }

  public Evento(String id, String nome, String local, String pais, String dataInicio, String dataFim, ArrayList<Prova> provas) {
    this.id = id;
    this.nome = nome;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.local = local;
    this.pais = pais;
    this.provas = new ArrayList<>();
  }

  public void gerarPrograma(){
    DadosAplicacao dados = DadosAplicacao.getDadosAplicacao();
    int totalInscritos = 0;
    ArrayList<Inscricao> inscricoes = dados.getInscricoes();

    for (int i = 0; i < provas.size(); i++) {
      for (int j = 0; j < inscricoes.size(); j++) {
        Inscricao inscricao = inscricoes.get(j);
        if(inscricao.getIdProva().equals(provas.get(i).getId())){
          totalInscritos++;
        }
      }
      int numeroEtapas = (int) Math.ceil((totalInscritos - 8) / 4);
      int aux = 8;
      if (aux > totalInscritos){
        aux = totalInscritos;
      }

      if (totalInscritos > 0){
        Etapa etapa = new Etapa("final", aux, dataInicio, numeroEtapas + 1, i+10+":00");
        provas.get(i).adicionarEtapa(etapa);
      }
      for (int j = 0; j < numeroEtapas; j++) {
        if (j == 0){
          aux += 4;
          if (aux > totalInscritos){
            aux = totalInscritos;
          }
          Etapa etapa = new Etapa("semi-final", aux, dataInicio, numeroEtapas-j, i+10+":00");
          provas.get(i).adicionarEtapa(etapa);
          break;
        }
        if (j == 1){
          aux += 4;
          if (aux > totalInscritos){
            aux = totalInscritos;
          }
          Etapa etapa = new Etapa("quartos-final", aux, dataInicio, numeroEtapas-j, i+10+":00");
          provas.get(i).adicionarEtapa(etapa);
          break;
        }
        aux += 4;
        if (aux > totalInscritos){
          aux = totalInscritos;
        }
        Etapa etapa = new Etapa("Ronda " + (numeroEtapas-j), aux, dataInicio, numeroEtapas-j, i+10+":00");
        provas.get(i).adicionarEtapa(etapa);
      }
    }
  }

  public String getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(String dataInicio) {
    this.dataInicio = dataInicio;
  }

  public String getDataFim() {
    return dataFim;
  }

  public void setDataFim(String dataFim) {
    this.dataFim = dataFim;
  }

  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public ArrayList<Prova> getProvas() {
    return provas;
  }

  public Prova getProva(String idModalidade) {
    for (int i = 0; i < provas.size(); i++) {
      if (provas.get(i).getModalidade().getId().equals(idModalidade)){
        return provas.get(i);
      }
    }
    return null;
  }

  public Prova getProvaComId(String idProva){
    for (int i = 0; i < provas.size(); i++) {
      if (provas.get(i).getId().equals(idProva)){
        return provas.get(i);
      }
    }
    return null;
  }

  public void setProvas(ArrayList<Prova> provas) {
    this.provas = provas;
  }

  public void adicionarProva(Prova prova){
    provas.add(prova);
  }

  public void removerProva(String idProva){

  }

  public ArrayList<String> getPaisesMaisMedalhados(){
    return null;
  }
}
