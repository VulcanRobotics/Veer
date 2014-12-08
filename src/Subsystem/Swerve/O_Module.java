/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subsystem.Swerve;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
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
    
    public O_Module(int CimPort, int CimilePort, int turnPort, int turnEncoderA, int turnEncoderB) {
        cim = new Victor(RobotMap.driveModule, CimPort);
        cimile = new Victor(RobotMap.driveModule, CimilePort);
        turnMotor = new Talon(RobotMap.turnModule, turnPort);
        //Encoder Initilization
            turnEncoder = new TurnEncoder(turnEncoderA, turnEncoderB);
        //TurnPID Initialization
            turn = new PIDController(0.1, 0.0, 0.0, turnEncoder, turnMotor);
            turn.setContinuous();
            turn.setInputRange(0.0, 360.0);
            turn.setOutputRange(-0.3, 0.3);
            turn.enable();
    }
    
    public void setAngle(double angle) {
        turn.setSetpoint(angle);
        SmartDashboard.putNumber("WheelAngle", turnEncoder.pidGet());
        SmartDashboard.putNumber("PIDTarget", turn.getSetpoint());
        System.out.println(turn.getP());
    }
    
    class TurnEncoder implements PIDSource {
        Encoder encoder;
        public TurnEncoder(int turnEncoderA, int turnEncoderB) {
            encoder = new Encoder(RobotMap.turnModule, turnEncoderA, RobotMap.turnModule, turnEncoderB, false, EncodingType.k4X);
            encoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
            encoder.setDistancePerPulse(360.0/450.0);
            encoder.setReverseDirection(true);
            encoder.start();
            encoder.reset();
        }
        
        public double pidGet() {
            return encoder.pidGet()%360.0;
        }
    
    }
}
