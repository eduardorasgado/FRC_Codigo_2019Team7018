/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchPanelHandler extends Subsystem {
  
  // el par de valvulas en el solenoide
  private DoubleSolenoid solenoidPair = null;

  @Override
  public void initDefaultCommand() {
    // asignando cada puerto del pcm a el par solenoide
    solenoidPair = new DoubleSolenoid(RobotMap.solenoidRight,
                    RobotMap.solenoidLeft);
  }
  public void explodeHatch(){
    // expulsar el hatch panel
    solenoidPair.set(Value.kForward);
  }

  public void implodeHatch(){
    // contraer el sistema
    solenoidPair.set(Value.kReverse);
  }

  public void stopSolenoid(){
    //
    solenoidPair.set(Value.kOff);
  }
}
