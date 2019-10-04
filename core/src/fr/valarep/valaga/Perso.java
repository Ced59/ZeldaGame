package fr.valarep.valaga;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Perso {
    
    SpriteBatch batch;
    protected Texture texture;
    protected float x = 30.0f;
    protected float y = 40.0f;
    protected int speed = 10;
    protected Rectangle zone;
    
    public Perso(){

    }
    
    public Perso(float x, float y, Texture t, int speed){
        
        this.x = x;
        this.y = y;
        texture = t;
        this.speed = speed;
        zone = new Rectangle(x, y , texture.getWidth(), texture.getHeight());
        
    }
    
    public Texture getTexture() {
        return texture;
    }
    
    public void draw(SpriteBatch batch){
        
        batch.begin();
        batch.draw(texture, x, y);
        batch.end();
    }
    
    public void dispose() {
        texture.dispose();
    }

	public void position() {

        x = Collision.borderCollisionX(x, this);
        y = Collision.borderCollisionY(y, this);
        zone.setPosition(x, y);
	}

	public boolean inCollisionWith(Perso perso) {

		return this.zone.overlaps(perso.zone);
	}
    
}