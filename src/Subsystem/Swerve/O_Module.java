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
        turnEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
        turnEncoder.setDistancePerPulse(2000/360);
        turnEncoder.start();
        turn = new PIDController(1.0, 0.1, 0.1, turnEncoder, turnMotor);
        turn.setContinuous();
        turn.enable();
    }
    
    public void setAngle(double angle) {
        turn.setSetpoint(angle);
        System.out.println(turnEncoder.getDistance());
    }
}
