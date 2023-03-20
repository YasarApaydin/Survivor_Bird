package com.yasarapaydin.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
SpriteBatch batch;
Texture background;
Texture bee1;
Texture bee2;
Texture bee3;
Texture bird,bird1,bird2,bird3,bird4,bird5,bird6,bird7,bird8;

float birdX=0;
float birdY=0;
int gameState=0;
float velocity=0;
float enemyVelocity=4;
Random random;
float gravity= 0.2f;
int numberOfEnemies= 2;
int k=0;
Circle birdCircle;
ShapeRenderer shapeRenderer;
int score = 0;
BitmapFont font;
BitmapFont font2;
int scoredEnemy=0;


float[] enemyX = new float[numberOfEnemies];
float[]  enemyOffSet1 = new float[numberOfEnemies];
float[]  enemyOffSet2 = new float[numberOfEnemies];
float[]  enemyOffSet3= new  float[numberOfEnemies];
float distance=0;
private Music music;
private Sound sound;

//private Sound sound;

Circle[] enemyCircle1;
Circle[] enemyCircle2;
Circle[] enemyCircle3;


	@Override
	public void create () {
         batch = new SpriteBatch();
		background = new Texture("background.png");
		bee1 = new Texture("bee.png");
		bee2 = new Texture("bee.png");
		bee3 = new Texture("bee.png");
        bird = new Texture("bird.png");
		distance= Gdx.graphics.getWidth()/2;
		bird1 = new Texture("bird-2.png");
		bird2 = new Texture("bird-4.png");
		bird3 = new Texture("bird-5.png");
	    bird4 = new Texture("bird-7.png");
		bird5 = new Texture("bird-8.png");
		bird6 = new Texture("bird-9.png");
		bird7 = new Texture("bird-10.png");
		bird8 = new Texture("bird-11.png");
		random = new Random();
		font= new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(4);
		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(6);
         shapeRenderer = new ShapeRenderer();
         birdX=Gdx.graphics.getWidth()/2-bird.getHeight()/2;
		 birdY= Gdx.graphics.getHeight()/3;
		 birdCircle = new Circle();
		sound = Gdx.audio.newSound(Gdx.files.internal("background.png"));
		 music = Gdx.audio.newMusic(Gdx.files.internal("choco birds run.mp3"));

	     enemyCircle1 = new Circle[numberOfEnemies];
		 enemyCircle2 = new Circle[numberOfEnemies];
		 enemyCircle3 =  new Circle[numberOfEnemies];
		 music.setVolume(0.2f);
		 music.setLooping(true);
		 music.play();


         for(int i=0;i<numberOfEnemies;i++){

			 enemyOffSet1[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
			 enemyOffSet2[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
			 enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

			 enemyX[i]=Gdx.graphics.getWidth()*3/4+Gdx.graphics.getWidth()-bee1.getWidth()/2-i*distance;

			 enemyCircle1[i] = new Circle();
			 enemyCircle2[i] = new Circle();
			 enemyCircle3[i] = new Circle();
		 }


	}

	@Override
	public void render () {
	/*if(Gdx.input.justTouched()){
       gameState =1;

		}*/
		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		if(gameState==1){

			if(enemyX[scoredEnemy] <Gdx.graphics.getWidth()/2-bird.getHeight()/2){
				score++;
				if(scoredEnemy < numberOfEnemies-1){
					scoredEnemy++;

				}else{
					scoredEnemy=1;
				}
		}

			if(Gdx.input.justTouched()){
				velocity = (float)   -birdY/70;
         k++;
if(k==9){
	k=0;
}
			}


			for(int i=0;i<numberOfEnemies;i++) {


				if(enemyX[i]<-Gdx.graphics.getWidth()/15){
					enemyX[i]= enemyX[i]+numberOfEnemies*distance;

					enemyOffSet1[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet2[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
				}else{
					enemyX[i] = enemyX[i]-enemyVelocity;
				}


				batch.draw(bee1,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffSet1[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
				batch.draw(bee2,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffSet2[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
				batch.draw(bee3,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffSet3[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);

			    enemyCircle1[i] = new Circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet1[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);
				enemyCircle2[i] = new Circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet2[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);
				enemyCircle3[i] = new Circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet3[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);
			}





			if(birdY>0 && birdY < Gdx.graphics.getHeight()){
				velocity=velocity +gravity;
				birdY = birdY -velocity;


			}else{
				gameState= 2;

			}


		}else if(gameState==0){

			if(Gdx.input.justTouched()){
				gameState =1;

           long id = sound.play(1.0f);
           sound.setPitch(id,2);
           sound.setLooping(id,false);
			}
		}else if(gameState==2){
			font2.draw(batch,"Game Over To Play Again!",100,Gdx.graphics.getHeight()/2);
			velocity = (float)   -birdY/70;
			if(Gdx.input.justTouched()){
			gameState =1;
			birdY= Gdx.graphics.getHeight()/3;



				for(int i=0;i<numberOfEnemies;i++){

					enemyOffSet1[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet2[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

					enemyX[i]=Gdx.graphics.getWidth()*3/4+Gdx.graphics.getWidth()-bee1.getWidth()/2-i*distance;

					enemyCircle1[i] = new Circle();
					enemyCircle2[i] = new Circle();
					enemyCircle3[i] = new Circle();
				}

				velocity=velocity +gravity;
				scoredEnemy=0;
				score=0;
			}
		}



       batch.draw(bird,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		if(k==2){
			batch.draw(bird1,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		}
		if(k==3){
			batch.draw(bird2,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		}		if(k==4){
			batch.draw(bird3,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		}
		if(k==5){
			batch.draw(bird4,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		}	if(k==6){
			batch.draw(bird5,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		}
		if(k==7){
			batch.draw(bird6,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		}	if(k==8){
			batch.draw(bird7,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		}
	  font.draw(batch,String.valueOf(score),Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/5);
	   batch.end();

       birdCircle.set(birdX+Gdx.graphics.getWidth()/30,birdY+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);


	 //  shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
	  // shapeRenderer.setColor(Color.BLACK);
	  // shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);

	   for(int i=0;i<numberOfEnemies ;i++){
          // shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet1[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);
		  // shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet2[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);
		   //shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet3[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);

	  if(Intersector.overlaps(birdCircle,enemyCircle1[i]) || Intersector.overlaps(birdCircle,enemyCircle2[i]) || Intersector.overlaps(birdCircle,enemyCircle3[i])){
		  gameState = 2;

	  }
	   }
	//	shapeRenderer.end();
	}

	@Override
	public void dispose () {
sound.dispose();
music.dispose();
	}
}
