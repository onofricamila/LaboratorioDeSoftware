package onofriLiptak;

public interface LaboRobotStrategy {

    void run(ArnoldSchwarzenegger arnoldSchwarzenegger);
    void onScannedRobot(ArnoldSchwarzenegger arnoldSchwarzenegger);
    void onHitByBullet(ArnoldSchwarzenegger arnoldSchwarzenegger);
    void onHitWall(ArnoldSchwarzenegger laboRobot);

}
