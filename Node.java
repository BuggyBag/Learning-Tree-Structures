class Node {
    int data;
    double weight;
    Node left, right;

    public Node(int data, double weight) {
        this.data = data;
        this.weight = weight;
        left = right = null;
    }
}