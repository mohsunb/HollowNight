package dev.pogodemon.utils;

import dev.pogodemon.world.EntityData;
import dev.pogodemon.world.entity_data.*;
import dev.pogodemon.world.entity_data.forgotten_crossroads.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapHelper
{
    public static final int KINGS_PASS = 0;
    public static final int DIRTMOUTH = 1;

    //Forgotten Crossroads
    public static final int CR1 = 2;
    public static final int CR2 = 3;
    public static final int CR3 = 4;
    public static final int CR4 = 5;
    public static final int CR5 = 6;
    public static final int CR6 = 7;
    public static final int CR7 = 8;
    public static final int CR8 = 9;
    public static final int CR9 = 10;
    public static final int CR10 = 11;
    public static final int CR11 = 12;
    public static final int CR12 = 13;
    public static final int CR13 = 14;
    public static final int CR14 = 15;
    public static final int CR15 = 16;
    public static final int CR16 = 17;
    public static final int CR17 = 18;
    public static final int CR18 = 19;
    public static final int CR19 = 20;
    public static final int CR20 = 21;
    public static final int CR21 = 22;
    public static final int CR22 = 23;
    public static final int CR23 = 24;
    public static final int CR24 = 25;
    public static final int CR25 = 26;
    public static final int CR26 = 27;
    public static final int CR27 = 28;
    public static final int CR28 = 29;
    public static final int CR29 = 30;
    public static final int CR30 = 31;
    public static final int CR31 = 32;
    public static final int CR32 = 33;
    public static final int CR33 = 34;
    public static final int CR34 = 35;
    public static final int CR35 = 36;
    public static final int CR36 = 37;
    public static final int CR37 = 38;
    public static final int CR38 = 39;
    public static final int CR39 = 40;
    public static final int CR40 = 41;

    public static Color worldColor(int map_id)
    {
        if (map_id == KINGS_PASS)
            return new Color(89, 96, 126);
        else if (map_id == DIRTMOUTH)
            return new Color(0, 41, 56);
        return new Color(0, 74, 85);
    }

    public static String path(int map_id)
    {
        if (map_id == KINGS_PASS)
            return "/collision_maps/kings_pass.png";
        else if (map_id == DIRTMOUTH)
            return "/collision_maps/dirtmouth.png";
        else if (map_id == CR1)
            return "/collision_maps/crossroads_1.png";
        else if (map_id == CR2)
            return "/collision_maps/crossroads_2.png";
        else if (map_id == CR3)
            return "/collision_maps/crossroads_3.png";
        else if (map_id == CR4)
            return "/collision_maps/crossroads_4.png";
        else if (map_id == CR5)
            return "/collision_maps/crossroads_5.png";
        else if (map_id == CR6)
            return "/collision_maps/crossroads_6.png";
        else if (map_id == CR7)
            return "/collision_maps/crossroads_7.png";
        else if (map_id == CR8)
            return "/collision_maps/crossroads_8.png";
        else if (map_id == CR9)
            return "/collision_maps/crossroads_9.png";
        else if (map_id == CR10)
            return "/collision_maps/crossroads_10.png";
        else if (map_id == CR11)
            return "/collision_maps/crossroads_11.png";
        else if (map_id == CR12)
            return "/collision_maps/crossroads_12.png";
        else if (map_id == CR13)
            return "/collision_maps/crossroads_13.png";
        else if (map_id == CR14)
            return "/collision_maps/crossroads_14.png";
        else if (map_id == CR15)
            return "/collision_maps/crossroads_15.png";
        else if (map_id == CR16)
            return "/collision_maps/crossroads_16.png";
        else if (map_id == CR17)
            return "/collision_maps/crossroads_17.png";
        else if (map_id == CR18)
            return "/collision_maps/crossroads_18.png";
        else if (map_id == CR19)
            return "/collision_maps/crossroads_19.png";
        else if (map_id == CR20)
            return "/collision_maps/crossroads_20.png";
        else if (map_id == CR21)
            return "/collision_maps/crossroads_21.png";
        else if (map_id == CR22)
            return "/collision_maps/crossroads_22.png";
        else if (map_id == CR23)
            return "/collision_maps/crossroads_23.png";
        else if (map_id == CR24)
            return "/collision_maps/crossroads_24.png";
        else if (map_id == CR25)
            return "/collision_maps/crossroads_25.png";
        else if (map_id == CR26)
            return "/collision_maps/crossroads_26.png";
        else if (map_id == CR27)
            return "/collision_maps/crossroads_27.png";
        else if (map_id == CR28)
            return "/collision_maps/crossroads_28.png";
        else if (map_id == CR29)
            return "/collision_maps/crossroads_29.png";
        else if (map_id == CR30)
            return "/collision_maps/crossroads_30.png";
        else if (map_id == CR31)
            return "/collision_maps/crossroads_31.png";
        else if (map_id == CR32)
            return "/collision_maps/crossroads_32.png";
        else if (map_id == CR33)
            return "/collision_maps/crossroads_33.png";
        else if (map_id == CR34)
            return "/collision_maps/crossroads_34.png";
        else if (map_id == CR35)
            return "/collision_maps/crossroads_35.png";
        else if (map_id == CR36)
            return "/collision_maps/crossroads_36.png";
        else if (map_id == CR37)
            return "/collision_maps/crossroads_37.png";
        else if (map_id == CR38)
            return "/collision_maps/crossroads_38.png";
        else if (map_id == CR39)
            return "/collision_maps/crossroads_39.png";
        else if (map_id == CR40)
            return "/collision_maps/crossroads_40.png";

        return null;
    }

    public static Coordinate coordinate(int map_id, int entrance_id)
    {
        if (map_id == KINGS_PASS)
        {
            if (entrance_id == KingsPass.RIGHT)
                return new Coordinate(14528, 1072);
        }

        else if (map_id == DIRTMOUTH)
        {
            if (entrance_id == Dirtmouth.LEFT)
                return new Coordinate(0, 1632);
            else if (entrance_id == Dirtmouth.BOTTOM)
                return new Coordinate(12675, 4348);
        }

        else if (map_id == CR1)
        {
            if (entrance_id == Cr1.TOP)
                return  new Coordinate(3826, 1421);
            else if (entrance_id == Cr1.LEFT)
                return  new Coordinate(0, 2312);
            else if (entrance_id == Cr1.RIGHT)
                return  new Coordinate(7200, 1992);
        }

        else if (map_id == CR2)
        {
            if (entrance_id == Cr2.RIGHT_1)
                return  new Coordinate(3616, 2792);
            else if (entrance_id == Cr2.RIGHT_2)
                return  new Coordinate(3616, 6192);
            else if (entrance_id == Cr2.LEFT_1)
                return  new Coordinate(0, 2792);
            else if (entrance_id == Cr2.LEFT_2)
                return  new Coordinate(0, 6552);
            else if (entrance_id == Cr2.LEFT_3)
                return  new Coordinate(0, 8232);
            else if (entrance_id == Cr2.BOTTOM)
                return  new Coordinate(1561, 9519);
        }

        else if (map_id == CR3)
        {
            if (entrance_id == Cr3.RIGHT)
                return  new Coordinate(5687, 1712);
        }

        else if (map_id == CR4)
        {
            if (entrance_id == Cr4.RIGHT)
                return  new Coordinate(8512, 1152);
        }

        else if (map_id == CR5)
        {
            if (entrance_id == Cr5.RIGHT)
                return  new Coordinate(5942, 1192);
            else if (entrance_id == Cr5.LEFT)
                return  new Coordinate(0, 1232);
        }

        else if (map_id == CR6)
        {
            if (entrance_id == Cr6.RIGHT_1)
                return  new Coordinate(4783, 872);
            else if (entrance_id == Cr6.RIGHT_2)
                return  new Coordinate(4786, 3952);
        }

        else if (map_id == CR7)
        {
            if (entrance_id == Cr7.RIGHT)
                return  new Coordinate(6095, 1792);
            else if (entrance_id == Cr7.LEFT)
                return  new Coordinate(0, 1512);
        }

        else if (map_id == CR8)
        {
            if (entrance_id == Cr8.TOP)
                return  new Coordinate(1852, 0);
            else if (entrance_id == Cr8.RIGHT_1)
                return  new Coordinate(3737, 1072);
            else if (entrance_id == Cr8.RIGHT_2)
                return  new Coordinate(3749, 2832);
            else if (entrance_id == Cr8.LEFT_1)
                return  new Coordinate(76, 912);
            else if (entrance_id == Cr8.LEFT_2)
                return  new Coordinate(0, 2832);
        }

        else if (map_id == CR9)
        {
            if (entrance_id == Cr9.RIGHT)
                return  new Coordinate(5380, 1032);
            else if (entrance_id == Cr9.LEFT)
                return  new Coordinate(0, 912);
        }

        else if (map_id == CR10)
        {
            if (entrance_id == Cr10.RIGHT)
                return  new Coordinate(5425, 592);
            else if (entrance_id == Cr10.BOTTOM)
                return  new Coordinate(4874, 4235);
        }

        else if (map_id == CR11)
        {
            if (entrance_id == Cr11.RIGHT)
                return  new Coordinate(6974, 2192);
            else if (entrance_id == Cr11.LEFT)
                return  new Coordinate(0, 2192);
        }

        else if (map_id == CR12)
        {
            if (entrance_id == Cr12.RIGHT)
                return  new Coordinate(6857, 1552);
            else if (entrance_id == Cr12.LEFT)
                return  new Coordinate(0, 1552);
        }

        else if (map_id == CR13)
        {
            if (entrance_id == Cr13.RIGHT_1)
                return  new Coordinate(2531, 952);
            else if (entrance_id == Cr13.RIGHT_2)
                return  new Coordinate(2531, 3512);
            else if (entrance_id == Cr13.LEFT_1)
                return  new Coordinate(0, 1032);
            else if (entrance_id == Cr13.LEFT_2)
                return  new Coordinate(0, 3152);
        }

        else if (map_id == CR14)
        {
            if (entrance_id == Cr14.LEFT)
                return  new Coordinate(0, 1272);
        }

        else if (map_id == CR15)
        {
            if (entrance_id == Cr15.RIGHT)
                return  new Coordinate(6100, 792);
            else if (entrance_id == Cr15.LEFT)
                return  new Coordinate(0, 3912);
        }

        else if (map_id == CR16)
        {
            if (entrance_id == Cr16.RIGHT)
                return  new Coordinate(6029, 952);
            else if (entrance_id == Cr16.LEFT)
                return  new Coordinate(0, 1312);
            else if (entrance_id == Cr16.BOTTOM)
                return  new Coordinate(4473, 2019);
        }

        else if (map_id == CR17)
        {
            if (entrance_id == Cr17.RIGHT)
                return  new Coordinate(6578, 1192);
            else if (entrance_id == Cr17.LEFT)
                return  new Coordinate(0, 1192);
        }

        else if (map_id == CR18)
        {
            if (entrance_id == Cr18.RIGHT)
                return new Coordinate(5778, 1392);
            else if (entrance_id == Cr18.LEFT)
                return new Coordinate(0, 1472);
        }

        else if (map_id == CR19)
        {
            if (entrance_id == Cr19.LEFT)
                return new Coordinate(0, 4072);
            else if (entrance_id == Cr19.RIGHT)
                return new Coordinate(4823, 4072);
        }

        else if (map_id == CR20)
        {
            if (entrance_id == Cr20.LEFT)
                return new Coordinate(0, 5032);
            else if (entrance_id == Cr20.RIGHT)
                return new Coordinate(5694, 5032);
        }

        else if (map_id == CR21)
        {
            if (entrance_id == Cr21.LEFT)
                return new Coordinate(0, 1952);
            else if (entrance_id == Cr21.TOP)
                return new Coordinate(634, 101);
            else if (entrance_id == Cr21.RIGHT)
                return new Coordinate(8473, 1192);
        }

        else if (map_id == CR22)
        {
            if (entrance_id == Cr22.BOTTOM)
                return new Coordinate(550, 2525);
        }

        else if (map_id == CR23)
        {
            if (entrance_id == Cr23.TOP)
                return new Coordinate(1077, 0);
            else if (entrance_id == Cr23.RIGHT_1)
                return new Coordinate(2217, 992);
            else if (entrance_id == Cr23.RIGHT_2)
                return new Coordinate(2217, 2952);
            else if (entrance_id == Cr23.LEFT_1)
                return new Coordinate(0, 3192);
            else if (entrance_id == Cr23.LEFT_2)
                return new Coordinate(0, 5072);
            else if (entrance_id == Cr23.BOTTOM)
                return new Coordinate(1158, 5601);
        }

        else if (map_id == CR24)
        {
            if (entrance_id == Cr24.RIGHT)
                return new Coordinate(2224, 712);
        }

        else if (map_id == CR25)
        {
            if (entrance_id == Cr25.LEFT)
                return new Coordinate(0, 1032);
            else if (entrance_id == Cr25.RIGHT)
                return new Coordinate(6992, 1112);
        }

        else if (map_id == CR26)
        {
            if (entrance_id == Cr26.LEFT)
                return new Coordinate(0, 1112);
            else if (entrance_id == Cr26.RIGHT)
                return new Coordinate(5950, 1112);
        }

        else if (map_id == CR27)
        {
            if (entrance_id == Cr27.LEFT_1)
                return new Coordinate(0, 432);
            else if (entrance_id == Cr27.LEFT_2)
                return new Coordinate(0, 2472);
            else if (entrance_id == Cr27.RIGHT)
                return new Coordinate(2145, 3792);
            else if (entrance_id == Cr27.BOTTOM)
                return new Coordinate(1061, 5235);
        }

        else if (map_id == CR28)
        {
            if (entrance_id == Cr28.RIGHT)
                return new Coordinate(4782, 1232);
        }

        else if (map_id == CR29)
        {
            if (entrance_id == Cr29.LEFT)
                return new Coordinate(0, 832);
        }

        else if (map_id == CR30)
        {
            if (entrance_id == Cr30.LEFT)
                return new Coordinate(0, 992);
            else if (entrance_id == Cr30.TOP)
                return new Coordinate(6422, 0);
            else if (entrance_id == Cr30.RIGHT)
                return new Coordinate(13729, 352);
        }

        else if (map_id == CR31)
        {
            if (entrance_id == Cr31.TOP)
                return new Coordinate(1293, 0);
            else if (entrance_id == Cr31.LEFT_1)
                return new Coordinate(0, 952);
            else if (entrance_id == Cr31.RIGHT)
                return new Coordinate(3614, 3072);
            else if (entrance_id == Cr31.LEFT_2)
                return new Coordinate(0, 3072);
        }

        else if (map_id == CR32)
        {
            if (entrance_id == Cr32.LEFT)
                return new Coordinate(0, 752);
            else if (entrance_id == Cr32.RIGHT)
                return new Coordinate(5424, 992);
        }

        else if (map_id == CR33)
        {
            if (entrance_id == Cr33.LEFT)
                return new Coordinate(0, 832);
            else if (entrance_id == Cr33.RIGHT)
                return new Coordinate(2015, 832);
        }

        else if (map_id == CR34)
        {
            if (entrance_id == Cr34.RIGHT)
                return new Coordinate(8214, 1952);
        }

        else if (map_id == CR35)
        {
            if (entrance_id == Cr35.LEFT)
                return new Coordinate(0, 1432);
            else if (entrance_id == Cr35.RIGHT)
                return new Coordinate(7986, 1352);
        }

        else if (map_id == CR36)
        {
            if (entrance_id == Cr36.LEFT)
                return new Coordinate(0, 2592);
            if (entrance_id == Cr36.RIGHT)
                return new Coordinate(5341, 2512);
        }

        else if (map_id == CR37)
        {
            if (entrance_id == Cr37.LEFT_1)
                return new Coordinate(0, 1552);
            else if (entrance_id == Cr37.LEFT_2)
                return new Coordinate(0, 2832);
            else if (entrance_id == Cr37.RIGHT_1)
                return new Coordinate(3945, 1512);
            else if (entrance_id == Cr37.RIGHT_2)
                return new Coordinate(3945, 2592);
        }

        else if (map_id == CR38)
        {
            if (entrance_id == Cr38.LEFT)
                return new Coordinate(0, 1032);
        }

        else if (map_id == CR39)
        {
            if (entrance_id == Cr39.RIGHT_1)
                return new Coordinate(3150, 872);
            else if (entrance_id == Cr39.RIGHT_2)
                return new Coordinate(3150, 2992);
            else if (entrance_id == Cr39.BOTTOM)
                return new Coordinate(1621, 3542);
        }

        else if (map_id == CR40)
        {
            if (entrance_id == Cr40.LEFT)
                return new Coordinate(0, 352);
        }

        return null;
    }

    public static EntityData getEntityData(Handler handler, int map_id)
    {
        if (map_id == KINGS_PASS)
            return new KingsPass(handler);
        else if (map_id == DIRTMOUTH)
            return new Dirtmouth(handler);
        else if (map_id == CR1)
            return new Cr1(handler);
        else if (map_id == CR2)
            return new Cr2(handler);
        else if (map_id == CR3)
            return new Cr3(handler);
        else if (map_id == CR4)
            return new Cr4(handler);
        else if (map_id == CR5)
            return new Cr5(handler);
        else if (map_id == CR6)
            return new Cr6(handler);
        else if (map_id == CR7)
            return new Cr7(handler);
        else if (map_id == CR8)
            return new Cr8(handler);
        else if (map_id == CR9)
            return new Cr9(handler);
        else if (map_id == CR10)
            return new Cr10(handler);
        else if (map_id == CR11)
            return new Cr11(handler);
        else if (map_id == CR12)
            return new Cr12(handler);
        else if (map_id == CR13)
            return new Cr13(handler);
        else if (map_id == CR14)
            return new Cr14(handler);
        else if (map_id == CR15)
            return new Cr15(handler);
        else if (map_id == CR16)
            return new Cr16(handler);
        else if (map_id == CR17)
            return new Cr17(handler);
        else if (map_id == CR18)
            return new Cr18(handler);
        else if (map_id == CR19)
            return new Cr19(handler);
        else if (map_id == CR20)
            return new Cr20(handler);
        else if (map_id == CR21)
            return new Cr21(handler);
        else if (map_id == CR22)
            return new Cr22(handler);
        else if (map_id == CR23)
            return new Cr23(handler);
        else if (map_id == CR24)
            return new Cr24(handler);
        else if (map_id == CR25)
            return new Cr25(handler);
        else if (map_id == CR26)
            return new Cr26(handler);
        else if (map_id == CR27)
            return new Cr27(handler);
        else if (map_id == CR28)
            return new Cr28(handler);
        else if (map_id == CR29)
            return new Cr29(handler);
        else if (map_id == CR30)
            return new Cr30(handler);
        else if (map_id == CR31)
            return new Cr31(handler);
        else if (map_id == CR32)
            return new Cr32(handler);
        else if (map_id == CR33)
            return new Cr33(handler);
        else if (map_id == CR34)
            return new Cr34(handler);
        else if (map_id == CR35)
            return new Cr35(handler);
        else if (map_id == CR36)
            return new Cr36(handler);
        else if (map_id == CR37)
            return new Cr37(handler);
        else if (map_id == CR38)
            return new Cr38(handler);
        else if (map_id == CR39)
            return new Cr39(handler);
        else if (map_id == CR40)
            return new Cr40(handler);

        return null;
    }
}
