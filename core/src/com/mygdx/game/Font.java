package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Font {

    private FreeTypeFontGenerator FontGenerator ;
    private FreeTypeFontGenerator.FreeTypeFontParameter FontParameter;
    BitmapFont font;
    int size =12;
    SpriteBatch batch;

    public Font(int size) {
        FontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Assets/font.ttf"));
        FontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FontParameter.size= size*(int)categories.PPM;
        font = FontGenerator.generateFont(FontParameter);
        batch = new SpriteBatch();
    }
    public void render(String text,float x,float y){
        batch.begin();
        font.draw(batch,text,x,y);
        batch.end();
    }
    public void dispose(){

        FontGenerator.dispose();
    }

}
