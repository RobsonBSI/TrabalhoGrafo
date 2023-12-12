import Vizinho_Mais_Proximo.ResultadoVizinhoMaisProximo;
import Vizinho_Mais_Proximo.VizinhoMaisProximo;

public class Grafo {
    public static void main(String[] args) {
        int[][] grafo = {
                {0, 2, 9, 10},
                {2, 0, 6, 4},
                {9, 6, 0, 7},
                {4, 8, 7, 0}
        };
        VizinhoMaisProximo vizinho= new VizinhoMaisProximo();
        String[] nomesCidades = {"A", "B", "C", "D"};
        String cidadeInicial = "A";
        int pontoInicial = 0;
        ResultadoVizinhoMaisProximo resultado = vizinho.vizinhoMaisProximo(grafo, nomesCidades, cidadeInicial);

        System.out.println("Caminho encontrado: " + resultado.caminho);
        System.out.println("Distância total: " + resultado.distanciaTotal);

    }
}