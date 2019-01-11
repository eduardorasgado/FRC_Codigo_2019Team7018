/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include <frc/WPILib.h>
//#include <frc/Joystick.h>
//#include <IterativeRobot.h>
//#include <frc/Timer.h>
//#include <SmartDashboard/SmartDashboard.h>

/**
 * This sample program provides an example for ShuffleBoard, an alternative
 * to SmartDashboard for displaying values and properties of different robot
 * parts.
 *
 * ShuffleBoard can use pre-programmed widgets to display various values, such
 * as Boolean Boxes, Sliders, Graphs, and more. In addition, they can display
 * things in various Tabs.
 *
 * For more information on how to create personal layouts and more in
 * ShuffleBoard, feel free to reference the official FIRST WPILib documentation
 * online.
 */
#pragma once

class Robot : public frc::IterativeRobot {
 public:

  void AutonomousInit() override {
    // Update the Max Output for the drivetrain.
  }
  void AutonomousPeriodic() override{}

  void TeleopInit() override{}

  void TeleopPeriodic() override {

  
      frc::SmartDashboard::PutNumber("Joystick X valor", m_stick.GetX());
      frc::SmartDashboard::PutNumber("JoyStick Y valor", m_stick.GetY());
      frc::Wait(0.10);
  }

 private:
  frc::Joystick m_stick{0};
  //nt::NetworkTableEntry m_maxSpeed;
};

