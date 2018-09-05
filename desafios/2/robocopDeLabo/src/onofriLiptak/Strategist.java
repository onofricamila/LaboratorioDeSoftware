package onofriLiptak;

public interface Strategist {

    LaboRobotStrategy getStrategyForRunning(ArnoldSchwarzenegger arnoldSchwarzenegger);
    LaboRobotStrategy getStrategyForScannedRobot(ArnoldSchwarzenegger arnoldSchwarzenegger);
    LaboRobotStrategy getStrategyForHitByBullet(ArnoldSchwarzenegger arnoldSchwarzenegger);
    LaboRobotStrategy getStrategyForHitWall(ArnoldSchwarzenegger arnoldSchwarzenegger);

}
