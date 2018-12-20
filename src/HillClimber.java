import java.util.Random;

public class HillClimber {

    private static final int _NBITER = 216;
    private static final int _SIZEMAX = 20;

    public Solution hillClimberFirst(int maxOpti, Automata automata){

        //initialisation du "tableau"
        Initialization initialize = new Initialization();
        int [] rules = new int[_NBITER];
        initialize.init(rules);

        int fitness = automata.f(rules, _SIZEMAX);

        System.out.println("Fitness : " + fitness);

        Solution solution = new Solution(rules);

        boolean better = false;
        int repeat = 0;

        //On recherche le premier meilleur voisin tant que le nb max d'itérations n'est pas atteint
        while (better != true && repeat < maxOpti) {
            //on stocke le "voisin" courant
            int[] rulesTest = new int[_NBITER];
            int tmpi;
            for (tmpi = 0; tmpi < _NBITER; tmpi++) {
                rulesTest[tmpi] = rules[tmpi];
            }

            //on change une règle choisie aléatoirement
            int ruleToChange = (int) (Math.random() * 88);
            int valToChange = (int) (Math.random() * 4);
            int insertNewRule = initialize.tabIndice[ruleToChange];

            initialize.setRule(rulesTest, insertNewRule / 36, (insertNewRule / 6) % 6, insertNewRule % 6, valToChange);

            //on évalue la règle générée aléatoirement
            int testBetterFit = automata.f(rulesTest, _SIZEMAX);

            //on compare si la fitness règle générée aléatoirement est meilleur que la 1ère
            if (testBetterFit >= fitness) {

                //on remplace la règle et la fitness
                solution.setRegles(rulesTest);
                solution.setFitness(testBetterFit);

                better = true;

            }
            repeat++;
        }

        return solution;

    }


    



}
