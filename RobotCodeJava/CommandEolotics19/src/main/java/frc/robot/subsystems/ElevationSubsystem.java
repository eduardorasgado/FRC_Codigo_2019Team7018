/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ElevationSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Spark actuatorFront;
  private Spark actuatorRear;

  @Override
  public void initDefaultCommand() {
    actuatorFront = new Spark(RobotMap.ActuatorFront);
    actuatorRear = new Spark(RobotMap.ActuatorRear);
  }

  public void StartFrontForward(){
    //
    actuatorFront.set(RobotMap.MAX_MOTOR_POWER);
  }

  public void StartFrontBackwards(){
    //
    actuatorFront.set(-RobotMap.MAX_MOTOR_POWER);
  }

  public void StopFront(){
    //
    actuatorFront.set(RobotMap.STOP_MOTOR);
  }

  public void StartRearForward(){
    //
    actuatorRear.set(RobotMap.MAX_MOTOR_POWER);
  }

  public void StartRearBackwards(){
    //
    actuatorRear.set(-RobotMap.MAX_MOTOR_POWER);
  }

  public void StopRear(){
    //
    actuatorRear.set(RobotMap.STOP_MOTOR);
  }
}
