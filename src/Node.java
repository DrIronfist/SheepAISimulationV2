public abstract class Node {
    public double weights[];
    public double bias;
    public Node(int ic) {
        weights = new double[ic];
        for(int i = 0; i < ic; i++) {
            weights[i] = Math.random();
        }
        bias = Math.random();
    }
    public abstract double run(double[] input);
}