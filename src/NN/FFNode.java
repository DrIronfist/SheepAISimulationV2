package NN;
public class FFNode extends Node{
    public FFNode(int ic) {
        super(ic);
    }
    @Override
    public double run(double[] input){
        double activationInput = NetworkMath.dotVectors(input, weights) + bias;
        return NetworkMath.sigmoid(activationInput);
    }
}