/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotMap;

public class CargoHandler extends Subsystem {
  
  private Spark canonUpperMotor; // motor arriba
  private Spark canonBottomMotor; // moyot de abajo
  // definiendo el motor para el canon arm
  private Spark canonArmMotor;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    canonUpperMotor = new Spark(RobotMap.MotorCanonUpper);
    canonBottomMotor = new Spark(RobotMap.MotorCanonBottom);
    canonArmMotor = new Spark(RobotMap.MotorCanonArm);
  }

  public void launchCargo(double speed){
    // mecanismo de sustraccion
    // baja el brazo y succiona
    moveCargoUpperMotor(true, speed);
    moveCargoBottomMotor(false);
  }

  public void homePosition(){
    // se puede mandar a llamar despues de soltar
    // el boton de succion, para el motor
  }

  public void takeCargo(){
    // mecanismo de lanzamiento
    // ubica el brazo y lanza
    moveCargoUpperMotor(false,1.0);
    moveCargoBottomMotor(true);
  }

  public void stopGripper(){
    // para los motores upper y bottom
    stopCargoUpperMotor();
    stopCargoBottomMotor();
  }

  public void moveCargoUpperMotor(boolean negative,
        double speed){
    // toma una velocidad y activa el motor superior
    if(negative){
      canonUpperMotor.set(-speed);
    } else {
      canonUpperMotor.set(speed);
    }
  }

  public void moveCargoBottomMotor(boolean negative){
    // toma una velocidad y activa el motor inferior
    if(negative){
      canonBottomMotor.set(-RobotMap.MAX_MOTOR_POWER);
    }
    else{
      canonBottomMotor.set(RobotMap.MAX_MOTOR_POWER);
    }
  }

  public void moverArmMotor(boolean negative){
    // mover el brazo
    if(negative){
      // el brazo baja
      canonArmMotor.set(RobotMap.AVERAGE_MOTOR_POWER);
    } else {
      canonArmMotor.set(RobotMap.MAX_MOTOR_POWER);
    }
  }

  public void stopCargoUpperMotor(){
    // para los motores del sucker/launcher superior
    canonUpperMotor.set(RobotMap.STOP_MOTOR);
  }
  public void stopCargoBottomMotor(){
    // para el motor del sucker/launcher inferior
    canonBottomMotor.set(RobotMap.STOP_MOTOR);
  }
  public void stopArmMotor(){
    // para el motor del brazo
    canonArmMotor.set(RobotMap.STOP_MOTOR);
  }
}
