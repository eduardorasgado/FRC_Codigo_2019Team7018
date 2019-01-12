/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

// Libreria para los victor spx
import edu.wpi.first.wpilibj.VictorSP;

/**
 * Add your docs here.
 */
public class BallSucker extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private double STOP_MOTOR = 0.0;

  //Puerto para los victor del ball Sucker
  private final int MECANNO_MOTOR_PORT = 7;
  private final int ARM_MOTOR_PORT = 8;

  // los dos motores del Ball Sucker
  private VictorSP MecSuckerMotor;
  private VictorSP ArmSuckerMotor;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    // 2) Sucker
    MecSuckerMotor = new VictorSP(MECANNO_MOTOR_PORT);
    ArmSuckerMotor = new VictorSP(ARM_MOTOR_PORT);
  }

  public void DrivePressed(double MAX_POWER_MOTOR, double MIN_POWER_MOTOR)
  {
    // cuando se presiona el boton del ball sucker
    MecSuckerMotor.set(MAX_POWER_MOTOR);
    ArmSuckerMotor.set(MIN_POWER_MOTOR);
  }

  public void DriveRelease()
  {
    MecSuckerMotor.set(STOP_MOTOR);
    ArmSuckerMotor.set(STOP_MOTOR);
  }
}
