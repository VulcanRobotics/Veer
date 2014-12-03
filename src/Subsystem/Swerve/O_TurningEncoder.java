/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author 1218
 */

public class O_TurningEncoder implements PIDSource{

    Encoder encoder;
    int countsPerRotation = 360;
    
    public O_TurningEncoder(int encoderPort_A, int encoderPort_B) {
        encoder = new Encoder(RobotMap.turnModule, encoderPort_A, RobotMap.turnModule, encoderPort_B);
    }
    
    public void setForward() {
        encoder.reset();
    }

    public double pidGet() {
        //needs to return a value between -180 and 180
        return (encoder.get()/countsPerRotation)/360 - 180;
    }
}