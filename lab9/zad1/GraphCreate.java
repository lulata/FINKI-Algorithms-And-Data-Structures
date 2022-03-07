package APS.lab9.zad1;

import java.util.Scanner;

class Graph {

    int num_nodes; // broj na jazli
    Character nodes[];    // informacija vo jazlite - moze i ne mora?
    int adjMat[][];  // matrica na sosednost


    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        nodes = new Character[num_nodes];
        for (int i = 0; i < num_nodes; ++i)
            nodes[i] = (char) (i + 'A');
        adjMat = new int[num_nodes][num_nodes];

        for (int i = 0; i < this.num_nodes; i++)
            for (int j = 0; j < this.num_nodes; j++)
                adjMat[i][j] = 0;
    }


    int adjacent(int x, int y) {  // proveruva dali ima vrska od jazelot so indeks x do jazelot so indeks y
        return (adjMat[x][y] != 0) ? 1 : 0;
    }

    void addEdge(int x, int y) {  // dodava vrska megu jazlite so indeksi x i y
        adjMat[x][y] = 1;
        adjMat[y][x] = 1;
    }


    void deleteEdge(int x, int y) {
        // ja brise vrskata megu jazlite so indeksi x i y
        adjMat[x][y] = 0;
        adjMat[y][x] = 0;
    }

    // Moze i ne mora?
    Character get_node_value(int x) {  // ja vraka informacijata vo jazelot so indeks x
        return nodes[x];
    }

    // Moze i ne mora?
    void set_node_value(int x, Character a) {  // ja postavuva informacijata vo jazelot so indeks na a
        nodes[x] = a;
    }


    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }


    void printMatrix() {
        for (int i = 0; i < adjMat.length; ++i) {
            for (int j = 0; j < adjMat[0].length; ++j)
                System.out.print(adjMat[i][j] + " ");
            System.out.println();
        }
    }
}

public class GraphCreate {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.next();
        int nodes = sc.nextInt();
        Graph g = new Graph(nodes);
        for (int i = 0; i < n; ++i) {
            String s = sc.nextLine();
            String[] parts = s.split(" ");

            if (parts[0].equals("ADDEDGE")) {
                g.addEdge(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            } else if (parts[0].equals("DELETEEDGE")) {
                g.deleteEdge(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            } else if (parts[0].equals("ADJACENT")) {
                System.out.println(g.adjacent(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
            } else if (parts[0].equals("PRINTMATRIX")) {
                g.printMatrix();
            } else if (parts[0].equals("PRINTNODE")) {
                System.out.println(g.get_node_value(Integer.parseInt(parts[1])));
            }
        }
    }
}
