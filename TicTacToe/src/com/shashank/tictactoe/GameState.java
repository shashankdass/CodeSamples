package com.shashank.tictactoe;

public class GameState {

	PlayerType[][] pl = new PlayerType[3][3];//array of player types
	PlayerType winner = new PlayerType("NONE");//determine wether winner is X or O
	boolean x_turn = true;// true if x turn to play
	int move_count = 0;
	public PlayerType[][] getPl() {
		return pl;
	}
	public void setPl(PlayerType[][] pl) {
		this.pl = pl;
	}
	public PlayerType getWinner() {
		return winner;
	}
	public void setWinner(PlayerType winner) {
		this.winner = winner;
	}
	public boolean isX_turn() {
		return x_turn;
	}
	public void setX_turn(boolean x_turn) {
		this.x_turn = x_turn;
	}
	public int getMove_count() {
		return move_count;
	}
	public void setMove_count(int move_count) {
		this.move_count = move_count;
	}
	public int getLast_move() {
		return last_move;
	}
	public void setLast_move(int last_move) {
		this.last_move = last_move;
	}
	int last_move = 0; // if a player moves to r row and c column last_move = 3*r + c

	public void isWin() {
		// TODO Auto-generated method stub
		int count = 0;
		pl = this.pl;
		for (int i = 0; i < 3; i++){
		if (pl[i][0].getPlayer().equalsIgnoreCase("X") && pl[i][1].getPlayer().equalsIgnoreCase("X") && pl[i][2].getPlayer().equalsIgnoreCase("X")) {
			System.out.println("X Wins!!");
			break;
			} 
		}
		for (int i = 0; i < 3; i++){
		if (pl[0][i].getPlayer().equalsIgnoreCase("X") && pl[1][i].getPlayer().equalsIgnoreCase("X") && pl[2][i].getPlayer().equalsIgnoreCase("X")) {
			System.out.println("X Wins!!");
			break;
			} 
		}
		for (int i = 0; i < 3; i++){
			if (pl[i][0].getPlayer().equalsIgnoreCase("O") && pl[i][1].getPlayer().equalsIgnoreCase("O") && pl[i][2].getPlayer().equalsIgnoreCase("O")) {
				System.out.println("O Wins!!");
				break;
				} 
			}
		for (int i = 0; i < 3; i++){
		if (pl[0][i].getPlayer().equalsIgnoreCase("O") && pl[1][i].getPlayer().equalsIgnoreCase("O") && pl[2][i].getPlayer().equalsIgnoreCase("O")) {
			System.out.println("O Wins!!");
			break;
			} 
		}
		if(pl[0][0].getPlayer().equalsIgnoreCase("X") && pl[1][1].getPlayer().equalsIgnoreCase("X") && pl[2][2].getPlayer().equalsIgnoreCase("X")) {
			System.out.println("X Wins!!");
		}
		if(pl[0][0].getPlayer().equalsIgnoreCase("O") && pl[1][1].getPlayer().equalsIgnoreCase("O") && pl[2][2].getPlayer().equalsIgnoreCase("O")) {
			System.out.println("O Wins!!");
		}
	}
	public void initialize() {
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++){
				pl[i][j] = new PlayerType("NONE");
			}
		
		// TODO Auto-generated method stub
		
	}
		
}
	
	

