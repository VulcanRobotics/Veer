/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author liamcook
 */

//is a class so we can add a compass, 2nd gyro to read against, IMU, etc
public class O_1218Gyro implements PIDSource {
   Gyro gyro = new Gyro(RobotMap.Gyro); 
   int offset = 0;
   public O_1218Gyro() {
       gyro.setSensitivity(.00738888);
   }
   
   int getAngle() {
       return (int)gyro.getAngle();
   }
   
   void reset() {
       gyro.reset();
   }
   
   public double pidGet() {
       //pid needs to go from -180 to 180 to match setpoint
       return getAngle()-180;
   }
}
