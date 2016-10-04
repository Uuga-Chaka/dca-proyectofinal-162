package programaciondmi.dca.ejemplos;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieBlanca extends EspecieAbstracta implements IApareable,
		ICarnivoro {
	private int vida;
	private float fuerza;
	private int velocidad;
	/*
	 * Se utiliza para definfir cuando el inidividuo puede realizar acciones:
	 * moverse, aparearse, etc
	 */
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;

	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;

	public EspecieBlanca(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 5;

		System.out.println(this);
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub

	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		HijoBlanco hijo = new HijoBlanco(ecosistema);
		hijo.setX(this.x);
		hijo.setY(this.y);
		ecosistema.agregarEspecie(hijo);
		return hijo;
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.fill(255);
		app.ellipse(x, y, vida, vida);
	}

	@Override
	public void mover() {
		if (energia > 0) {
			System.out.println("[id=" + id + ", energia=" + energia + "]");

			// Definir una direccion aleatoria
			int targetX = random.nextInt();
			int targetY = random.nextInt();
			cambiarDireccion(new PVector(targetX, targetY));

			// Si tengo buena energía para aparearme
			if (energia > LIMITE_APAREO) {
				System.out.println("Me puedo aparear");
				buscarParejaCercana();
				// Si hay una pareja cercana la prioridad es reproducirse
				if (parejaCercana != null) {

					intentarAparear();
				}
			}

			// moverse en la dirección asignada actualmente
			this.x += dir.x;
			this.y += dir.y;
			energia -= 0.01;

		}

	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();

			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * <p>
	 * Este método busca a una especie apareable dentro del rango permitido por
	 * la fuerza actual.
	 * </p>
	 */
	private void buscarParejaCercana() {

		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		System.out.println("Buscando pareja entre " + todas.size()
				+ " especies del mundo");
		ListIterator<EspecieAbstracta> iterador = todas.listIterator();
		boolean encontro = false;
		while (!encontro && iterador.hasNext()) {
			EspecieAbstracta e = iterador.next();
			if ((e instanceof IApareable) && !e.equals(this)) {
				float dist = PApplet.dist(x, y, e.getX(), e.getY());
				System.out.println("Encontró apareable a " + dist);
				if (dist < energia) {
					System.out.println("Encontró una pareja cercana");
					encontro = true;
					parejaCercana = e;
					// Cambiar la dirección
					cambiarDireccion(new PVector(parejaCercana.getX(),
							parejaCercana.getY()));
				}
			}
		}
		// asegurarse de que la referencia sea null;
		if (!encontro) {
			parejaCercana = null;
			System.out.println("No encontró una pareja cercana");
		}

	}

	/**
	 * <p>
	 * Este método valida que una pareja cercana este a la distancia adecuada y
	 * genera un descendiente en caso de cumplirse la condición
	 * </p>
	 */
	private void intentarAparear() {

		float dist = PApplet.dist(x, y, parejaCercana.getX(),
				parejaCercana.getY());
		if (dist < vida) {
			IApareable a = (IApareable) parejaCercana;
			ecosistema.agregarEspecie(aparear(a));
			// perder energia
			energia -= 50;
		}

	}

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}

	@Override
	public String toString() {
		return "EspecieBlanca [id=" + id + ", vida=" + vida + ", fuerza="
				+ fuerza + ", parejaCercana=" + parejaCercana + ", dir=" + dir
				+ ", x=" + x + ", y=" + y + ", estado=" + estado + "]";
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO implementar metodo
		return false;
	}

}
