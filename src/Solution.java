import java.io.*;

public class Solution {

    public int [] regles;
    public int fitness;

    public Solution() {
        int [] regles;
        int fitness;
    }

   public Solution(int [] regles){
        this.regles = regles;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int[] getRules() {
        return regles;
    }

    public void setRules(int[] regles) {
        this.regles = regles;
    }


}
