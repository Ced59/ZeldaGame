package fr.valarep.valaga;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Perso {

    public Enemy(float x, float y, Texture t, int speed) {

        super(x, y, t, speed);

    }

    public void position() {
        
        if (Math.random() < 0.5) {
            
            this.y += speed * Math.random();
            super.position();
            
        } else {
            
            this.y -= speed * Math.random();
            super.position();
            
        }
        
        if (Math.random() < 0.5) {
            
            x += speed * Math.random();
            super.position();
            
        } else {
            
            x -= speed * Math.random();
            super.position();
        }
}
}

