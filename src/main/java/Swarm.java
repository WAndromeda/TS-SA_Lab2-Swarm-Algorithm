import java.util.ArrayList;

public class Swarm {
    private final static int iter = 100000;
    private ArrayList<Particle> particles;
    private final int swarmSize;
    private double minValue;
    private double maxValue;
    private double globalMaxSpeed;

    public Swarm(int swarmSize, double minValue, double maxValue) {
        this.swarmSize = swarmSize;
        this.minValue = minValue;
        this.maxValue = maxValue;
        particles = new ArrayList<>();
    }

    public static Double functionValue(double x, int numOfFormula){
        //Используется функция из методички
        if (numOfFormula == 0)
            return (- x * Math.sin(Math.pow(Math.abs(x), (1.0/2.0))) );
        else if (numOfFormula == 1)
            return  3 * Math.pow(x, 3) + 5 * Math.pow(x, 2);
        else if (numOfFormula == 2)
            return (x * Math.cos(Math.pow(Math.abs(x), (1.0/4.0))) );
        else if (numOfFormula == 3)
            return (x * Math.cos(Math.pow(x, (1.0/4.0))) );
        throw new RuntimeException("Некорректный номер формулы");
    }

    public ArrayList<Particle> getParticles() {
        return particles;
    }

    public void setParticles(ArrayList<Particle> particles) {
        this.particles = particles;
    }

    public int getSwarmSize() {
        return swarmSize;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void clearParticles(){
        if (particles != null)
            particles.clear();
        particles = new ArrayList<>();
    }

    public double getGlobalMaxSpeed() {
        return globalMaxSpeed;
    }

    public void setGlobalMaxSpeed(double globalMaxSpeed) {
        this.globalMaxSpeed = globalMaxSpeed;
    }

    public double calculateMax(final int numOfFormula){
        // -----------------------------------------
        for (int i = 0; i < swarmSize; i++)
            particles.add(new Particle(0, 0, minValue + Math.random()*(maxValue - minValue)));
        globalMaxSpeed = particles.get(0).getLocalMaxSpeed();
        for (Particle p : particles){
            //p.setLocalMaxSpeed(functionValue(p.getX(), numOfFormula));
            if (functionValue(p.getX(), numOfFormula) > functionValue(p.getLocalMaxSpeed(), numOfFormula)) {
                p.setLocalMaxSpeed(p.getX());
                if (functionValue(p.getLocalMaxSpeed(), numOfFormula) > functionValue(globalMaxSpeed, numOfFormula))
                    globalMaxSpeed = p.getLocalMaxSpeed();
            }
        }
        // -----------------------------------------
        for (int i = 0; i < iter; i++){

            //Подсчитать скорость
            for (Particle p : particles)
                p.nextIterationMax(globalMaxSpeed, minValue, maxValue, numOfFormula);

            for (Particle p : particles)
                if (Swarm.functionValue(p.getLocalMaxSpeed(), numOfFormula) > Swarm.functionValue(globalMaxSpeed, numOfFormula)) {
                    globalMaxSpeed = p.getLocalMaxSpeed();
                }
            //Вычислить лучшую скорость, значение функции в ней должно быть минимально
        }
        return globalMaxSpeed;
    }

    public double calculateMin(final int numOfFormula){
        // -----------------------------------------
        for (int i = 0; i < swarmSize; i++)
            particles.add(new Particle(0, 0, minValue + Math.random()*(maxValue - minValue)));
        globalMaxSpeed = particles.get(0).getLocalMaxSpeed();
        for (Particle p : particles){
            //p.setLocalMaxSpeed( functionValue(p.getX(), numOfFormula));
            if (functionValue(p.getX(), numOfFormula) < functionValue(p.getLocalMaxSpeed(), numOfFormula)) {
                p.setLocalMaxSpeed(p.getX());
                if (functionValue(p.getLocalMaxSpeed(), numOfFormula) < functionValue(globalMaxSpeed, numOfFormula))
                    globalMaxSpeed = p.getLocalMaxSpeed();
            }
        }
        // -----------------------------------------
        for (int i = 0; i < iter; i++){
            //Подсчитать скорость
            for (Particle p : particles)
                p.nextIterationMin(globalMaxSpeed, minValue, maxValue, numOfFormula);
            for (Particle p : particles)
                if (Swarm.functionValue(p.getLocalMaxSpeed(), numOfFormula) < Swarm.functionValue(globalMaxSpeed, numOfFormula)) {
                    globalMaxSpeed = p.getLocalMaxSpeed();
                }
            //Вычислить лучшую скорость, значение функции в ней должно быть минимально
        }
        return globalMaxSpeed;
    }


   /* private static class RunningParticle implements Runnable{

        private Particle particle;
        private int particleNumber;
        private boolean ready;

        public RunningParticle(Particle particle, int particleNumber) {
            this.particle = particle;
            this.particleNumber = particleNumber;
            ready = false;
        }

        public void run() {
            try {
                particle.nextIteration(particleNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ready = true;
        }

        public boolean isReady() {
            return ready;
        }

        public Particle getParticle() {
            return particle;
        }
    }

    private static boolean isAllThreadsEnded(ArrayList<RunningParticle> runningParticles){
        boolean isReady = true;
        for (RunningParticle rP : runningParticles){
            if (!rP.isReady()) {
                //System.out.println();
                //System.out.println("НЕ КОНЕЦ: " + rP.particleNumber);
                isReady = false;
            }
        }
        return isReady;
    }*/

}
