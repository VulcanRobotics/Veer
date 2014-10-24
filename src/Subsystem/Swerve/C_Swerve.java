package Subsystem.Swerve;

import MathObject.O_Point;
import Robot.CommandBase;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 1218
 */
public class C_Swerve extends CommandBase {

    public C_Swerve() {
        requires(swerve);
    }

    protected void initialize() {
    }

    protected void execute() {
        System.out.println("execting C_Swerve");
        swerve.swerve(90, (float)0.75, new O_Point(0,0), 0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
