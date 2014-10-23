/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensing;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
/**
 *
 * @author 1218
 */
public class TurningEncoder extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Encoder encoder;
    int countsPerRotation;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setForward()
    {
        encoder.reset();
    }
    
    public void TurningEncoder(int port1, int port2, int newCountsPerTurn, int zeroPort)
    {
        encoder = new Encoder(port1, port2);
        countsPerRotation = newCountsPerTurn;
    }
    
    public int getAngle()
    {// get abslotute abgle from 0 to 360 ignore switch of direction
        int encoderValue = encoder.get();
        return (encoderValue/countsPerRotation) /360;
    }
}
