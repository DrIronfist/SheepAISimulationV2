import java.lang.reflect.InvocationTargetException;

public class Layer {
    public Node[] nodes;
    public Layer(int nc, int ic, NetworkType nType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        nodes = new Node[nc];
        Class<?extends Node> nodeType;
        switch(nType){
            case FEEDFORWARD:
                nodeType = FFNode.class;
                break;
            case PERCEPTRON:
                nodeType = FFNode.class;
                break;
            default:
                nodeType = FFNode.class;
                break;
        }
        for(int i = 0; i < nc; i++) {
            nodes[i] = nodeType.getConstructor(int.class).newInstance(ic);
        }
    }
    public double[] run(double[] input) {
        double[] output = new double[nodes.length];
        for(int i = 0; i < output.length; i++) {
            output[i] = nodes[i].run(input);
        }
        return output;
    }

}