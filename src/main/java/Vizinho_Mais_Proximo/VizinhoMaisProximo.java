package Vizinho_Mais_Proximo;

import java.util.ArrayList;
import java.util.List;

public class VizinhoMaisProximo {
    public static ResultadoVizinhoMaisProximo vizinhoMaisProximo(int[][] grafo, String[] nomesCidades, String cidadeInicial) {
        int numCidades = grafo.length;
        boolean[] visitados = new boolean[numCidades];
        List<String> caminho = new ArrayList<>();
        int distanciaTotal = 0;

        // Encontra o índice correspondente à cidade inicial
        int indiceCidadeInicial = encontrarIndiceCidade(cidadeInicial, nomesCidades);

        // Inicializa o caminho com a cidade inicial
        caminho.add(cidadeInicial);
        visitados[indiceCidadeInicial] = true;

        // Enquanto houver cidades não visitadas
        while (caminho.size() < numCidades) {
            String cidadeAtual = caminho.get(caminho.size() - 1);
            VizinhoMaisProximoResult resultado = encontrarVizinhoMaisProximo(grafo, encontrarIndiceCidade(cidadeAtual, nomesCidades), visitados);

            String proximoVizinho = nomesCidades[resultado.proximoVizinho];
            distanciaTotal += resultado.distancia;

            // Adiciona a cidade mais próxima ao caminho
            caminho.add(proximoVizinho);
            visitados[resultado.proximoVizinho] = true;
        }

        // Completa o ciclo, voltando à cidade inicial
        caminho.add(cidadeInicial);
        distanciaTotal += grafo[encontrarIndiceCidade(caminho.get(caminho.size() - 2), nomesCidades)][indiceCidadeInicial];

        return new ResultadoVizinhoMaisProximo(caminho, distanciaTotal);
    }

    private static VizinhoMaisProximoResult encontrarVizinhoMaisProximo(int[][] grafo, int indiceCidadeAtual, boolean[] visitados) {
        int proximoVizinho = -1;
        int menorDistancia = Integer.MAX_VALUE;

        for (int i = 0; i < grafo.length; i++) {
            if (!visitados[i] && grafo[indiceCidadeAtual][i] < menorDistancia) {
                proximoVizinho = i;
                menorDistancia = grafo[indiceCidadeAtual][i];
            }
        }

        return new VizinhoMaisProximoResult(proximoVizinho, menorDistancia);
    }

    private static int encontrarIndiceCidade(String cidade, String[] nomesCidades) {
        for (int i = 0; i < nomesCidades.length; i++) {
            if (nomesCidades[i].equals(cidade)) {
                return i;
            }
        }
        return -1; // Retorna -1 se não encontrar
    }
}
