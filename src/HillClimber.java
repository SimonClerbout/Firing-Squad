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
                solution.setRules(rulesTest);
                solution.setFitness(testBetterFit);

                better = true;

            }
            repeat++;
        }

        System.out.println("Fitness HFCI : " + fitness);
        return solution;

    }


    public Solution ILS(int maxOpti, Automata automata){

        Initialization initialize = new Initialization();
        int [] rules = new int[_NBITER];
        initialize.init(rules);

        int fitness = automata.f(rules, _SIZEMAX);

        //premier "voisin"
        Solution firstResult = new Solution(rules);

        //deuxième voisin
        Solution secondResult = new Solution();

        //on execute un HCFI
        firstResult = hillClimberFirst(maxOpti, automata);

        int repeat = 0;
        int[] rulesTest = new int[_NBITER];

        do{

            //altération de 3 règles
            Random rand = new Random();
            for(int tmpi = 0; tmpi < 3; tmpi++){

                rulesTest[rand.nextInt(_NBITER)] = rand.nextInt(4);
                rulesTest = firstResult.getRules();

            }

            //succesion d'HCFI permettant de faire une recherche aléatoire
            secondResult = hillClimberFirst(2000000, automata);

            repeat++;

            // si la fitness actuelle est inférieur à la suivante, la fitness actuelle prend la valeur suivante
            if(fitness <= secondResult.getFitness()){

                fitness = secondResult.getFitness();
                initialize.init(rulesTest);

                firstResult.setFitness(fitness);

            }

        }while(repeat < maxOpti);// on boucle tant que le nombre de répétitions n'est pas dépassé

        System.out.println("Fitness ILS : " + fitness);
        return firstResult;


    }


    public Solution randomSearch(int maxOpti, Automata automata){

        Initialization initialize = new Initialization();
        int [] rules = new int[_NBITER];
        initialize.init(rules);

        int fitness = automata.f(rules, _SIZEMAX);

        Solution firstResult = new Solution(rules);

        int[] rulesTest = new int[_NBITER];

        int repeat = 0;

        while(repeat <= maxOpti){

            Random rand = new Random();
            for(int tmpi = 0; tmpi < 1; tmpi++){

                rulesTest[rand.nextInt(_NBITER)] = rand.nextInt(4);
                rulesTest = firstResult.getRules();

            }

            int testFit = automata.f(rulesTest, _SIZEMAX);

            firstResult.setRules(rulesTest);
            firstResult.setFitness(testFit);

            repeat++;

        }

        System.out.println("Fitness Random Search : " + fitness);
        return firstResult;

    }

}
