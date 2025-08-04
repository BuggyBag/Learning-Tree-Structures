import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //prueba con 3 ejemplos predeterminados
        
        int[] exampleKeys = {9, 15, 18, 20, 25};
        int[] exampleFrequencies = {4, 1, 8, 3, 9};
        double[] test = new double[exampleKeys.length];
        int tot = 0;
        int numberOfElements = exampleKeys.length;

        for (int i = 0; i < exampleKeys.length; i++) {
            tot += exampleFrequencies[i];
        }
      
        for (int i = 0; i < exampleKeys.length; i++) {
            test[i] = ((double) exampleFrequencies[i]) / tot;
        }

        Node example_root = constructOptimalBST(exampleKeys, test, 0, numberOfElements-1);
        System.out.print("\nArbol:\n");
        printBST(example_root);

        exampleKeys = new int[]{7, 15, 3, 18, 11};
        exampleFrequencies = new int[]{4,8,1,9,6};
        tot = 0;

        for (int i = 0; i < exampleKeys.length; i++) {
            tot += exampleFrequencies[i];
        }
        
        for (int i = 0; i < exampleKeys.length; i++) {
            test[i] = ((double) exampleFrequencies[i]) / tot;
        }
        
        example_root = constructOptimalBST(exampleKeys, test, 0, numberOfElements-1);
        System.out.print("\nArbol:\n");
        printBST(example_root);
  
        exampleKeys = new int[]{16, 29, 38, 8, 45};
        exampleFrequencies = new int[]{1,4,4,2,3};  
        tot = 0;

        for (int i = 0; i < exampleKeys.length; i++) {
            tot += exampleFrequencies[i];
        }

        for (int i = 0; i < exampleKeys.length; i++) {
            test[i] = ((double) exampleFrequencies[i]) / tot;
        }
        
        example_root = constructOptimalBST(exampleKeys, test, 0, numberOfElements-1);
        System.out.print("\nArbol:\n");
        printBST(example_root);

        // aquí el usuario podrá probar con sus propios valores
       
        Scanner scan = new Scanner(System.in);

        System.out.println("\n Inicio de código ");
        System.out.print("Digite la cantidad de datos a introducir: ");
        int n = Integer.parseInt(scan.nextLine());

        int[] keys = new int[n];
        int[] freq = new int[n];
        double[] p = new double[n];
        int total = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("\nDigite el dato #" + i + ": ");
            keys[i] = Integer.parseInt(scan.nextLine());
            System.out.print("Digite la frecuencia del dato #" + i + ": ");
            freq[i] = Integer.parseInt(scan.nextLine());
            total += freq[i];
        }

        for (int i = 0; i < n; i++) {
            p[i] = ((double) freq[i]) / total;  // Se cambió a double y se simplificó
        }

        Node root = constructOptimalBST(keys, p, 0, n - 1);

        // Imprimir el árbol óptimo construido
        System.out.print("\nArbol:\n");
        printBST(root);

        double totalCost = calculateCost(root, 1);
        System.out.println("\n\nEl costo total del arbol optimo es: " + (totalCost * total));

        scan.close();
    }

    // Función para construir un árbol óptimo BST para un conjunto dado de claves y probabilidades
    static Node constructOptimalBST(int[] keys, double[] p, int i, int j) {
        if (i > j) {
            return null;
        }

        // Encontrar la raíz con el costo esperado mínimo
        int minRoot = findMinRoot(keys, p, i, j);

        Node root = new Node(keys[minRoot], p[minRoot]);
        root.left = constructOptimalBST(keys, p, i, minRoot - 1);
        root.right = constructOptimalBST(keys, p, minRoot + 1, j);

        return root;
    }

    // Función para encontrar la raíz con el costo esperado mínimo
    static int findMinRoot(int[] keys, double[] p, int i, int j) {
        int minRoot = -1;
        double minCost = Double.MAX_VALUE;
        double cost;

        // Calcular la suma de probabilidades en el rango (i, j)
        double sumProbabilities = 0;
        for (int k = i; k <= j; k++) {
            sumProbabilities += p[k];
        }

        for (int r = i; r <= j; r++) {
            cost = expectedCost(keys, p, i, j, r);

            // Costo esperado considerando el rango (i, j)
            double expectedCost = cost + (r > i ? sumProbabilities - p[r] : sumProbabilities);

            if (expectedCost < minCost) {
                minCost = expectedCost;
                minRoot = r;
            }
        }

        return minRoot;
    }


    // Función para calcular el costo esperado para una raíz dada
    static double expectedCost(int[] keys, double[] p, int i, int j, int r) {
        double cost = 0;
        for (int k = i; k <= j; k++) {
          cost += (Math.abs(k - r) + 1) * p[k];
        }
        return cost;
    }

    static void printBST(Node root) {
        if (root != null) {
            printBSTUtil(root, 0);
        }
    }

    static void printBSTUtil(Node root, int level) {
        if (root == null) {
            return;
        }

        // Recorre el subárbol derecho primero
        printBSTUtil(root.right, level + 1);

        // Imprime los espacios según el nivel
        for (int i = 0; i < level; i++) {
            System.out.print('\t');
        }

        // Imprime el nodo actual
        System.out.println(root.data + " (" + root.weight + ")");

        // Recorre el subárbol izquierdo
        printBSTUtil(root.left, level + 1);
    }

    static double calculateCost(Node root, int level) {
        if (root == null) {
            return 0;
        }
        // El costo de este nodo es su nivel multiplicado por su valor
        double cost = level * root.weight;
        // Suma recursivamente los costos de los hijos izquierdo y derecho
        cost += calculateCost(root.left, level + 1);
        cost += calculateCost(root.right, level + 1);
        return cost;
    }
}