package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import java.util.Random;

import processing.core.PImage;
import processing.core.PVector;
import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.ejecucion.Mundo;

public class AmidCanibal extends EspecieAbstracta implements ICanibal {
	
	private PImage []img;
	private PApplet app;

	private int vida;
	private float fuerza;
	private int velocidad;
	/*
	 * Se utiliza para definfir cuando el individuo puede realizar acciones:
	 * moverse, aparearse, etc
	 */
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;
	private int ciclo;

	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;

	public AmidCanibal(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		
		img[0]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo00.png");
		img[1]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo01.png");
		img[2]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo02.png");
		img[3]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo03.png");
		img[4]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo04.png");
		img[5]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo05.png");
		img[6]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo06.png");
		img[7]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo07.png");
		img[8]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo08.png");
		img[9]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo09.png");
		img[10]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo10.png");
		img[11]= app.loadImage("data/Personajes.P1/P1_Frente/P1_F_Enfermo11.png");
		
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.image(img[0],0,0);
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(vida>0){
			mover();
			try{
				Thread.sleep(33);
			}catch(Exception e){
				
			}
		}

	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}
