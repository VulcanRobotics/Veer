/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author afiol-mahon
 */
public class SS_Swerve extends Subsystem{

O_Module module = new O_Module(RobotMap.SM3_CIM, RobotMap.SM3_CIMile, RobotMap.SM3_banebot, RobotMap.SM3_EncoderA, RobotMap.SM3_EncoderB);


    
    public void initDefaultCommand() {
        setDefaultCommand(new C_Swerve());
    }
    
}