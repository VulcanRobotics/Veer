/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author afiol-mahon
 */
public class O_Module {
    
    Victor cim;
    Victor cimile;
    
    Talon turnMotor;
    TurnEncoder turnEncoder;
    
    public PIDController turn;
    
    public O_Module(int CimPort, int CimilePort, int turnPort, int turnEncoderA, int turnEncoderB){
        cim = new Victor(RobotMap.driveModule, CimPort);
        cimile = new Victor(RobotMap.driveModule, CimilePort);
        turnMotor = new Talon(RobotMap.turnModule, turnPort);
        turnEncoder = new TurnEncoder(turnEncoderA, turnEncoderB);
        
        turn = new PIDController(1.0, 0.1, 0.1, turnEncoder, turnMotor, .0010);
        turn.setInputRange(0, 360);
        turn.setOutputRange(-.3, .3);
        turn.setContinuous();
        
        turn.enable();
    }
    
    public void setAngle(double angle) {
        turn.setSetpoint(angle);
        SmartDashboard.putNumber("PIDTarget", turn.getSetpoint());
        System.out.println("P: "+ turn.getP());
        System.out.println(turnEncoder.pidGet());
    }
}