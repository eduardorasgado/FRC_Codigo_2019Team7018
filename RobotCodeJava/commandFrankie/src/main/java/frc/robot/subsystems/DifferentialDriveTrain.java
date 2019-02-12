/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
// Se importa dentro del objeto differencialDrive, dos de ellos
import edu.wpi.first.wpilibj.Spark;
// permite crear un sistema diferencial para controlar los dos motores
// que  le dan la direccion al robot
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// controlar 2 motores izquierdos y dos derecho
import edu.wpi.first.wpilibj.SpeedControllerGroup;


/**
 * Add your docs here.
 */
public class DifferentialDriveTrain extends Subsystem {
  // puertos para el spark
  private final int SPARK_PORT_LEFT_REAR = 0;
  private final int SPARK_PORT_LEFT_FRONT = 1;

  private final int SPARK_PORT_RIGHT_REAR = 2;
  private final int SPARK_PORT_RIGHT_FRONT = 6;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  // Creando los motores para el robot diferencial
  private Spark motorRearRight;  // (LF)(LR)----(*RR)(RF)
  private Spark motorFrontRight; // (LF)(LR)----(RR)(*RF)

  private Spark motorRearLeft; // (LF)(*LR)----(RR)(RF)
  private Spark motorFrontLeft; // (*LF)(LR)----(RR)(RF)

  // creando el sistema diferencial para el robot
  private DifferentialDrive mecanismoPrincipal;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // definiendo los controladores de los motores derechos
    motorRearRight = new Spark(SPARK_PORT_RIGHT_REAR);
    motorFrontRight = new Spark(SPARK_PORT_RIGHT_FRONT);

    // definiendo los controladores de los motores izquierdos
    motorRearLeft = new Spark(SPARK_PORT_LEFT_REAR);
    motorFrontLeft = new Spark(SPARK_PORT_LEFT_FRONT);

    // agrupando los motores
    SpeedControllerGroup m_right = new SpeedControllerGroup(motorFrontRight, motorRearRight);
    SpeedControllerGroup m_left = new SpeedControllerGroup(motorFrontLeft, motorRearLeft);
    // controlador principal para el movimiento del robot
    mecanismoPrincipal = new DifferentialDrive(m_left, m_right);
  }

  public void Drive(Joystick m_stick)
  {
    // moviendo todo el tren de manejo
    mecanismoPrincipal.arcadeDrive(-m_stick.getY(), m_stick.getX());
  }

  public void DriveMax(float vel)
  {
    // moviendo todo el tren de manejo
    mecanismoPrincipal.arcadeDrive(-1.0, 1.0);
  }
}
