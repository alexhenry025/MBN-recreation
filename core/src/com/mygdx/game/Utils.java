package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

public class Utils {
    private ArrayList<Sound> Sound = new ArrayList<Sound>();

    Sound sound;
    private int index;

    public Utils(){
        File_Reader();
    }

    public void File_Reader(){
        FileHandle file = Gdx.files.internal("Assets/Sound.txt"); // this can be found in the assets folder
        String [] text = file.readString().split("\n");
        for(int i = 0; i < text.length; i ++){
            sound = Gdx.audio.newSound(Gdx.files.internal(text[i]));
            Sound.add(sound);
        }
    }


    public ArrayList<Sound> getSound(){
        return Sound;
    }
}
