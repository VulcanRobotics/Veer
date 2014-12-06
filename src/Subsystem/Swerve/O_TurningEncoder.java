/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author 1218
 */

public class O_TurningEncoder implements PIDSource{

    Encoder encoder;
    
    public O_TurningEncoder(int encoderPort_A, int encoderPort_B) {

        encoder = new Encoder(RobotMap.turnModule, encoderPort_A, RobotMap.turnModule, encoderPort_B, true, CounterBase.EncodingType.k4X);
        encoder.setDistancePerPulse(1);
        encoder.start();

    }
    
    public void setForward() {
        encoder.reset();
    }

    public double pidGet() {
        //needs to return a value between -180 and 180
        encoder.start();
        //System.out.println(encoder.get());
        double numberOfClicks = SmartDashboard.getNumber("NC", 7000);
        if (encoder.getRaw() > 0) {
            System.out.println("Encoder is: " + (((( encoder.getRaw()/numberOfClicks)*360.0) % 360.0) - 180));
            return ((( encoder.getRaw()/numberOfClicks)*360.0) % 360.0) - 180;
        }
        else
        {
            System.out.println("Encoder is: " + (((( encoder.getRaw()/numberOfClicks)*360.0) % 360.0) + 180));
            return ((( encoder.getRaw()/numberOfClicks)*360.0) % 360.0) + 180;
        }
        
    }
}