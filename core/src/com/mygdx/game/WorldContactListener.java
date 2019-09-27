package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;


public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Begin Contact", "");
    }

    @Override
    public void endContact(Contact contact) {

        Gdx.app.log("End Contact", "");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
