package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Font {

    private FreeTypeFontGenerator FontGenerator ;
    private FreeTypeFontGenerator.FreeTypeFontParameter FontParameter;
    BitmapFont font;
    SpriteBatch batch;

    public Font(int size) {
        FontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Assets/font.ttf"));
        FontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FontParameter.size= size;
        font = FontGenerator.generateFont(FontParameter);
        batch = new SpriteBatch();
    }
    public void render(String text,float x,float y){
        font.setColor(0,0,0, 1);
        batch.begin();
        font.draw(batch,text,x,y);
        batch.end();
    }
    public void dispose(){

        FontGenerator.dispose();
    }

}
