/*
 * This is the main class for the game
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game;
import com.badlogic.gdx.physics.box2d.*;
import java.util.ArrayList;

import com.mygdx.Objects.Door;
import com.mygdx.Objects.Filter;
import com.mygdx.Objects.NPC;
public class WorldContactListener implements ContactListener {
    public ArrayList<Fixture> obj; // an fixture array that adds each object within contact to distingush between them
    public WorldContactListener(){
        obj = new ArrayList<Fixture>(); // make a new one for each map
    }

    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureA().getUserData() == "Player"){ // if the first fixture is player
            if(contact.getFixtureB().getUserData().getClass() == Door.class){ // check if the second fixture  is any door from door class
                obj.add(contact.getFixtureB()); // add the object for only that door
                Fade_Animation.alpha = 0; //set
                Maps.fade_out = true; // call the fade out animation
                new Fadeout_timer(1); // delay for a second
            }
            if(contact.getFixtureB().getUserData().getClass() == NPC.class){
                obj.add(contact.getFixtureB());
            }
            if(contact.getFixtureB().getUserData().getClass() == Filter.class){
                obj.add(contact.getFixtureB());
            }
        }

        Main.objs = obj; // update the obj arraylist in the main for changing the map
    }


    @Override
    public void endContact(Contact contact) {
        if(contact.getFixtureA().getUserData().equals("Player")){
            if(contact.getFixtureB().getUserData().getClass() == Door.class){ // check if the second fixture  is any door from door class
                obj.remove(contact.getFixtureB()); // add the object for only that door

            }
            if(contact.getFixtureB().getUserData().getClass() == NPC.class){
                obj.remove(contact.getFixtureB());

            }
            if(contact.getFixtureB().getUserData().getClass() == Filter.class){
                Main.filter = false;
                obj.remove(contact.getFixtureB());
            }
        }

        Main.objs = obj; // update the main one
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        short first = contact.getFixtureA().getFilterData().categoryBits;
        short second = contact.getFixtureB().getFilterData().categoryBits;
        if((first | second) == (Main.Player | Main.Filter)){
            Main.filter = true;
            contact.setEnabled(false);
        }

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}