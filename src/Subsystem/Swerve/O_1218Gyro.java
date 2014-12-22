/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author liamcook
 */
public class O_1218Gyro {
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
}
