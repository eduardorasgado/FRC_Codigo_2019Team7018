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
public class BallLauncher extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final double STOP_MOTOR = 0.0;

  // Puertos para los victor del ball Launcher
  private final int VICTOR_SP1_PORT = 4;
  private final int VICTOR_SP2_PORT = 5;

  // los dos motores del Ball Launcher
  private VictorSP MLauncher1;
  private VictorSP MLauncher2;

  @Override
  public void initDefaultCommand() {
    // 1) Launcher
    MLauncher1 = new VictorSP(VICTOR_SP1_PORT);
    MLauncher2 = new VictorSP(VICTOR_SP2_PORT);
  }

  public void DrivePressed(double MAX_POWER_MOTOR, double MAX_POWER_MOTOR_NEGATIVE)
  {
    MLauncher1.set(MAX_POWER_MOTOR);
    MLauncher2.set(MAX_POWER_MOTOR_NEGATIVE);
  }

  public void DriveRelease()
  {
    MLauncher1.set(STOP_MOTOR);
    MLauncher2.set(STOP_MOTOR);
  }
}
