class BPlusNode {
    char type;
    Object[] keys;
    BPlusNode[] references;

    public BPlusNode(char type) {
        this.type = type;
        this.keys = new String[2]; // Máximo de 2 claves
        this.references = new BPlusNode[3]; // Máximo de 3 referencias
    }
}