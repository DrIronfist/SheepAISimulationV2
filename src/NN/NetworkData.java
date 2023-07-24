package NN;
public class NetworkData {

    public int inputCount;
    public int outputCount;
    public int layerCount;
    public int nodeCount;
    public NetworkType nType;
    public NetworkData(int ic, int oc, int lc, int nc, NetworkType ntype) {
        inputCount = ic;
        outputCount = oc;
        layerCount = lc;
        nodeCount = nc;
        nType = ntype;
    }

}