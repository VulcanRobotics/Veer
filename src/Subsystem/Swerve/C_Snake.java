/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import MathObject.O_Point;
import MathObject.O_Vector;
import Robot.CommandBase;
import static Robot.CommandBase;
import Robot.OI;

/**
 *
 * @author liamcook
 */
public class C_Snake extends CommandBase {
    
    public C_Snake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(swerve);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("snaking");
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        swerve.swerve(new O_Vector(0.0, 0.0), new O_Point(0.0, 1.0/(OI.rightX() + 0.02 )), OI.joystick1.getY() * ((OI.rightX() >= 0) ? 1.0 : -1.0));
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
