/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.CommandBase;

/**
 *
 * @author liamcook
 */
public class C_GoToHeading extends CommandBase {
    
    //PIDController PID = new PIDController(.07, 0, 0, turn, gyro, );
    
    public C_GoToHeading() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(swerve);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("Pidding to heading");
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
