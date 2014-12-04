/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author 1218
 */

public class O_TurningEncoder implements PIDSource{

    Encoder encoder;
    int countsPerRotation = 10;
    
    public O_TurningEncoder(int encoderPort_A, int encoderPort_B) {
        encoder = new Encoder(2, encoderPort_A, 2, encoderPort_B);
        encoder.setDistancePerPulse(1.0);
        encoder.start();
    }
    
    public void setForward() {
        encoder.reset();
    }

    public double pidGet() {
        //needs to return a value between -180 and 180
        encoder.start();
        //System.out.println(encoder.get());
        double encoderRawValue = (encoder.get()/countsPerRotation);
        while (encoderRawValue > 360)
        {
            encoderRawValue = encoderRawValue - 360;
        }
        //System.out.println(encoder.get());
        return encoderRawValue - 180;
    }
}