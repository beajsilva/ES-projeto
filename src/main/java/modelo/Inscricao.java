package modelo;

import java.util.UUID;

public class Inscricao {
  private String id;
  private String idProva;
  private String idAtleta;
  private String marcaAtleta;
  private Resultado resultado;

  public Inscricao(String id,String idProva, String idAtleta, String marcaAtleta, Resultado resultado) {
    this.id = id;
    this.idProva = idProva;
    this.idAtleta = idAtleta;
    this.marcaAtleta = marcaAtleta;
    this.resultado = resultado;
  }

  public Inscricao() {
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public String getIdProva() {
    return idProva;
  }

  public void setIdProva(String idProva) {
    this.idProva = idProva;
  }

  public String getIdAtleta() {
    return idAtleta;
  }

  public void setIdAtleta(String idAtleta) {
    this.idAtleta = idAtleta;
  }

  public String getMarcaAtleta() {
    return marcaAtleta;
  }

  public void setMarcaAtleta(String marcaAtleta) {
    this.marcaAtleta = marcaAtleta;
  }

  public Resultado getResultado() {
    return resultado;
  }

  public void setResultado(Resultado resultado) {
    this.resultado = resultado;
  }
}
