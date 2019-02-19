/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;
import frc.robot.commands.SolenoidOperation;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  SolenoidOperation s_op;
  private Joystick mJoyStick;

  public OI(){
    mJoyStick = new Joystick(RobotMap.joystick_port);
    s_op = new SolenoidOperation(SolenoidOperation.Direction.STOP);
  }

  public void mainLoop(){
    //
    if(mJoyStick.getRawButtonPressed(RobotMap.explode_button)){
      // si el boton 1 es presionado
      s_op.direction = SolenoidOperation.Direction.FORWARD;
    }
    if(mJoyStick.getRawButtonReleased(RobotMap.explode_button)){
      // si el boton uno es liberado
      s_op.direction = SolenoidOperation.Direction.STOP;
    }
    if(mJoyStick.getRawButtonPressed(RobotMap.implode_button)){
      // si el boton 2 es presionado
      s_op.direction = SolenoidOperation.Direction.REVERSE;
    }
    if(mJoyStick.getRawButtonReleased(RobotMap.implode_button)){
      // si el boton 2 es liberado
      s_op.direction = SolenoidOperation.Direction.STOP;
    }
  }
}
