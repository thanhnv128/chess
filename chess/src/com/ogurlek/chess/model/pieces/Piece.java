package com.ogurlek.chess.model.pieces;

import com.ogurlek.chess.model.board.Tile;

public class Piece {
	private Tile tile;
	private PieceType type;
	private PieceColor color;
	
	public Piece(Tile tile, PieceType type, PieceColor color){
		this.tile = tile;
		this.type = type;
		this.color = color;
	}
	
	public Tile getTile(){
		return this.tile;
	}
	
	public void setTile(Tile tile){
		this.tile = tile;
	}
	
	public PieceType getType(){
		return this.type;
	}
	
	public PieceColor getColor(){
		return this.color;
	}
}