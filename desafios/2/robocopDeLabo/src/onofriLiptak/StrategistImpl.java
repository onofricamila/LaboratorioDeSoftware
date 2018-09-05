package onofriLiptak;

import java.util.Random;

public class StrategistImpl implements Strategist{

    private static final LaboRobotStrategy attackerStrategy = new StrategistImpl.AttackerStrategy();
    private static final LaboRobotStrategy deffenderStrategy = new StrategistImpl.DeffenderStrategy();
    private static final StrategistImpl instance = new StrategistImpl();

    public static Strategist getInstance(){
        return instance;
    }

    public LaboRobotStrategy getStrategyForRunning(ArnoldSchwarzenegger arnoldSchwarzenegger){
        LaboRobotStrategy strategies[] = {attackerStrategy, deffenderStrategy};
        int rnd = new Random().nextInt(strategies.length);
        return strategies[rnd];
    }

    public LaboRobotStrategy getStrategyForScannedRobot(ArnoldSchwarzenegger arnoldSchwarzenegger){
        if(arnoldSchwarzenegger.getScannedDistance() < 2000){
            return attackerStrategy;
        }else{
            return deffenderStrategy;
        }
    }

    public LaboRobotStrategy getStrategyForHitByBullet(ArnoldSchwarzenegger arnoldSchwarzenegger){
        if(arnoldSchwarzenegger.getEnergy() > 40){
            return attackerStrategy;
        }else{
            return deffenderStrategy;
        }
    }

    public LaboRobotStrategy getStrategyForHitWall(ArnoldSchwarzenegger arnoldSchwarzenegger){
        if(arnoldSchwarzenegger.getEnergy() > 50){
            return attackerStrategy;
        }else{
            return deffenderStrategy;
        }
    }

    // Clases anidadas -----------------------------------------------------------------------------

    private static class AttackerStrategy implements LaboRobotStrategy{

        public void run(ArnoldSchwarzenegger arnoldSchwarzenegger){
            while(true) {
                arnoldSchwarzenegger.ahead(20);
                arnoldSchwarzenegger.turnGunLeft(360);
                arnoldSchwarzenegger.back(20);
            }
        }

        public void onScannedRobot(ArnoldSchwarzenegger arnoldSchwarzenegger){
            arnoldSchwarzenegger.turnGunTo(arnoldSchwarzenegger.getScannedAngle());
            arnoldSchwarzenegger.fire(3);
        }

        public void onHitByBullet(ArnoldSchwarzenegger arnoldSchwarzenegger){
            arnoldSchwarzenegger.turnLeft(90);
            arnoldSchwarzenegger.back(200);
            arnoldSchwarzenegger.turnGunRight(360);
        }

        public void onHitWall(ArnoldSchwarzenegger arnoldSchwarzenegger){
            arnoldSchwarzenegger.back(20);
            arnoldSchwarzenegger.turnRight(90);
            arnoldSchwarzenegger.ahead(1000);
        }

    }

    private static class DeffenderStrategy implements LaboRobotStrategy{

        public void run(ArnoldSchwarzenegger arnoldSchwarzenegger){
            while(true) {
                arnoldSchwarzenegger.ahead(5000);
                arnoldSchwarzenegger.turnGunLeft(360);
            }
        }

        public void onScannedRobot(ArnoldSchwarzenegger arnoldSchwarzenegger){
            arnoldSchwarzenegger.turnGunTo(arnoldSchwarzenegger.getScannedAngle());
            arnoldSchwarzenegger.fire(3);
            arnoldSchwarzenegger.turnGunTo(arnoldSchwarzenegger.heading);
            arnoldSchwarzenegger.turnLeft(90);
            arnoldSchwarzenegger.ahead(100);

        }

        public void onHitByBullet(ArnoldSchwarzenegger arnoldSchwarzenegger){
            arnoldSchwarzenegger.turnLeft(90);
            arnoldSchwarzenegger.ahead(400);
            arnoldSchwarzenegger.turnGunLeft(360);
        }

        public void onHitWall(ArnoldSchwarzenegger arnoldSchwarzenegger){
            arnoldSchwarzenegger.back(20);
            arnoldSchwarzenegger.turnLeft(90);
        }

    }
}
