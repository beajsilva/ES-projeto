package modelo;

public class ResultadoEtapa {
  private String idEtapa;
  private String marcaAlcancada;

  public ResultadoEtapa(String idEtapa, String marcaAlcancada) {
    this.idEtapa = idEtapa;
    this.marcaAlcancada = marcaAlcancada;
  }

  public String getIdEtapa() {
    return idEtapa;
  }

  public void setIdEtapa(String idEtapa) {
    this.idEtapa = idEtapa;
  }

  public String getMarcaAlcancada() {
    return marcaAlcancada;
  }

  public void setMarcaAlcancada(String marcaAlcancada) {
    this.marcaAlcancada = marcaAlcancada;
  }
}
