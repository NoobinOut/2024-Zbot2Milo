// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    // Driving Parameters - Note that these are not the maximum capable speeds of
    // the robot, rather the allowed maximum speeds
    public static final double kMaxSpeedMetersPerSecond = 4.8;
    public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second

    public static final double kDirectionSlewRate = 1.2; // radians per second
    public static final double kMagnitudeSlewRate = 1.8; // percent per second (1 = 100%)
    public static final double kRotationalSlewRate = 2.0; // percent per second (1 = 100%)

    // Chassis configuration
    public static final double kTrackWidth = Units.inchesToMeters(23.5);
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = Units.inchesToMeters(23.5);
    // Distance between front and back wheels on robot
    public static final SwerveDriveKinematics kDriveKinematics =
        new SwerveDriveKinematics(
            new Translation2d(kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    // Angular offsets of the modules relative to the chassis in radians
    public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
    public static final double kFrontRightChassisAngularOffset = 0;
    public static final double kBackLeftChassisAngularOffset = Math.PI;
    public static final double kBackRightChassisAngularOffset = Math.PI / 2;

    // SPARK MAX CAN IDs
    public static final int kFrontLeftDrivingCanId = 7;
    public static final int kRearLeftDrivingCanId = 4;
    public static final int kFrontRightDrivingCanId = 8;
    public static final int kRearRightDrivingCanId = 2;

    public static final int kFrontLeftTurningCanId = 5;
    public static final int kRearLeftTurningCanId = 1;
    public static final int kFrontRightTurningCanId = 3;
    public static final int kRearRightTurningCanId = 6;

    public static final boolean kGyroReversed = false;
  }

  public static final class ModuleConstants {
    // The MAXSwerve module can be configured with one of three pinion gears: 12T, 13T, or 14T.
    // This changes the drive speed of the module (a pinion gear with more teeth will result in a
    // robot that drives faster).
    public static final int kDrivingMotorPinionTeeth = 13;

    // Invert the turning encoder, since the output shaft rotates in the opposite direction of
    // the steering motor in the MAXSwerve Module.
    public static final boolean kTurningEncoderInverted = true;

    // Calculations required for driving motor conversion factors and feed forward
    public static final double kDrivingMotorFreeSpeedRps = NeoMotorConstants.kFreeSpeedRpm / 60;
    public static final double kWheelDiameterMeters = 0.0762;
    public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
    // 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15 teeth on the
    // bevel pinion
    public static final double kDrivingMotorReduction =
        (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);
    public static final double kDriveWheelFreeSpeedRps =
        (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters) / kDrivingMotorReduction;

    public static final double kDrivingEncoderPositionFactor =
        (kWheelDiameterMeters * Math.PI) / kDrivingMotorReduction; // meters
    public static final double kDrivingEncoderVelocityFactor =
        ((kWheelDiameterMeters * Math.PI) / kDrivingMotorReduction) / 60.0; // meters per second

    public static final double kTurningEncoderPositionFactor = (2 * Math.PI); // radians
    public static final double kTurningEncoderVelocityFactor =
        (2 * Math.PI) / 60.0; // radians per second

    public static final double kTurningEncoderPositionPIDMinInput = 0; // radians
    public static final double kTurningEncoderPositionPIDMaxInput =
        kTurningEncoderPositionFactor; // radians

    public static final double kDrivingP = 0.04;
    public static final double kDrivingI = 0;
    public static final double kDrivingD = 0;
    public static final double kDrivingFF = 1 / kDriveWheelFreeSpeedRps;
    public static final double kDrivingMinOutput = -1;
    public static final double kDrivingMaxOutput = 1;

    public static final double kTurningP = 1;
    public static final double kTurningI = 0;
    public static final double kTurningD = 0;
    public static final double kTurningFF = 0;
    public static final double kTurningMinOutput = -1;
    public static final double kTurningMaxOutput = 1;

    public static final IdleMode kDrivingMotorIdleMode = IdleMode.kBrake;
    public static final IdleMode kTurningMotorIdleMode = IdleMode.kBrake;

    public static final int kDrivingMotorCurrentLimit = 50; // amps
    public static final int kTurningMotorCurrentLimit = 20; // amps
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final double kDriveDeadband = 0.15;
  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 1.5;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 1;

    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
        new TrapezoidProfile.Constraints(
            kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }

  public static final class NeoMotorConstants {
    public static final double kFreeSpeedRpm = 5676;
  }

  public static final class VisionConstants {

    public static final int kCameraCenterX = 285;
    public static final double kDecelerationDistance = 9;
    public static final int kTotalAprilTags = 12;

    public static final double kCameraFOV = 68.5;

    public static final float kCameraMaxWidth = 650;
    public static final float kCameraMaxHieght = 570;
    public static final double kNeededPos[] = {kCameraMaxWidth / 2, kCameraMaxHieght / 2};
    public static final double kCenterOfScreen[] = {kCameraMaxWidth / 2, kCameraMaxHieght / 2};
    public static final double kDriveAimErrorRange = 10; // In pixels
  }

  public static final class AxleConstants {
    public static final int kMasterAxleMotorPort = 9;
    public static final int kMinionAxleMotorPort = 10;
    public static final int kTopLimitSwitchPort = 5;

    public static final double kDefaultHeight = .003;
    public static final double kAmpHeight = .276;
    public static final double kIntakeHeight = .003;
    public static final double kBasicSpeakerAimHeight = .003;
    public static double kMeasuredPosHorizontal = 0.023;
    public static double kAxleTestSpeed = .2;

    public static final double kManualAimSpeed = 0.1;

    public static final double kTestHeight = 0.0;

    public static double kTestRadiansNeeded = Math.PI / 2;
  }

  public static final class ShooterConstants {
    public static final int kMasterShooterMotorPort = 11;
    public static final int kMinionShooterMotorPort = 12;

    public static final double kShootSpeakerSpeed = 0.5;
    public static final double kShootAmpSpeed = .2;
    public static final double kReverseIndexSpeed = -.2;

    public static final double kTestVelocity = 100.0;
  }

  public static final class UltrasonicConstants {
    // public static final int kUltrasonicTriggerPort = 2;
    // public static final int kUltrasonicSensorPort = 3;
  }

  public static final class LEDConstants {
    public static final int kLEDPort = 8;
  }

  public static final class IntakeConstants {
    public static final int kMasterIntakeMotorPort = 13;

    public static final double kIntakeMotorSpeed = -1.0;
    public static final double kIndexSpeed = -1.0;
    public static final double kIndexSpeedSlow = -0.1;
    public static final double kIndexReverseSpeed = .2;

    public static final int kIntakeSensorPort = 0;
    public static final int kIntakeOutputPort = 1;
    public static final int kNoteDetectedLEDPort = 2;
    public static final int kNoteReadyLEDPort = 3;

    // May need to swap this value after testing the sensor
    public static final boolean kIntakeSensorNoteDetected = false;
    public static final boolean kOutputSensorNoteDetected = false;
  }

  public static final class climbConstants {
    public static final int kClimbMotorPort = 14;

    public static final double kClimbMotorPortSpeed = 0.1;

    public static final double kClimbDefaultHeight = 0;
  }

  public static final class AutoAimConstants {
    public static final double kLaunchStartingHeight =
        0.35; // In meters; height of note launch point; ***SUBJECT TO CHANGE***
    public static final double kLaunchToCameraDifference =
        0.2; // In meters; distance to ADD to camera distance (positive if launcher is behind
    // camera); ***SUBJECT TO CHANGE***
    public static final double kTargetX =
        0; // In meters; X value of speaker relative to origin (originX = speakerX)
    public static final double kTargetY =
        2.0; // In meters; height of speaker target; ***SUBJECT TO CHANGE***
    public static final double kLaunchVelocity =
        7.0; // In meters/second; velocity of note shooting out; ***SUBJECT TO CHANGE***
    public static final double kgravitationalConstant =
        9.80665; // In meters/second/second (acceleration); standard gravity constant

    public static final int kMaxIterations =
        100; // Number of iterations before giving up on angle solving
    public static final double kRangeForAimAngle =
        0.01; // Required accuracy for aim angle brute force calculation
    public static final double kRangeForMax =
        0.01; // Required accuracy for angle of max brute force calculation

    public static final double kTagToSpeakerDistance = 0.25; // in meters; ***SUBJECT TO CHANGE***

    public static final double kDriveRotationPower = 0.1;

    public static final double kMaxPhysicalAngleDegrees = 50; // in degrees (if that wasn't obvious)
    public static final double kPhysicalShooterAngleOffsetDegrees =
        20; // in degrees, ADDED to the axle angle for shooter

    public static final double kShooterAimErrorRangeDegrees = 5; // In degrees
  }
}
