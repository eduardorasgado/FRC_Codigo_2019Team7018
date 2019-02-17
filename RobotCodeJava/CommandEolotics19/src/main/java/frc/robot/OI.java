/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;
import frc.robot.subsystems.DifferentialDriveTrain;
import frc.robot.subsystems.CargoHandler;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  // definimos una instancia de joystick
  public Joystick mStick;
  // sistema principal
  private DifferentialDriveTrain mainDrive;
  private CargoHandler cargoHandler;

  public OI(){
    // constructor
    mStick = new Joystick(RobotMap.ACTUAL_USB_PORT);

    mainDrive = new DifferentialDriveTrain();
    cargoHandler = new CargoHandler();
  }
  
  public double getSliderData(){
    return mStick.getRawAxis(RobotMap.SLIDER_AXIS_PORT);
  }

  public boolean canonLauncherPressed(){
    return mStick.getRawButtonPressed(RobotMap.LAUNCHER_BUTTON_NUMBER);
  }

  public boolean canonSuckerPressed(){
    return mStick.getRawButtonPressed(RobotMap.SUCKER_BUTTON_NUMBER);
  }

  public boolean button_3_Pressed(){
    return mStick.getRawButtonPressed(RobotMap.BUTTON_PORT_3);
  }

  public boolean button_5_Pressed(){
    return mStick.getRawButtonPressed(RobotMap.BUTTON_PORT_5);
  }

  public double getZAxis(){
    // conseguir datos del slider
    return mStick.getRawAxis(RobotMap.SLIDER_AXIS_PORT);
  }

  public double getSpeed(){
    double sliderData = getSliderData();

    // los datos de la slider vienen de -1 a 1
    if(sliderData < 0)
    {
      // convirtiendolo a positivo
      sliderData = (1.0 + sliderData) * -1;
    }
    else{
      // 1 + 0....
      sliderData = 1.0 + sliderData;
    }
    // mapeando el valor para poder pasarlo como velocidad al motor launcher
    sliderData = sliderData / 2.0;
    
    return sliderData;
  }

  public void mainEventLoop(){
    // se toman las lecturas de todos los botones para ejecutar
    // las acciones
    double sliderData = getSpeed();

    mainDrive.Drive(mStick.getY(), mStick.getX());

    double z_axis = getZAxis();

    if(canonLauncherPressed()){
      cargoHandler.launchCargo(sliderData);
    } else {
      cargoHandler.stopGripper();
    }
    if(canonSuckerPressed()){
      cargoHandler.takeCargo();
    } else {
      cargoHandler.stopGripper();
    }
    if(button_3_Pressed()){
      boolean neg;
      if(z_axis > 0){
        // mover el sistema de brazo del cargo handler
        neg = false;
        cargoHandler.moverArmMotor(neg);
      } else {
        neg = true;
        cargoHandler.moverArmMotor(neg);
      }
    } else {
      cargoHandler.stopArmMotor();
    }
  }
}
