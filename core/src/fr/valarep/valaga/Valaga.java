package fr.valarep.valaga;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Valaga extends ApplicationAdapter {
  SpriteBatch batch;
  Hero link;
  Goal zelda;
  ArrayList<Enemy> cocottes;
  boolean gameOver = false;
  
  static Color backgroundColor = Color.GOLD;
  
  @Override
  public void create() {
    batch = new SpriteBatch();
    link = new Hero(0.0f, 0.0f, new Texture("LinkRight.png"), 10);
    zelda = new Goal(Gdx.graphics.getWidth() - 60 , Gdx.graphics.getHeight() - 100, new Texture("zelda.png"), 10);
    cocottes = new ArrayList<Enemy>();
    
    cocottes.add(new Enemy(0.0f, Gdx.graphics.getHeight() - 50, new Texture("CocotteRight.png"), 20));
    cocottes.add(new Enemy(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() /2, new Texture("CocotteRight.png"), 20));
    cocottes.add(new Enemy(Gdx.graphics.getWidth() - 50, 0.0f, new Texture("CocotteRight.png"), 20));
    
  }
  
  @Override
  public void render() {
    
    Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a); // Efface l'écran entre chaque Sprite
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    updatePositions();
    
    RenderLogo(link);
    RenderLogo(zelda);
    
    for (Enemy enemy : cocottes) {
      RenderLogo(enemy);
    }
  }
  
  
  
  @Override
  public void dispose() {
    
    batch.dispose();
    link.dispose();
    zelda.dispose();
    
    for (Enemy enemy : cocottes) {
      enemy.dispose();
    }
  }
  
  
  private void updatePositions() {
    
    link.position();
    for (Enemy enemy : cocottes) {
      enemy.position();
    }
    
    zelda.position();
    
    for (Enemy enemy : cocottes) {
      InCollision(link, enemy);
    }
    
    InGoal(link, zelda);
  }
  
  private void InGoal(Hero hero, Goal goal) {
    
    if (hero.inCollisionWith(goal)) {
      
      link = new Hero(0.0f, 0.0f, new Texture("LinkRight.png"), 10);
      // zelda = new Goal(Gdx.graphics.getWidth() - 60 , Gdx.graphics.getHeight() - 100, new Texture("zelda.png"), 10);
      
      cocottes.add(new Enemy(0.0f, Gdx.graphics.getHeight() - 50, new Texture("CocotteRight.png"), 20));
      cocottes.add(new Enemy(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() /2, new Texture("CocotteRight.png"), 20));
      cocottes.add(new Enemy(Gdx.graphics.getWidth() - 50, 0.0f, new Texture("CocotteRight.png"), 20));
      
    }
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
    backgroundColor = Color.BLACK;

    Texture ganondorf = new Texture("Ganondorf2.png");
    Texture gameOver = new Texture("game_over.jpg");

    Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
    batch.draw(ganondorf, (Gdx.graphics.getWidth() / 2) - (ganondorf.getWidth() / 2), 0);
    batch.draw(gameOver, (Gdx.graphics.getWidth() / 2) - (gameOver.getWidth() / 2), 840);
    batch.end();
  }
}
