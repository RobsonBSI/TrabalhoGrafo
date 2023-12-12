package Vizinho_Mais_Proximo;

import java.util.List;

public class ResultadoVizinhoMaisProximo {
    public List<String> caminho;
    public int distanciaTotal;

    public ResultadoVizinhoMaisProximo(List<String> caminho, int distanciaTotal) {
        this.caminho = caminho;
        this.distanciaTotal = distanciaTotal;
    }
}
