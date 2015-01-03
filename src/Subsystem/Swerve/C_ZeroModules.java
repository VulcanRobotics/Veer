/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.CommandBase;
import Robot.OI;

/**
 *
 * @author afiol-mahon
 */
public class C_ZeroModules extends CommandBase {
      
    int numberOfModulesZeroed;
            
    public C_ZeroModules() {
        requires(swerve);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
         for(int k = 0; k<4; k++) {
             swerve.modules[k].isZeroing = true;
         }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       numberOfModulesZeroed = 0;
    
       for(int k = 0; k<4; k++) {
             swerve.modules[k].zero();
             if (!swerve.modules[k].isZeroing) {
                 numberOfModulesZeroed++;
             }
         }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

        return numberOfModulesZeroed == 4 | OI.Button_X.get();
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("Swerve Modules Zeroed");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
