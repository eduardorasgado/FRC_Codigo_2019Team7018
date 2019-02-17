/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // PUERTOS PARA EL JOYSTICK O LOS CONTROLES
  public final static int ACTUAL_USB_PORT = 0;
  // -- botones del joystick para ejecutar acciones
  // botones para el cargo handler
  public final static int LAUNCHER_BUTTON_NUMBER = 2;
  public final static int SUCKER_BUTTON_NUMBER = 1;
  public final static int BUTTON_PORT_3 = 3;
  public final static int BUTTON_PORT_5 = 5;

  public final static int SLIDER_AXIS_PORT = 3;

  // PUERTOS PARA EL DRIVE TRAIN
  public final static int MotorLeftRear = 0;
  public final static int MotorLeftFront = 1;

  public final static int MotorRightRear = 2;
  public final static int MotorRightFront = 3;

  // PUERTOS PARA EL CARGO HANDLER
  public final static int MotorCanonUpper = 4;
  public final static int MotorCanonBottom = 5;

  public final static int MotorCanonArm = 9;

  // PUERTOS PARA EL HATCH PANEL HANDLER
}
