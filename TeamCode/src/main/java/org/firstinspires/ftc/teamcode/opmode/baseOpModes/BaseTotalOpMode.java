package org.firstinspires.ftc.teamcode.opmode.baseOpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.subsystem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BaseTotalOpMode extends CommandOpMode {
    protected MotorEx fL, fR, bL, bR;
    protected DcMotor slideLeft, slideRight;
    protected ServoEx claw;
    protected DriveSubsystem drive;
    protected ArmSubsystem arm;




    @Override
    public void initialize() {
        initHardware();
        setUpHardwareDevices();
        drive = new DriveSubsystem(fL, fR, bL, bR);
        arm = new ArmSubsystem(claw, slideLeft, slideRight);


        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addData("Mode", "Done initializing");
        telemetry.update();
    }

    protected void initHardware() {
        fL = new MotorEx(hardwareMap, "leftFront");
        fR = new MotorEx(hardwareMap, "rightFront");
        bL = new MotorEx(hardwareMap, "leftBack");
        bR = new MotorEx(hardwareMap, "rightBack");

        slideLeft = hardwareMap.dcMotor.get("slideL");
        slideRight = hardwareMap.dcMotor.get("slideR");

        //TODO find min and max
        claw = new SimpleServo(hardwareMap, "claw", 0, 120);

    }
    protected void setUpHardwareDevices() {
        //TODO MAKE SURE CORRECT MOTORS ARE REVERSED
        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);

        //Use this to fix stuff
        fL.setInverted(true);
        fR.setInverted(false);
        bL.setInverted(false);
        bR.setInverted(false);


    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("leftFront Power", round(fL.motor.getPower()));
        telemetry.addData("leftBack Power", round(bL.motor.getPower()));
        telemetry.addData("rightFront Power", round(fR.motor.getPower()));
        telemetry.addData("rightBack Power", round(bR.motor.getPower()));

        telemetry.addData("claw Position", claw.getPosition());

        telemetry.addData("slideLeft Pos", slideLeft.getCurrentPosition());
        telemetry.addData("slideLeft Pos", slideLeft.getCurrentPosition());


        telemetry.update();
    }

    private static double round(double value, @SuppressWarnings("SameParameterValue") int places) {
        if (places < 0) throw new IllegalArgumentException();

        return new BigDecimal(Double.toString(value)).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

    private static double round(double value) {
        return round(value, 4);
    }


}