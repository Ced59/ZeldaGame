package fr.valarep.valaga;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Valaga extends ApplicationAdapter {
  SpriteBatch batch;
  Enemy link;
  Hero ganondorf;
  Enemy zelda;
  Enemy midona;
  boolean gameOver = false;
  

  
  final static Color BACKGROUND_COLOR = Color.GOLD;

  @Override
  public void create() {
    batch = new SpriteBatch();
    link = new Enemy(0.0f, 0.0f, new Texture("Link.png"), 20);
    
    ganondorf = new Hero(0.0f, Gdx.graphics.getHeight() - 100, new Texture("Ganondorf.png"), 10);
    zelda = new Enemy(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100, new Texture("zelda.png"), 20);
    midona = new Enemy(Gdx.graphics.getWidth() - 100, 0.0f, new Texture("Midona.png"), 20);

  }

  @Override
  public void render() {

    Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, BACKGROUND_COLOR.a); // Efface l'écran entre chaque Sprite
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    updatePositions();

    RenderLogo(link);
    RenderLogo(ganondorf);
    RenderLogo(zelda);
    RenderLogo(midona);
  }

  private void updatePositions() {

    link.position();
    ganondorf.position();
    zelda.position();
    midona.position();

    InCollision(ganondorf, link);
    InCollision(ganondorf, zelda);
    InCollision(ganondorf, midona);

  }

  private void InCollision(Hero hero, Enemy enemy) {

    if (hero.inCollisionWith(enemy)) {

      gameOver = true;

    }
  }

  private void RenderLogo(Perso perso) {

    if (gameOver) {
      gameOver();
    }
    else {
      perso.draw(batch);
    }
    
  }

  private void gameOver() {
    batch.begin();
    batch.draw(new Texture("game_over.jpg"), 325, 300);
    batch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
    link.dispose();
    ganondorf.dispose();
    zelda.dispose();
    midona.dispose();
  }
}
