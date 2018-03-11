package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Andrei on 11/22/2017.
 */

//@Disabled
@TeleOp(name="Basic: Andrei's Linear OpMode", group="Linear Opmode")

public class TeleOpTryOut extends LinearOpMode
{
    private DcMotor motor1 = null;//motoare stanga fata si spate
    private DcMotor motor2 = null;
    private DcMotor motor3 = null;//motare dreapta fata si spate
    private DcMotor motor4 = null;
    private DcMotor motor_cub_rid = null;//motor pentru ridicare cuburi
    private DcMotor motor_cub_flip = null;//motor pentru flip brate cuburi
    private Servo armss = null;//brat prindere stanga sus
    private Servo armsj = null;//brat prindere stanga jos
    private Servo armds = null;//brat prindere dreapta sus
    private Servo armdj = null;//brat prindere dreapta jos

    private double viteza = 1;
    private boolean apas_rid = false, rid_state = false, apas_flip = false, flip_state = false;// (adevarat/fals)
    private boolean grabs = false, grabj = false, apas_lb = false;

    public void MoveL()//Miscarea robotului fata spate stanga dreapta, fiecare joystick controleaza 2 motoare
    {
        if (gamepad1.left_stick_y > 0.5)
        {
            motor1.setPower(viteza);
            motor2.setPower(viteza);

            motor3.setPower(-viteza);
            motor4.setPower(-viteza);
        }
        else if(gamepad1.left_stick_y < -0.5)
        {
            motor1.setPower(-viteza);
            motor2.setPower(-viteza);

            motor3.setPower(viteza);
            motor4.setPower(viteza);
        }
        else if(gamepad1.left_stick_x > 0.5)
        {
            motor1.setPower(-viteza);
            motor2.setPower(viteza);

            motor3.setPower(-viteza);
            motor4.setPower(viteza);
        }
        else if(gamepad1.left_stick_x < -0.5)
        {
            motor1.setPower(viteza);
            motor2.setPower(-viteza);

            motor3.setPower(viteza);
            motor4.setPower(-viteza);
        }
        else if(gamepad1.right_stick_x <= 0.4)
        {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
        }
    }

    public void MoveR()
    {
        if(gamepad1.right_stick_x > 0.5)
        {
            motor1.setPower(viteza);
            motor2.setPower(viteza);
            motor3.setPower(viteza);
            motor4.setPower(viteza);
        }
        else if(gamepad1.right_stick_x < -0.5)
        {
            motor1.setPower(-viteza);
            motor2.setPower(-viteza);
            motor3.setPower(-viteza);
            motor4.setPower(-viteza);
        }
    }

    public void MotCub()//Miscarea motoarelor pentru ridicarea si fliparea cuburilor
    {
        //Lift
        if(!gamepad1.a)
            apas_rid = false;
        if(gamepad1.a && !apas_rid)
        {
            apas_rid = true;
            if(!rid_state)
            {
                rid_state = true;
                motor_cub_rid.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor_cub_rid.setTargetPosition(10000);
                motor_cub_rid.setPower(1);
            }
            else
            {
                rid_state = false;
                motor_cub_rid.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor_cub_rid.setTargetPosition(0);
                motor_cub_rid.setPower(-1);
            }
        }

        //Flip
        if(!gamepad1.b)
            apas_flip = false;
        if(gamepad1.b && !apas_flip)
        {
            apas_flip = true;
            if(motor_cub_rid.getCurrentPosition() > 2500)
            {
                if(!flip_state)
                {
                    flip_state = true;
                    motor_cub_flip.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor_cub_flip.setTargetPosition(550);
                    motor_cub_flip.setPower(0.5);
                }
                else
                {
                    flip_state = false;
                    motor_cub_flip.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor_cub_flip.setTargetPosition(0);
                    motor_cub_flip.setPower(0.5);
                }
            }
        }
    }

    public  void Arms()//Miscarea servo-motoarelor care prind cuburile
    {
        if(!flip_state)
        {
            if(!gamepad1.left_bumper)
                apas_lb = false;

            if(gamepad1.left_bumper && !apas_lb)
            {
                apas_lb = true;
                if(!grabj)
                {
                    grabj = true;
                    armsj.setPosition(0);
                    armdj.setPosition(1);
                }
                else
                {
                    grabj = false;
                    armsj.setPosition(0.5);
                    armdj.setPosition(0.5);
                }
            }
        }
        else
        {
            if(!gamepad1.left_bumper)
                apas_lb = false;

            if(gamepad1.left_bumper && !apas_lb)
            {
                apas_lb = true;
                if(!grabs)
                {
                    grabs = true;
                    armss.setPosition(1);
                    armds.setPosition(0);
                }
                else
                {
                    grabs = false;
                    armss.setPosition(0.5);
                    armds.setPosition(0.5);
                }
            }
        }
    }

//Controlarea servo-urilor care prind cuburile
    public void Relicva()
    {

    }

/*
  Legenda controale:
   Joystick stanga -  miscare fata/spate + strafe
   Joystick dreapta - miscare rotatie
   DPad Sus si DPad Jos -
   DPad Stanga si DPad Dreapta -
   Buton a - Ridicare/Coborare brat cub

   Main(). Aici se initializeaza fiecare componenta si se activeaza subprogramele necesare
*/
    @Override
    public void runOpMode() throws InterruptedException
    {
        motor1 = hardwareMap.get(DcMotor.class, "motorleftfront");
        motor2 = hardwareMap.get(DcMotor.class, "motorleftback");
        motor3 = hardwareMap.get(DcMotor.class, "motorrightfront");
        motor4 = hardwareMap.get(DcMotor.class, "motorrightback");
        motor_cub_rid = hardwareMap.get(DcMotor.class, "motorridicare");
        motor_cub_flip = hardwareMap.get(DcMotor.class, "motorflip");
        armss = hardwareMap.get(Servo.class, "stangasus");
        armds = hardwareMap.get(Servo.class, "dreaptasus");
        armsj = hardwareMap.get(Servo.class, "stangajos");
        armdj = hardwareMap.get(Servo.class, "dreaptajos");
        telemetry.addData("Status:", "Initialized");

        armss.setPosition(0.5);
        armsj.setPosition(0.5);
        armds.setPosition(0.5);
        armdj.setPosition(0.5);

        motor_cub_flip.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor_cub_rid.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.update();
        waitForStart();

        while(opModeIsActive())
        {
            MoveR();
            MoveL();
            MotCub();
            Arms();
        }
        telemetry.addData("Status:" , "Terminated");
        telemetry.update();
    }
}
