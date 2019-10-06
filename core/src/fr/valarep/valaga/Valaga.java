package fr.valarep.valaga;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class Valaga extends ApplicationAdapter {
  SpriteBatch batch;
  Hero link;
  Goal zelda;
  ArrayList<Enemy> cocottes;
  boolean gameOver;
  Skin buttonSkin;
  Button reload;
  Stage stage;
  int lvl;
  BitmapFont font;
  BitmapFont font2;
  Music mainTheme;
  Sound soundGoal;
  Music evilLaught;
  Sound chicken;
  
  static Color backgroundColor = Color.GOLD;
  
  @Override
  public void create() {
    
    mainTheme = Gdx.audio.newMusic(Gdx.files.internal("sounds/maintheme.wav"));
    mainTheme.setLooping(true);
    mainTheme.play();
    mainTheme.setVolume(0.5f);
    soundGoal = Gdx.audio.newSound(Gdx.files.internal("sounds/cloche.wav"));
    evilLaught = Gdx.audio.newMusic(Gdx.files.internal("sounds/devilLaught.wav"));
    evilLaught.setLooping(false);
    chicken = Gdx.audio.newSound(Gdx.files.internal("sounds/chicken.wav"));
    chicken.play();

    gameOver = false;

    backgroundColor = Color.GOLD;
    batch = new SpriteBatch();
    link = new Hero(0.0f, 0.0f, new Texture("LinkRight.png"), 10);
    zelda = new Goal(Gdx.graphics.getWidth() - 60, Gdx.graphics.getHeight() - 100, new Texture("zelda.png"), 10);
    
    cocottes = new ArrayList<Enemy>();
    cocottes.add(new Enemy(0.0f, Gdx.graphics.getHeight() - 50, new Texture("CocotteRight.png"), 20));
    cocottes.add(new Enemy(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, new Texture("CocotteRight.png"), 20));
    cocottes.add(new Enemy(Gdx.graphics.getWidth() - 50, 0.0f, new Texture("CocotteRight.png"), 20));

    font = new BitmapFont();
    font2 = new BitmapFont();
    lvl = 1;
  }
  
  @Override
  public void render() {
    
    Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a); // Efface l'écran
    // entre chaque
    // Sprite
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
      
      soundGoal.play();

      cocottes.add(new Enemy(0.0f, Gdx.graphics.getHeight() - 50, new Texture("CocotteRight.png"), 20));
      cocottes.add(new Enemy(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, new Texture("CocotteRight.png"), 20));
      cocottes.add(new Enemy(Gdx.graphics.getWidth() - 50, 0.0f, new Texture("CocotteRight.png"), 20));
      lvl +=1;
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
    } else {
      perso.draw(batch);
      
      batch.begin();
      font2.draw(batch, "Vous êtes au niveau " + lvl, 10, 200);
      batch.end();
    }
  }
  
  private void gameOver() {
    
    mainTheme.stop();
    chicken.stop();
    evilLaught.play();

    Skin buttonSkin = new Skin(Gdx.files.internal("skin/glassy/skin/glassy-ui.json"));
    
    
    Stage stage = new Stage();
    Gdx.input.setInputProcessor(stage);
    
    Button reload = new TextButton("Rejouer", buttonSkin);
    reload.setPosition(1500, 900);
    reload.addListener(new ClickListener(){     
      @Override
      public void clicked(InputEvent event, float x, float y) {

        evilLaught.stop();
        create();
      }
    });
    
    stage.addActor(reload);
    
    Button quit = new TextButton("Quitter", buttonSkin);
    quit.setPosition(1500, 700);
    quit.addListener(new ClickListener(){     
      @Override
      public void clicked(InputEvent event, float x, float y) {
        
        dispose();
      }
    });
    
    stage.addActor(quit);
    
    stage.draw();
    
    batch.begin();
    backgroundColor = Color.BLACK;
    
    Texture ganondorf = new Texture("Ganondorf2.png");
    Texture gameOver = new Texture("game_over.jpg");
    
    font.draw(batch, "Vous avez atteint le niveau " + lvl, 10, 200);
    
    Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
    batch.draw(ganondorf, (Gdx.graphics.getWidth() / 2) - (ganondorf.getWidth() / 2), 0);
    batch.draw(gameOver, (Gdx.graphics.getWidth() / 2) - (gameOver.getWidth() / 2), 840);
    
    batch.end();
  }
}
