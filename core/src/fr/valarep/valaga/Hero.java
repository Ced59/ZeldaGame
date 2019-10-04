package fr.valarep.valaga;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;


public class Hero extends Perso {

    public Hero () {
        
    }

    public Hero(float x, float y, Texture t, int speed) {

        super(x, y, t, speed);
    }

    public void position() {


        boolean left = false;
        if (Gdx.input.isKeyPressed(Keys.Q) || Gdx.input.isKeyPressed(Keys.LEFT)){

            left = true;
        }

        boolean right = false;
        if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){

            right = true;
        }

        boolean up = false;
        if (Gdx.input.isKeyPressed(Keys.Z) || Gdx.input.isKeyPressed(Keys.UP)){

            up = true;
        }

        boolean down = false;
        if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){

            down = true;
        }

        if (left){

            x -= speed;
            this.texture = new Texture("LinkLeft.png");
            super.position();
        }

        if (right){
        
            x += speed;
            this.texture = new Texture("LinkRight.png");
            super.position();
        }

        if (down){
            
            y -= speed;
            super.position();
        }

        if (up){

            y += speed;
            super.position();
        }
    }
}