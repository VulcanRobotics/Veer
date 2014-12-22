/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 *
 * @author liamcook
 */
public class rightBumper extends JoystickButton {

    GenericHID joystick;
    int buttonNumber;
    
    public rightBumper(GenericHID joystick, int buttonNumber) {
        super(joystick, buttonNumber);
        this.buttonNumber = buttonNumber;
        this.joystick = joystick;
    }
    
    public boolean get() {
        return joystick.getRawAxis(buttonNumber) < -0.1;
    }
}
