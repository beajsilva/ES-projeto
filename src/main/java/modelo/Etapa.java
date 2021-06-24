package modelo;

import java.util.Date;
import java.util.UUID;

public class Etapa {
  private String id;
  private String data;
  private int diaCompeticao;
  private String horaCompeticao;
  private String nome;
  private int numeroAtletas;

  public Etapa(String nome, int numeroAtletas, String data, int diaCompeticao, String horaCompeticao) {
    this.id = UUID.randomUUID().toString();
    this.data = data;
    this.diaCompeticao = diaCompeticao;
    this.nome = nome;
    this.numeroAtletas = numeroAtletas;
    this.horaCompeticao = horaCompeticao;
  }

  public String getHoraCompeticao() {
    return horaCompeticao;
  }

  public void setHoraCompeticao(String horaCompeticao) {
    this.horaCompeticao = horaCompeticao;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public int getDiaCompeticao() {
    return diaCompeticao;
  }

  public void setDiaCompeticao(int diaCompeticao) {
    this.diaCompeticao = diaCompeticao;
  }

  public String getRonda() {
    return nome;
  }

  public void setRonda(String ronda) {
    this.nome = ronda;
  }

  public int getNumeroAtletas() {
    return numeroAtletas;
  }

  public void setNumeroAtletas(int numeroAtletas) {
    this.numeroAtletas = numeroAtletas;
  }

  public void registarProgresso(){

  }
}
