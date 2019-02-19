/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;
//import frc.robot.commands.SolenoidOperation;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.subsystems.HatchPanelHandler;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //SolenoidOperation s_op;
  private Joystick mJoyStick;
  private Spark motor;
  HatchPanelHandler hp_handler;

  public OI(){
    mJoyStick = new Joystick(RobotMap.joystick_port);
    //s_op = new SolenoidOperation(SolenoidOperation.Direction.STOP);
    motor = new Spark(RobotMap.motor_port);

    hp_handler = new HatchPanelHandler();
  }

  public void mainLoop(){
    //
    var speed = mJoyStick.getY();
    motor.set(speed);

    if(mJoyStick.getRawButtonPressed(RobotMap.explode_button)){
      // si el boton 1 es presionado
      //s_op.direction = SolenoidOperation.Direction.FORWARD;
      hp_handler.explodeHatch();
      //System.out.println("explode");
    }
    if(mJoyStick.getRawButtonReleased(RobotMap.explode_button)){
      // si el boton uno es liberado
      //s_op.direction = SolenoidOperation.Direction.STOP;
      hp_handler.stopSolenoid();
    }
    if(mJoyStick.getRawButtonPressed(RobotMap.implode_button)){
      // si el boton 2 es presionado
      //s_op.direction = SolenoidOperation.Direction.REVERSE;
      hp_handler.implodeHatch();
      //System.out.println("implode");
    }
    if(mJoyStick.getRawButtonReleased(RobotMap.implode_button)){
      // si el boton 2 es liberado
      //s_op.direction = SolenoidOperation.Direction.STOP;
      hp_handler.stopSolenoid();
    }
  }
}
