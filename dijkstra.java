import java.util.Arrays;

public class DijkstraAlgorithm {

    private static final int INFINITY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // Test Case 1
        int[][] graph1 = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        int startNode1 = 0;
        runDijkstra(graph1, startNode1);

        // Test Case 2
        int[][] graph2 = {
                {0, 3, 0, 0, 0, 0, 0, 4},
                {3, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 8, 0, 0, 0, 0},
                {0, 0, 8, 0, 2, 0, 0, 0},
                {0, 0, 0, 2, 0, 6, 0, 0},
                {0, 0, 0, 0, 6, 0, 7, 0},
                {0, 0, 0, 0, 0, 7, 0, 9},
                {4, 0, 0, 0, 0, 0, 9, 0}
        };
        int startNode2 = 2;
        runDijkstra(graph2, startNode2);

        // Test Case 3
        int[][] graph3 = {
                {0, 2, 4, 0, 0, 0},
                {2, 0, 1, 7, 0, 0},
                {4, 1, 0, 0, 3, 0},
                {0, 7, 0, 0, 0, 1},
                {0, 0, 3, 0, 0, 5},
                {0, 0, 0, 1, 5, 0}
        };
        int startNode3 = 0;
        runDijkstra(graph3, startNode3);
    }

    /**
     * Run Dijkstra's algorithm on the given graph starting from the specified node.
     * @param graph The graph represented as an adjacency matrix.
     * @param startNode The starting node for the algorithm.
     */
    private static void runDijkstra(int[][] graph, int startNode) {
        System.out.println("Graph:");
        printGraph(graph);

        System.out.println("\nShortest distances from Node " + startNode + ":");
        dijkstra(graph, startNode);
        System.out.println("-------------------------");
    }

    /**
     * Implementation of Dijkstra's algorithm to find the shortest paths from a source node to all other nodes in a graph.
     * @param graph The adjacency matrix representing the graph.
     * @param startNode The starting node for the algorithm.
     */
    private static void dijkstra(int[][] graph, int startNode) {
        int vertices = graph.length;
        int[] dist = new int[vertices];
        boolean[] visited = new boolean[vertices];

        Arrays.fill(dist, INFINITY);
        dist[startNode] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != INFINITY &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist);
    }

    /**
     * Helper function to find the index of the vertex with the minimum distance value.
     * @param dist The array of distance values.
     * @param visited The array indicating whether a node has been visited or not.
     * @return The index of the minimum distance vertex.
     */
    private static int minDistance(int[] dist, boolean[] visited) {
        int min = INFINITY;
        int minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    /**
     * Helper function to print the solution, i.e., the shortest distances from the source vertex.
     * @param dist The array of shortest distances.
     */
    private static void printSolution(int[] dist) {
        System.out.println("Shortest distances:");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("To " + i + ": " + dist[i]);
        }
    }

    /**
     * Helper function to print the adjacency matrix representation of the graph.
     * @param graph The graph represented as an adjacency matrix.
     */
    private static void printGraph(int[][] graph) {
        for (int[] row : graph) {
            System.out.println(Arrays.toString(row));
        }
    }
}

