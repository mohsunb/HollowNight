package dev.pogodemon.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveData
{
    private ArrayList<String> list = new ArrayList<String>();
    private final String path;

    public SaveData(String path)
    {
        this.path = path;
        File file = new File(path);

        if (file.isFile())
        {
            try (Scanner scan = new Scanner(file))
            {
                while (scan.hasNextLine())
                {
                    String s = scan.nextLine();
                    if (s.charAt(0) != '[') // Ignore the lines that don't start with [ sign
                        continue;
                    list.add(s);
                }
            }

            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        else
        {
            //Defaults
            if (path.endsWith("dat"))
            {
                list.add("[geo]=0");
                list.add("[masks]=5");
                list.add("[has_shade]=false");
                list.add("[shade_room_id]=0");
                list.add("[shade_geo]=0");
                list.add("[shade_spawn_x]=0");
                list.add("[shade_spawn_y]=0");
                list.add("[load_state]=0");
                list.add("[load_map_id]=0");
                list.add("[load_coordinates_x]=3100");
                list.add("[load_coordinates_y]=-1000");
                list.add("[respawn_state]=2");
                list.add("[respawn_map_id]=0");
                list.add("[respawn_coordinates_x]=3149");
                list.add("[respawn_coordinates_y]=4752");
                list.add("[dirtmouth_entrance_opened]=false");
                list.add("[secret_1]=false");
                list.add("[secret_2]=false");
                list.add("[secret_3]=false");
                list.add("[secret_4]=false");
                list.add("[secret_5]=false");
                list.add("[secret_6]=false");
                list.add("[secret_7]=false");
                list.add("[secret_8]=false");
                list.add("[secret_9]=false");
                list.add("[secret_10]=false");
                list.add("[secret_11]=false");
                list.add("[secret_12]=false");
                list.add("[rock_1]=5");
                list.add("[rock_2]=5");
                list.add("[rock_3]=5");
                list.add("[rock_4]=5");
                list.add("[rock_5]=5");
                list.add("[rock_6]=5");
                list.add("[rock_7]=5");
                list.add("[rock_8]=5");
                list.add("[rock_9]=5");
                list.add("[rock_10]=5");
                list.add("[rock_11]=5");
                list.add("[rock_12]=5");
                list.add("[rock_13]=5");
                list.add("[rock_14]=5");
                list.add("[rock_15]=5");
                list.add("[rock_16]=5");
                list.add("[rock_17]=5");
                list.add("[rock_18]=5");
                list.add("[rock_19]=5");
                list.add("[rock_20]=5");
                list.add("[rock_21]=5");
                list.add("[rock_22]=5");
                list.add("[rock_23]=5");
                list.add("[rock_24]=5");
                list.add("[rock_25]=5");
                list.add("[rock_26]=5");
                list.add("[rock_27]=5");
                list.add("[rock_28]=5");
                list.add("[rock_29]=5");
                list.add("[chest_1]=false");
                list.add("[chest_2]=false");
            }

            else if (path.endsWith("cfg"))
            {
                list.add("[fullscreen]=true");
                list.add("[scaling]=1.0");
                list.add("[fps_cap]=144");
                list.add("[fps_counter]=false");
                list.add("[hitboxes]=false");
                list.add("[room_indicator]=false");
                list.add("[coordinates]=false");
            }
        }
    }

    public void updateLocalFile()
    {
        try
        {
            Files.writeString(Path.of(path), getRawData());
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updateValue(String var, float value)
    {
        ArrayList<String> temp = new ArrayList<String>(list);
        int i;
        for (String s : list)
        {
            if (s.substring(1, s.indexOf(']')).equals(var))
            {
                i = temp.indexOf(s);
                temp.remove(s);
                temp.add(i, "[" + var + "]" + "=" + value);
                break;
            }
        }
        list = temp;
    }

    public void updateValue(String var, int value)
    {
        ArrayList<String> temp = new ArrayList<String>(list);
        int i;
        for (String s : list)
        {
            if (s.substring(1, s.indexOf(']')).equals(var))
            {
                i = temp.indexOf(s);
                temp.remove(s);
                temp.add(i, "[" + var + "]" + "=" + value);
                break;
            }
        }
        list = temp;
    }

    public void updateValue(String var, boolean value)
    {
        ArrayList<String> temp = new ArrayList<String>(list);
        int i;
        for (String s : list)
        {
            if (s.substring(1, s.indexOf(']')).equals(var))
            {
                i = temp.indexOf(s);
                temp.remove(s);
                temp.add(i, "[" + var + "]" + "=" + value);
                break;
            }
        }
        list = temp;
    }

    public ArrayList<String> getSaveData()
    {
        return list;
    }

    public String getRawData()
    {
        StringBuilder s = new StringBuilder();

        for (String st : getSaveData())
            s.append(st).append("\n");

        s = new StringBuilder(s.substring(0, s.length()));

        return s.toString();
    }

    public boolean getBoolean(String var)
    {
        boolean bool = false;

        try
        {
            for (String s : list)
            {
                if (s.substring(1, s.indexOf(']')).equals(var))
                    bool = Boolean.parseBoolean(s.substring(s.indexOf('=') + 1));
            }
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return bool;
    }

    public float getValue(String var)
    {
        float f = 0;

        try
        {
            for (String s : list)
            {
                if (s.substring(1, s.indexOf(']')).equals(var))
                    f = Float.parseFloat(s.substring(s.indexOf('=') + 1));
            }
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return f;
    }
}
