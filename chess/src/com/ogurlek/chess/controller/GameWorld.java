package com.ogurlek.chess.controller;

import com.badlogic.gdx.Gdx;
import com.ogurlek.chess.model.Board;
import com.ogurlek.chess.model.GameState;
import com.ogurlek.chess.model.Piece;
import com.ogurlek.chess.model.PieceColor;
import com.ogurlek.chess.model.PieceType;
import com.ogurlek.chess.model.Tile;

public class GameWorld {

	GameState state;
	Board board;
	Piece selectedPiece;
	int[][] movement;

	public GameWorld(){
		this.state = GameState.TURN_WHITE;
		this.board = new Board();
		this.selectedPiece = null;
		this.movement = new int[8][8];
		Gdx.input.setInputProcessor(new InputHandler(this));
	}

	public void touchedBoard(int x, int y){
		int tileX = x / 60;
		int tileY = y / 60;

		Tile touchedTile = board.getTile(tileX, tileY);
		boolean occupied = touchedTile.isOccupied();
		Piece touchedPiece = null;

		if(occupied)
			touchedPiece = touchedTile.getPiece();

		switch(state){
		case TURN_WHITE: 
			if(occupied && (touchedPiece.getColor() == PieceColor.WHITE)){
				selectedPiece = touchedPiece;
				mapMovementArray(selectedPiece);
				state = GameState.MOVE_WHITE;
			}
			break;
		case MOVE_WHITE: 
			//placeholder chesspiece movement
			if(!occupied){
				move(selectedPiece, touchedTile);
				state = GameState.TURN_BLACK;
			}
			break;
		case TURN_BLACK: 
			if(occupied && (touchedPiece.getColor() == PieceColor.BLACK)){
				selectedPiece = touchedPiece;
				mapMovementArray(selectedPiece);
				state = GameState.MOVE_BLACK;
			}
			break;
		case MOVE_BLACK:
			//placeholder chesspiece movement
			if(!occupied){
				move(selectedPiece, touchedTile);
				state = GameState.TURN_WHITE;
			}
			break;
		}
	}

	private void clearMovementArray(){
		movement = new int[8][8];
	}

	private void mapMovementArray(Piece piece){
		clearMovementArray();

		Tile tile = piece.getTile();
		movement[tile.getX()][tile.getY()] = 2;

		switch(piece.getType()){
		case PAWN: mapPawnMovement(piece); break;
		case ROOK: break;
		case KNIGHT: break;
		case BISHOP: break;
		case QUEEN: break;
		case KING: break;
		}
	}

	private void mapPawnMovement(Piece piece){
		Tile tile = piece.getTile();
		int x = tile.getX();
		int y = tile.getY();
		PieceColor color = piece.getColor();
		PieceColor opposite = (color == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
		int direction = (color == PieceColor.WHITE) ? 1 : -1; 
		Tile targetTile = null;
		PieceColor targetColor = null;

		// Front pawn movement
		boolean frontOccupied = this.board.isOccupied(x, y-direction);
		if(!frontOccupied){
			movement[x][y-direction] = 1;
		}

		// Front right pawn attack
		if(this.board.isOccupied(x+1,y-direction)){
			targetTile = board.getTile(x+1, y-direction);

			if(targetTile != null){
				targetColor = targetTile.getPiece().getColor();

				if(targetColor == opposite){
					movement[x+1][y-direction] = 1;
				}
			}
		}

		// Front left pawn attack
		if(this.board.isOccupied(x-1,y-direction)){
			targetTile = board.getTile(x-1, y-direction);

			if(targetTile != null){
				targetColor = targetTile.getPiece().getColor();

				if(targetColor == opposite){
					movement[x-1][y-direction] = 1;
				}
			}
		}
	}

	public void move(Piece piece, Tile newTile){
		clearMovementArray();

		Tile oldTile = piece.getTile();
		oldTile.free();

		piece.setTile(newTile);
		newTile.occupy(piece);
	}

	public Board getBoard(){
		return this.board;
	}
	
	public int[][] getMovement(){
		return movement;
	}
}
