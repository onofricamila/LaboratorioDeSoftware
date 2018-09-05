package onofriLiptak;

public class LaboRobotStrategyImpl implements LaboRobotStrategy {

    public void run(ArnoldSchwarzenegger arnoldSchwarzenegger){
        arnoldSchwarzenegger.turnTo(45);
        while(true) {
            arnoldSchwarzenegger.turnTo(360);
            arnoldSchwarzenegger.ahead(5000);
        }
    }

    public void onScannedRobot(ArnoldSchwarzenegger arnoldSchwarzenegger){

        arnoldSchwarzenegger.turnTo(arnoldSchwarzenegger.getScannedBearing());

        if(arnoldSchwarzenegger.getScannedDistance() < 50){
            arnoldSchwarzenegger.fire(20);
        }else{
            arnoldSchwarzenegger.fire(10);
        }

    }

    public void onHitByBullet(ArnoldSchwarzenegger arnoldSchwarzenegger){
        arnoldSchwarzenegger.turnTo(arnoldSchwarzenegger.getScannedBearing());
        arnoldSchwarzenegger.ahead(10);
        arnoldSchwarzenegger.fire(5);
    }

    public void onHitWall(ArnoldSchwarzenegger arnoldSchwarzenegger){
        arnoldSchwarzenegger.back(20);
        arnoldSchwarzenegger.turnRight(90);
        arnoldSchwarzenegger.ahead(1000);

    }

}
