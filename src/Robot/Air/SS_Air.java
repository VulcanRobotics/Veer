/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Air;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author liamcook
 */
public class SS_Air extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    Compressor compressor;
    
    public SS_Air() {
        compressor = new Compressor(RobotMap.turnModule, RobotMap.Compressor_Switch, RobotMap.driveModule, RobotMap.Compressor_Relay);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        compressor.start();
    }
}
