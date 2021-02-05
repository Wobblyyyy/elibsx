package org.roxbotix.elibs2.robot.components;

public interface EncoderCore {
    int getCount();
    void setCountOffset(int offset);
    int getCountOffset();
    void zeroCount();
}
