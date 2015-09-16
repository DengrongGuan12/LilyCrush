package model.managers;

import java.util.ArrayList;

import model.Game;

public class GamesManager {
	private static GamesManager gamesManager;
	ArrayList<Game> games = new ArrayList<Game>();

	private GamesManager() {
	}

	public static synchronized GamesManager getInstance() {
		if (gamesManager == null) {
			gamesManager = new GamesManager();
		}
		return gamesManager;
	}

	public Game getGame(int gameID) {
		Game game = null;
		for (Game game1 : games) {
			if (game1.getID() == gameID) {
				game = game1;
				break;
			}
		}
		return game;
	}

	public void addGame(Game game) {
		this.games.add(game);
	}

	/*
	 * ��Ϸ����ʱ���ߵ�����Ϸ����ҶϿ�����ʱ��Ҫ������Ϸ������Ϸ���ڽ���ҲҪ����Ϸ��ֹ��
	 */
	public void removeGame(int gameID) {
		for (Game game : games) {
			if (game.getID() == gameID) {
				games.remove(game);
				break;
			}
		}
	}

}
