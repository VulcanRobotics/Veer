/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author 1218
 */

public class O_TurningEncoder {

    Encoder encoder;
    int countsPerRotation;
    
    public O_TurningEncoder(int encoderPort_A, int encoderPort_B, int countsPerRotation) {
        encoder = new Encoder(encoderPort_A, encoderPort_B);
        this.countsPerRotation = countsPerRotation;
    }
    
    public void setForward() {
        encoder.reset();
    }
    
    public int getAngle() {
        // get abslotute abgle from 0 to 360 ignore switch of direction
        return (encoder.get()/countsPerRotation)/360;
    }
}