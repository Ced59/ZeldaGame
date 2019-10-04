package fr.valarep.valaga;

import com.badlogic.gdx.Gdx;

public class Collision {

    public static float borderCollisionX(float x, Perso perso) {

        int limitX = Gdx.graphics.getWidth() - perso.texture.getWidth();

        if (limitX < x) {
            x = limitX;

        } else if (x < 0.0f) {

            x = 0.0f;
        }

        return x;
    }

    public static float borderCollisionY(float y, Perso perso) {

        int limitY = Gdx.graphics.getHeight() - perso.texture.getHeight();

        if (limitY < y) {
            y = limitY;
            
        } else if (y < 0.0f) {

            y = 0.0f;
        }

        return y;
    }
}