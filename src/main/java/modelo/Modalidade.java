package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Modalidade {
  private String id;
  private String nome;
  private String minimo;
  private String sexo;

  public Modalidade(String id, String nome, String minimo, String sexo) {
    this.id = id;
    this.nome = nome;
    this.minimo = minimo;
    this.sexo = sexo;
  }

  public Modalidade() {
    this.id = UUID.randomUUID().toString();
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

  public String getMinimo() {
    return minimo;
  }

  public void setMinimo(String minimo) {
    this.minimo = minimo;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }
}
