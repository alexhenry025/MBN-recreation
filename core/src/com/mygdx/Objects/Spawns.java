/*
 * This is a class that gets all the spawn locations from a text file, this is because getting from tiled caused issues
 * Might be destroyed in the near future if a better solution arises during project optimization upon completion
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.Objects;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;

public class Spawns {
    private ArrayList<Integer> Spawns_x = new ArrayList<Integer>(); // create an object array that holds all the spawn location x coordinates
    private ArrayList<Integer> Spawns_y = new ArrayList<Integer>(); // create an object Array that holds all the spawn location y coordinates

    public Spawns(){
        FileReader(); // call file reader to load in values
    }


    private void FileReader(){
        FileHandle file = Gdx.files.internal("Assets/SpawnBoxes.dat"); // this can be found in the assets folder
        String [] text = file.readString().split("\n");
        for(int i = 0; i < text.length; i ++){
            String [] text1 = text[i].split(", ");
            for(int j = 0; j < text1.length; j ++){
                if(j % 2 == 0) {
                    Spawns_x.add(Integer.parseInt(text1[j].trim()));
                }
                else{
                    Spawns_y.add(Integer.parseInt(text1[j].trim()));
                }
            }
        }
    }

    public ArrayList<Integer> getSpawns_x(){
        return Spawns_x;
    }

    public ArrayList<Integer> getSpawns_y(){
        return Spawns_y;
    }

}
