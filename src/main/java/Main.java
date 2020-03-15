public class Main {
    public static void main(final String[] args){

        menu();

        final int swarmSize = 100;

        double minValue = -500, maxValue = 500;

        Swarm swarm = new Swarm(swarmSize, minValue, maxValue);
        int numOfFormula = 0;
        calculateSwarmAlgorithm(swarm, numOfFormula);

        minValue = -1; maxValue = 0;
        Swarm swarm2 = new Swarm(swarmSize, minValue, maxValue);
        numOfFormula = 1;
        calculateSwarmAlgorithm(swarm2, numOfFormula);

        minValue = -500; maxValue = 500;
        Swarm swarm3 = new Swarm(swarmSize, minValue, maxValue);
        numOfFormula = 2;
        calculateSwarmAlgorithm(swarm3, numOfFormula);

        minValue = -500; maxValue = 500;
        Swarm swarm4 = new Swarm(swarmSize, minValue, maxValue);
        numOfFormula = 3;
        calculateSwarmAlgorithm(swarm4, numOfFormula);

        minValue = -500; maxValue = 600;
        Swarm swarm5 = new Swarm(swarmSize, minValue, maxValue);
        numOfFormula = 3;
        calculateSwarmAlgorithm(swarm5, numOfFormula);
    }

    private static void calculateSwarmAlgorithm(Swarm swarm, int numOfFormula) {
        swarm.calculateMax(numOfFormula);
        System.out.println("\nG_MAX = " + swarm.getGlobalMaxSpeed() + "\n");
        System.out.println("\nFUNC_MAX = " + Swarm.functionValue(swarm.getGlobalMaxSpeed(), numOfFormula) + "\n");
        swarm.clearParticles();
        swarm.calculateMin(numOfFormula);
        System.out.println("\nG_MIN = " + swarm.getGlobalMaxSpeed() + "\n");
        System.out.println("\nFUNC_MIN = " + Swarm.functionValue(swarm.getGlobalMaxSpeed(), numOfFormula) + "\n");
        swarm.clearParticles();
        System.out.println("\n-----------------------------------------------------------------------------\n");
    }

    // Вычисления кубической функции со сдвигами
    // Точка минимума - x = 0, функция в этой точке также равно 0, точка максимум  x = -20/18

    private static void menu(){
            System.out.println("\n\nЛабораторная работа №2 | Роевоей алгоритм | Николаев Н.С. | ИКБО-13-17\n");
            /*cout << "1. Создать граф случайным образом" << endl;
            cout << "2. Ввести граф вручную" << endl;
            cout << "3. Вывести все вершины из которых можно попасть в любую вершину, проходя не более 100 км" << endl;
            cout << "0. Выход\n" << endl;
            cout << "\nВведите команду: ";*/
    }
}
