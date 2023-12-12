package Vizinho_Mais_Proximo;

import java.util.ArrayList;
import java.util.List;

public class VizinhoMaisProximo {
    public static ResultadoVizinhoMaisProximo vizinhoMaisProximo(int[][] grafo, int pontoInicial) {
        int numPontos = grafo.length;
        boolean[] visitados = new boolean[numPontos];
        List<Integer> caminho = new ArrayList<>();
        int distanciaTotal = 0;

        // Inicializa o caminho com o ponto inicial
        caminho.add(pontoInicial);
        visitados[pontoInicial] = true;

        // Enquanto houver pontos não visitados
        while (caminho.size() < numPontos) {
            int pontoAtual = caminho.get(caminho.size() - 1);
            VizinhoMaisProximoResult resultado = encontrarVizinhoMaisProximo(grafo, pontoAtual, visitados);

            int proximoVizinho = resultado.proximoVizinho;
            distanciaTotal += resultado.distancia;

            // Adiciona o ponto mais próximo ao caminho
            caminho.add(proximoVizinho);
            visitados[proximoVizinho] = true;
        }

        // Completa o ciclo, voltando ao ponto inicial
        caminho.add(pontoInicial);
        distanciaTotal += grafo[caminho.get(caminho.size() - 2)][pontoInicial];

        return new ResultadoVizinhoMaisProximo(caminho, distanciaTotal);
    }

    private static VizinhoMaisProximoResult encontrarVizinhoMaisProximo(int[][] grafo, int pontoAtual, boolean[] visitados) {
        int proximoVizinho = -1;
        int menorDistancia = Integer.MAX_VALUE;

        for (int i = 0; i < grafo.length; i++) {
            if (!visitados[i] && grafo[pontoAtual][i] < menorDistancia) {
                proximoVizinho = i;
                menorDistancia = grafo[pontoAtual][i];
            }
        }

        return new VizinhoMaisProximoResult(proximoVizinho, menorDistancia);
    }
}
