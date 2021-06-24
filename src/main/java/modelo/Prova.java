package modelo;

import java.util.ArrayList;
import java.util.UUID;

public class Prova {
  private String id;
  private String idEvento;
  private Modalidade modalidade;
  private ArrayList<Etapa> etapas;

  public Prova(String id, String idEvento, Modalidade modalidade) {
    this.id = id;
    this.idEvento = idEvento;
    this.modalidade = modalidade;
    this.etapas = new ArrayList<>();
  }

  public Prova(String idEvento, Modalidade modalidade) {
    this.id = UUID.randomUUID().toString();
    this.idEvento = idEvento;
    this.modalidade = modalidade;
    this.etapas = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public Modalidade getModalidade() {
    return modalidade;
  }

  public void setModalidade(Modalidade modalidade) {
    this.modalidade = modalidade;
  }

  public void adicionarEtapa(Etapa etapa){
    etapas.add(etapa);
  }

  @Override
  public String toString() {
    String ret = modalidade.getNome() + '-' + modalidade.getSexo().toUpperCase().charAt(0);
    return ret;
  }
}
