/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author afiol-mahon
 */
public class O_Module {
    
    Victor cim;
    Victor cimile;
    
    Talon turnMotor;
    Encoder turnEncoder;
    
    PIDController turn;
    
    public O_Module(int CimPort, int CimilePort, int turnPort, int turnEncoderA, int turnEncoderB){
        cim = new Victor(RobotMap.driveModule, CimPort);
        cimile = new Victor(RobotMap.driveModule, CimilePort);
        turnMotor = new Talon(RobotMap.turnModule, turnPort);
        turnEncoder = new Encoder(turnEncoderA, turnEncoderB);
        turnEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kAngle);
        turn = new PIDController(0.01, 0.01, 0.01, turnEncoder, turnMotor);
        turn.setContinuous();
    }
    
    public void setAngle(int angle) {
        turn.setSetpoint(angle);
    }
}
