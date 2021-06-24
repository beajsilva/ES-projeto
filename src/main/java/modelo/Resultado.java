package modelo;

import java.util.ArrayList;

public class Resultado {
  private String posicaoFinal;
  private ArrayList<ResultadoEtapa> resultadoEtapas;

  public Resultado(String posicao, ArrayList<ResultadoEtapa> resultadoEtapas) {
    this.posicaoFinal = posicao;
    this.resultadoEtapas = resultadoEtapas;
  }

  public String getPosicao() {
    return posicaoFinal;
  }

  public void setPosicao(String posicao) {
    this.posicaoFinal = posicao;
  }

  public ArrayList<ResultadoEtapa> getResultadoEtapas() {
    return resultadoEtapas;
  }

  public void setResultadoEtapas(ArrayList<ResultadoEtapa> resultadoEtapas) {
    this.resultadoEtapas = resultadoEtapas;
  }

  public void adicionarResultadoEtapa(ResultadoEtapa resultadoEtapa){
    resultadoEtapas.add(resultadoEtapa);
  }
}
