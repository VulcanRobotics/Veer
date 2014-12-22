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
public class C_Pivot extends CommandBase {
    
    O_Point pivotPoint;
    int fieldCentricAngle = 0;
    double shouldReverse = 0;
    public C_Pivot() {
        requires(swerve);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (OI.Button_L1.get()) {
            fieldCentricAngle = -45;
            shouldReverse = 1.0;
        }
        if (OI.Button_L2.get()) {
            fieldCentricAngle = -135;
            shouldReverse = -1.0;
        }
        if (OI.Button_R1.get()) {
            fieldCentricAngle = 45;
            shouldReverse = -1.0;
        }
        if (OI.Button_R2.get()) {
            fieldCentricAngle = 135;
            shouldReverse = 1.0;
        }
       
        int robotCenticAngle = fieldCentricAngle - (int)(swerve.gyro.getAngle() % 360);
        if (robotCenticAngle > 180){
            robotCenticAngle = robotCenticAngle -360; 
        }
        if (robotCenticAngle < -180){
            robotCenticAngle = robotCenticAngle +360; 
        }
        
        double x = Math.cos(Math.toRadians(robotCenticAngle));
        if (x > 0) {
            x = 1.0;
        }
        if (x<0) {
            x = -1.0;
        }
        double y = Math.sin(Math.toRadians(robotCenticAngle));
        if (y > 0) {
            y = 1.0;
        }
        if (y < 0) {
            y = -1.0;
        }
       
        pivotPoint = new O_Point(x, y);
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("pivoting");
        
        swerve.swerve(new O_Vector(0, 0), pivotPoint, shouldReverse * OI.rightX());
             
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
