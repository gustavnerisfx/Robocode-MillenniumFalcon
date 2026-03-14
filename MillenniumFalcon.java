package mypackage;

import robocode.*;
import java.awt.Color;
import robocode.util.Utils;

public class MillenniumFalcon extends AdvancedRobot {

    public void run() {
        setBodyColor(Color.red);
        setGunColor(Color.white);
        setRadarColor(Color.red);
        setScanColor(Color.black);
        setBulletColor(Color.yellow);

       
        setAdjustRadarForGunTurn(true);
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForRobotTurn(true);

        
        setTurnRadarRightRadians(Double.POSITIVE_INFINITY);

        while (true) {
		
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        double absoluteBearing = getHeadingRadians() + e.getBearingRadians();

        double radarTurn = Utils.normalRelativeAngle(absoluteBearing - getRadarHeadingRadians());
        setTurnRadarRightRadians(radarTurn);

        double gunTurn = Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians());
        setTurnGunRightRadians(gunTurn);

        if (Math.abs(gunTurn) < Math.toRadians(10)) {
            fireBullet(3);
        }

        setTurnRight(e.getBearing());
        setAhead(e.getDistance() + 5);
    }

    public void onHitRobot(HitRobotEvent e) {
        double turnGunAmt = Utils.normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());
        turnGunRight(turnGunAmt);
        fireBullet(3);
        ahead(10);
    }

    public void onHitWall(HitWallEvent e) {
        back(50);
        turnLeft(45);
    }
}
