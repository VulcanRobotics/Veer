/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.CommandBase;
import Robot.OI;
import com.sun.squawk.util.MathUtils;

/**
 *
 * @author afiol-mahon
 */
public class C_Swerve extends CommandBase {
    
    public C_Swerve() {
        requires(swerve);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }
    
    double turnConstant = 0.707;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double leftX = OI.joystick1.getRawAxis(1);
        double leftY = OI.joystick1.getRawAxis(2);
        double rightX = OI.joystick1.getRawAxis(4);
        
        swerve.module.setAngle(MathUtils.atan((leftY - rightX * turnConstant) / (leftX + rightX * turnConstant)));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
