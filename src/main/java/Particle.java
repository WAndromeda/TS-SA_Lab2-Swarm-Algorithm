public class Particle {

    private double currentSpeed;
    private double localMaxSpeed;
    private double x;

    public Particle(double currentSpeed, double localMaxSpeed, double x) {
        this.currentSpeed = currentSpeed;
        this.localMaxSpeed = localMaxSpeed;
        this.x = x;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public double getLocalMaxSpeed() {
        return localMaxSpeed;
    }

    public void setLocalMaxSpeed(double localMaxSpeed) {
        this.localMaxSpeed = localMaxSpeed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void nextIterationMax(double globalMaxSpeed, double minValue, double maxValue, int numOfFormula)  {
        double t = 1; //какой-то коэффициент
        double a, b;
        a = Math.random(); b = 1 - a;
        double y = Math.random(); //Коэффициент сдерживания окружающей среды
        currentSpeed = ( y * (currentSpeed + a * (localMaxSpeed - x) + b * (globalMaxSpeed - x)) );
        x += t * currentSpeed;
        if (x < minValue)
            x = minValue;
        if (x > maxValue)
            x = maxValue;
        if (Swarm.functionValue(x, numOfFormula) > Swarm.functionValue(localMaxSpeed, numOfFormula)) {
            localMaxSpeed = x;
        }
        //System.out.println(String.format("x = %s", localMaxSpeed));
    }

    public void nextIterationMin(double globalMaxSpeed, double minValue, double maxValue, int numOfFormula)  {
        double t = 1; //какой-то коэффициент
        double a, b;
        a = Math.random(); b = 1 - a;
        double y = Math.random(); //Коэффициент сдерживания окружающей среды
        currentSpeed = ( y * (currentSpeed + a * (localMaxSpeed - x) + b * (globalMaxSpeed - x)) );
        x += t * currentSpeed;

        if (x < minValue) {
            //System.out.println("\nNUM = " + numOfFormula + " | MINVALUE = " + minValue + "\n");
            x = minValue;
        }
        if (x > maxValue)
            x = maxValue;
        //System.out.println(x);
        if (Swarm.functionValue(x, numOfFormula) < Swarm.functionValue(localMaxSpeed, numOfFormula)) {
            localMaxSpeed = x;
        }
        //System.out.println(String.format("x = %s", localMaxSpeed));
    }

    /*private void getPenalty(int penalty) throws InterruptedException {
        System.out.println("X = " + x);
        if (x < Swarm.minValue)
            x -= minValue;
        else
        if (x > Swarm.maxValue)
            x += maxValue;

        System.out.println("X + PEN = " + x);
        Thread.sleep(7000);
    }*/
}
