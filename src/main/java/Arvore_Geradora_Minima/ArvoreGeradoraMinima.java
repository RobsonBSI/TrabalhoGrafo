package Arvore_Geradora_Minima;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArvoreGeradoraMinima {

    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static List<String> transformarEmCircuitoEuleriano(List<Edge> agm, String[] cidades) {
        List<String> circuitoEuleriano = new ArrayList<>();
        Map<Integer, String> indiceParaCidade = new HashMap<>();

        // Mapeia os índices dos vértices para os nomes das cidades
        for (int i = 0; i < cidades.length; i++) {
            indiceParaCidade.put(i, cidades[i]);
        }

        // Inicializa o circuito euleriano a partir do primeiro vértice
        int startVertex = agm.get(0).from;
        int currentVertex = startVertex;

        // Itera sobre as arestas da árvore geradora mínima
        for (Edge edge : agm) {
            // Adiciona o vértice atual ao circuito
            circuitoEuleriano.add(indiceParaCidade.get(currentVertex));

            // Move para o próximo vértice na aresta
            currentVertex = (currentVertex == edge.from) ? edge.to : edge.from;
        }

        // Adiciona o vértice de início para formar um ciclo euleriano
        circuitoEuleriano.add(indiceParaCidade.get(startVertex));

        return circuitoEuleriano;
    }

    public static void main(String[] args) {
        String[] cidades = {"A", "B", "C", "D", "E"};
        List<Edge> agm = new ArrayList<>();
        agm.add(new Edge(0, 1, 10));
        agm.add(new Edge(1, 2, 12));
        agm.add(new Edge(2, 3, 6));
        agm.add(new Edge(3, 4, 8));

        List<String> circuitoEuleriano = transformarEmCircuitoEuleriano(agm, cidades);

        System.out.println("Circuito Euleriano:");
        for (String cidade : circuitoEuleriano) {
            System.out.print(cidade + " ");
        }
    }
}
