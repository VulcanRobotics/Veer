/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import MathObject.O_Point;
import MathObject.O_Vector;
import Robot.CommandBase;
import Robot.OI;

/**
 *
 * @author liamcook
 */
public class C_Twist extends CommandBase {
    
    public C_Twist() {
        requires(swerve);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("twist");
        int heading = OI.leftAngle();
        int robotCentricHeading = heading - (int)(swerve.veerGyro.getIntAngle() % 360);
        if (robotCentricHeading > 180){
            robotCentricHeading = robotCentricHeading -360; 
        }
        if (robotCentricHeading < -180){
            robotCentricHeading = robotCentricHeading +360; 
        }
        System.out.println("heading: " + heading);
        System.out.println("gyro: " + (swerve.veerGyro.getIntAngle() % 360));
        System.out.println("robo centric: " + robotCentricHeading);
        O_Vector translationVector = new O_Vector();
        translationVector = translationVector.polarVector(robotCentricHeading, OI.leftMagnitude());
        swerve.swerve(translationVector, new O_Point(0, 0), -OI.rightX());
        System.out.println("leftx: " + OI.leftX());
        System.out.println("lefty: " + OI.leftY());
        System.out.println("left angle: " + OI.leftAngle());
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
