package NN;
import java.lang.reflect.InvocationTargetException;

public class NeuralNetwork {
    public NetworkData data;
    public Layer[] layers;

    public NeuralNetwork(NetworkData dat) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        data = dat;
        layers = new Layer[dat.layerCount+1];
        int lastIc = dat.inputCount;
        for(int i = 0; i < dat.layerCount; i++) {
            layers[i] = new Layer(dat.nodeCount, lastIc, dat.nType);
            lastIc = dat.nodeCount;
        }
        layers[dat.layerCount] = new Layer(dat.outputCount, lastIc,dat.nType);
    }
    public double[] run(double[] input) {
        double[] lastInput = input;
        for(int i = 0; i < layers.length; i++) {
            lastInput = layers[i].run(lastInput);
        }
        return lastInput;
    }
    public void train(double[] input, double[] desired){
        double[] output = run(input);
        double cost = 0;
        for(int i = 0; i < output.length; i++){
            cost += Math.pow((desired[i] - output[i]),2);
        }
        double[] dCostOut = new double[output.length];
        for(int i = 0; i < dCostOut.length; i++){
            dCostOut[i] = 2*(output[i] - desired[i]);
        }
    }
}