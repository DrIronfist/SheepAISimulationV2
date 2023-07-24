package NN;
import java.lang.Math;

public class NetworkMath {
    public static double dotVectors(double[] input, double[] weights) {
        double sum = 0;
        for(int i = 0; i < input.length; i++) {
            sum += input[i] * weights[i];
        }
        return sum;
    }
    public static double sigmoid(double input) {
        return 1/(1+Math.pow(Math.E, -input));
    }
    public static double dCostOut(double output, double cost){
        return 2*(output - cost);
    }
    public static double dOutputInput(double input){
        return sigmoid(input)*(1-sigmoid(input));
    }
    public static double dInputWeight(double output){
        return output;
    }
    public static double dCostWeight(double input, double output, double cost){
        return dInputWeight(output)*dOutputInput(input)*dCostOut(output, cost);
    }
    public static double dCostBias(double input, double output, double cost){
        return dOutputInput(input)*dCostOut(output, cost);
    }
}