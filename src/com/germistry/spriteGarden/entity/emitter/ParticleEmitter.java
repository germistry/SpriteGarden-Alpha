package com.germistry.spriteGarden.entity.emitter;

import com.germistry.spriteGarden.entity.particle.Particle;
import com.germistry.spriteGarden.level.Level;

public class ParticleEmitter extends Emitter {

	private int life;
	
	public ParticleEmitter(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for(int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life));
		}
	}

	public int getLife() {
		return life;
	}
}
