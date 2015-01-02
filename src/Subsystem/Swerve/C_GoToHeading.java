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
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author liamcook
 */
public class C_GoToHeading extends CommandBase implements PIDOutput {
    
    PIDController PID;
    public C_GoToHeading() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(swerve);
        PID = new PIDController(.07, 0, 0, swerve.veerGyro, this, .001);
        PID.setInputRange(-180.0, 180.0);
        PID.setOutputRange(-1.0, 1.0);
        PID.setPID(1, 0, 0);
        PID.disable();
    }

    
    
    // Called just before this Command runs the first time
    protected void initialize() {
        PID.enable();
    }

    public void pidWrite(double turnSpeed) {
        //gets field centric heading = need to find a way to make 1 method for go to heading and one for twist
         int heading = OI.leftAngle();
        int robotCentricHeading = heading - (int)(swerve.veerGyro.getIntAngle() % 360);
        if (robotCentricHeading > 180){
            robotCentricHeading = robotCentricHeading -360; 
        }
        if (robotCentricHeading < -180){
            robotCentricHeading = robotCentricHeading +360; 
        }
        
        //get translation vectors
        
        O_Vector translationVector = new O_Vector();
        translationVector = translationVector.polarVector(robotCentricHeading, OI.leftMagnitude());
        
        //use value provided by PID for rotation
        swerve.swerve(translationVector, new O_Point(0, 0), turnSpeed);
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("Pidding to heading");
        PID.setSetpoint(OI.rightAngle());
        
        PID.setPID(SmartDashboard.getNumber("HeadingP"),
                   SmartDashboard.getNumber("HeadingI"),
                   SmartDashboard.getNumber("HeadingD"));
        SmartDashboard.putNumber("Heading", swerve.veerGyro.pidGet());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        PID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

    
}
