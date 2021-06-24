package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Atleta {
  private String id;
  private String nome;
  private String pais;
  private String sexo;
  private String dataNascimento;
  private String contacto;

  public Atleta(String id, String nome, String pais, String sexo, String dataNascimento, String contacto) {
    this.id = id;
    this.nome = nome;
    this.pais = pais;
    this.sexo = sexo;
    this.dataNascimento = dataNascimento;
    this.contacto = contacto;
  }

  public Atleta() {
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

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public String getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getContacto() {
    return contacto;
  }

  public void setContacto(String contacto) {
    this.contacto = contacto;
  }

  public String getRecordePessoal(Modalidade modalidade){
    return null;
  }
}
