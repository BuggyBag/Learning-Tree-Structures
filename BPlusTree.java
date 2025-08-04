public class BPlusTree {

    private BPlusNode root;

    public BPlusTree(int n) {
        root = new BPlusNode();
        root.keys = new Object[n-1];
        root.references = new Object[n];
    }

    public void insert(int key) {
        BPlusNode leafNode = findLeafNode(root, key);
        insertIntoNode(leafNode, key, value);
        if (leafNode.keys.length == leafNode.references.length - 1) {
          BPlusNode newLeafNode = new BPlusNode(node.type);

          // Mover la mitad de las claves y referencias al nuevo nodo
          int splitPoint = node.keys.length / 2;
          int newKeysLength = node.keys.length - splitPoint;

          // Mover las claves y referencias al nuevo nodo
          System.arraycopy(node.keys, splitPoint, newLeafNode.keys, 0, newKeysLength);
          System.arraycopy(node.references, splitPoint, newLeafNode.references, 0, newKeysLength + 1);

          // Actualizar el número de claves en cada nodo
          node.keys = Arrays.copyOf(node.keys, splitPoint); // Conservar la primera mitad en el nodo original
          node.references = Arrays.copyOf(node.references, splitPoint + 1); // Conservar la primera mitad más un puntero en el nodo original

          // Actualizar los punteros entre nodos hoja si es necesario
          newLeafNode.next = node.next;
          node.next = newLeafNode;
        }
    }

    public BPlusNode findLeafNode(BPlusNode node, int key) {
      // Si el nodo actual es hoja, devolverlo (caso base de la recursión)
      if (node.type == 'l') {
          return node;
      }

      // Encontrar la posición del intervalo adecuado para la clave
      int index;

      Object[] keys = node.keys;

      // Compare the key value with the keys in the node to find the appropriate interval
      if (key < (int) keys[0]) {
          index = 0;  // Values less than the first key
      } else if (key >= (int) keys[0] && key < (int) keys[1]) {
          index = 1;  // Values greater than or equal to the first key and less than the second key
      } else {
          index = 2;  // Values greater than or equal to the second key
      }
      
      // Llamar recursivamente a la función para el siguiente nodo
      return findLeafNode((BPlusNode) node.references[index], key);
  }


    public void delete(int key) {
      BPlusNode leafNode = findLeafNode(root, key);
      //deleteFromNode(leafNode, key);
      if (leafNode.keys.length < (leafNode.references.length - 1) / 2) {
          // Rebalanceo o combinación de nodos si es necesario
      }
  }
}
