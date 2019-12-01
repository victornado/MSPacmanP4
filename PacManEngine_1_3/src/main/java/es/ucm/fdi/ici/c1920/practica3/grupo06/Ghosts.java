package es.ucm.fdi.ici.c1920.practica3.grupo06;


import java.util.EnumMap;

import pacman.game.Game;
import pacman.controllers.GhostController;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;

public class Ghosts extends GhostController {
	
	private EnumMap<GHOST, MOVE> moves = new EnumMap<GHOST, MOVE>(GHOST.class);
	
	@Override
	public void preCompute(String opponent) {
		// TODO Auto-generated method stub
		super.preCompute(opponent);
	}
	
	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		// TODO Auto-generated method stub
		return moves;
	}

	@Override
	public void postCompute() {
		// TODO Auto-generated method stub
		super.postCompute();
	}
	
	
}