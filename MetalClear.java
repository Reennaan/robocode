package pkg;
import robocode.*;
import java.awt.Color;
import robocode.ScannedRobotEvent;

//API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

public class MetalClear extends AdvancedRobot
{
   

	public void run() {
	
		
		   setColors(Color.blue,Color.blue,Color.green);
		   setScanColor(Color.yellow);

		while(true) {
			

			setTurnRadarRight(360);
			setAhead(100);
			setTurnRight(50);
			execute();
			
					

		}
	}


public void onScannedRobot(ScannedRobotEvent e) {
		double robotHeading = getHeading();//pegando o angulo do robo
		double radarHeading = getRadarHeading();//pegando angulo do radar
		double enemyHeading = e.getBearing();//pegando o angulo do inimigo
		double gunHeading = getGunHeading();
		
		/*somando o angulo do angulo do robô com o angulo do inimigo é poissível
		 *achar angulo absoluto do inimigo por exemplo o robo aponta para 90 gaus
		 * e o inimigo está 30 gaus a direita 90 + 30 = 120 este é o angulo absoluto
		 * do inimigo para mover o radar até ele basta subtrair o angulo do radar 
		 * pelo valor absoluto do inimigo vamos supor que é 32, 120 - 32 = 88 então
		 * o radar deveria virar 88 graus a direita, caso o numero seja negativo então
		 * o radar deve virar à esquerda
		 */
		
		double absoluteAngle = robotHeading + enemyHeading;
		double target = absoluteAngle - radarHeading;
		double gunTarget = absoluteAngle - gunHeading;
		
		 if (target > 180) {
   	     	target -= 360;
		  	gunTarget -=360;
	    } else if (target < -180) {
	        target += 360;
			gunTarget +=360;
	    }
			
			setTurnRadarRight(target);
			setTurnGunRight(gunTarget);
			if(getVelocity() == 0 & Math.abs(gunTarget) <= 2 ){
				fire(3);
			}
			fire(1);

	}


	public void onHitByBullet(HitByBulletEvent e) {
	
		
	}
	

	public void onHitWall(HitWallEvent e) {

		
	
	}	
}
