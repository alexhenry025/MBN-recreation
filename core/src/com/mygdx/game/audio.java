package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;

public class audio {
    ArrayList<Music> sounds;

    public audio(){
        sounds =  new ArrayList<Music>();
        File_Reader();
    }

    private void File_Reader(){
        sounds.add(Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/Title.mp3")));
        sounds.add(Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/Room.mp3")));
        sounds.add(Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/City.mp3")));
        sounds.add(Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/Shop.mp3")));

    }

    void play(int choice){
        sounds.get(choice).play();
        sounds.get(choice).setLooping(true);
    }

    void stop(){
        for(int i = 0; i < sounds.size(); i ++){
            sounds.get(i).stop();
        }

    }
}
