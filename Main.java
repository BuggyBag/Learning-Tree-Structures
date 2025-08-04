public class Main {
    public static void main(String[] args) {
        BPlusTree bPlusTree = new BPlusTree(4);

        // Operaciones de inserción, búsqueda y eliminación
        bPlusTree.insert(5, "Valor 1");
        bPlusTree.insert(8, "Valor 2");
        bPlusTree.insert(3, "Valor 3");

        Object result = bPlusTree.search(8);
        System.out.println("Resultado de la búsqueda: " + result);

        //bPlusTree.delete(5);
    }
}
