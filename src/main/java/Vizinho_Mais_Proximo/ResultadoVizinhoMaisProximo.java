package Vizinho_Mais_Proximo;

import java.util.List;

public class ResultadoVizinhoMaisProximo {
    public List<Integer> caminho;
    public int distanciaTotal;

    public ResultadoVizinhoMaisProximo(List<Integer> caminho, int distanciaTotal) {
        this.caminho = caminho;
        this.distanciaTotal = distanciaTotal;
    }
}
