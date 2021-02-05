package org.roxbotix.elibs2.op.template;

import edu.wpi.first.wpilibj.TimedRobot;

import java.util.ArrayList;

public class Template extends TimedRobot {
    private ArrayList<Runnable> fOnStart;
    private ArrayList<Runnable> fOnFinish;

    private ArrayList<Runnable> fOnPacket;
    private ArrayList<Runnable> fPacket;
    private ArrayList<Runnable> fOffPacket;
}
