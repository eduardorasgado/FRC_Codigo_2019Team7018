/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
// Se importa dentro del objeto differencialDrive, dos de ellos
import edu.wpi.first.wpilibj.Spark;
// permite crear un sistema diferencial para controlar los dos motores
// que  le dan la direccion al robot
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// controlar 2 motores izquierdos y dos derecho
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap;

public class DifferentialDriveTrain extends Subsystem {
  // Creando los motores para el robot diferencial
  private Spark motorRearRight;  // (LF)(LR)----(*RR)(RF)
  private Spark motorFrontRight; // (LF)(LR)----(RR)(*RF)

  private Spark motorRearLeft; // (LF)(*LR)----(RR)(RF)
  private Spark motorFrontLeft; // (*LF)(LR)----(RR)(RF)

  // creando el sistema diferencial para el robot
  private DifferentialDrive mecanismoPrincipal;

  @Override
  public void initDefaultCommand() {
    // definiendo los controladores de los motores derechos
    motorRearRight = new Spark(RobotMap.MotorRightRear);
    motorFrontRight = new Spark(RobotMap.MotorRightFront);
    // definiendo los controladores de los motores izquierdos
    motorRearLeft = new Spark(RobotMap.MotorLeftRear);
    motorFrontLeft = new Spark(RobotMap.MotorLeftFront);

    // agrupando los controladores dada una configuracion diferencial
    var m_right = new SpeedControllerGroup(motorFrontRight, motorRearRight);
    var m_left = new SpeedControllerGroup(motorFrontLeft, motorRearLeft);

    mecanismoPrincipal = new DifferentialDrive(m_left, m_right);
  }

  public void Drive(double ySpeed, double xSpeed){
    // tomando los datos de traslacion del joystick
    mecanismoPrincipal.arcadeDrive(-ySpeed, xSpeed);
  }
}
