package Arvore_Geradora_Minima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArvoreGeradoraMinima {

    public static ResultadoCaixeiroViajanteAGM caixeiroViajanteAGM(int[][] grafo) {
        int numPontos = grafo.length;
        List<Aresta> arestas = gerarListaArestas(grafo);
        Collections.sort(arestas); // Ordena as arestas pelo peso

        UnionFind unionFind = new UnionFind(numPontos);
        List<Aresta> mstArestas = new ArrayList<>();

        for (Aresta aresta : arestas) {
            if (unionFind.union(aresta.origem, aresta.destino)) {
                mstArestas.add(aresta);
            }
        }

        List<Integer> caminho = percorrerAGM(mstArestas);
        int distanciaTotal = calcularDistanciaTotal(caminho, grafo);

        return new ResultadoCaixeiroViajanteAGM(caminho, distanciaTotal);
    }

    private static List<Aresta> gerarListaArestas(int[][] grafo) {
        List<Aresta> arestas = new ArrayList<>();
        int numPontos = grafo.length;

        for (int i = 0; i < numPontos; i++) {
            for (int j = i + 1; j < numPontos; j++) {
                arestas.add(new Aresta(i, j, grafo[i][j]));
            }
        }

        return arestas;
    }

    private static List<Integer> percorrerAGM(List<Aresta> mstArestas) {
        List<Integer> caminho = new ArrayList<>();
        boolean[] visitados = new boolean[mstArestas.size() + 1];

        for (Aresta aresta : mstArestas) {
            if (!visitados[aresta.origem]) {
                caminho.add(aresta.origem);
                visitados[aresta.origem] = true;
            }
            if (!visitados[aresta.destino]) {
                caminho.add(aresta.destino);
                visitados[aresta.destino] = true;
            }
        }

        // Adiciona a última cidade para fechar o ciclo
        caminho.add(mstArestas.get(0).origem);

        return caminho;
    }

    private static int calcularDistanciaTotal(List<Integer> caminho, int[][] grafo) {
        int distanciaTotal = 0;

        for (int i = 0; i < caminho.size() - 1; i++) {
            int pontoAtual = caminho.get(i);
            int proximoPonto = caminho.get(i + 1);
            distanciaTotal += grafo[pontoAtual][proximoPonto];
        }

        // Adiciona a última aresta para fechar o ciclo
        distanciaTotal += grafo[caminho.get(caminho.size() - 1)][caminho.get(0)];

        return distanciaTotal;
    }

    public static void main(String[] args) {
        int[][] grafo = {
                {0, 2, 9, 10},
                {2, 0, 6, 4},
                {9, 6, 0, 7},
                {10, 4, 7, 0}
        };

        ResultadoCaixeiroViajanteAGM resultado = caixeiroViajanteAGM(grafo);

        System.out.println("Caminho encontrado: " + resultado.caminho);
        System.out.println("Distância total: " + resultado.distanciaTotal);
    }
}

class Aresta implements Comparable<Aresta> {
    int origem;
    int destino;
    int peso;

    public Aresta(int origem, int destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public int compareTo(Aresta outraAresta) {
        return Integer.compare(this.peso, outraAresta.peso);
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
            rank[rootY]++;
        }

        return true;
    }
}

class ResultadoCaixeiroViajanteAGM {
    List<Integer> caminho;
    int distanciaTotal;

    public ResultadoCaixeiroViajanteAGM(List<Integer> caminho, int distanciaTotal) {
        this.caminho = caminho;
        this.distanciaTotal = distanciaTotal;
    }
}