package tests.gamemanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.gamemanager.model.Game;
import edu.westga.cs3212.gamemanager.model.Player;
import edu.westga.cs3212.gamemanager.model.User;

class TestAddCompletedGames {

	@Test
	void testGameNull() {
		User user = new User("Geekster101");
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			user.addCompletedGame(null);
		});
		assertEquals("Game must exist", exception.getMessage());
	}
	
	@Test
	void testGameInProgressAlreadyContains() {
		Player player = new Player("Bob", 50);
		User user = new User("Geekster101");
		Game game = new Game("StarWars");
		game.addPlayer(player);
		user.addInProgressGame(game);
		game.removePlayer(player);
		user.addInProgressGame(game);
		
		assertEquals(0,game.getPlayers().size());
	}
	
	@Test
	void testGameComepletedGameAlreadyContains() {
		Player player = new Player("Bob", 50);
		User user = new User("Geekster101");
		Game game = new Game("StarWars");
		game.addPlayer(player);
		game.setCompleteStatus(true);
		user.addInProgressGame(game);
		game.removePlayer(player);
		user.addInProgressGame(game);
		
		assertEquals(0,game.getPlayers().size());
	}
	
	@Test
	void testCompletedStatusGameTrue(){
		Game game1 = new Game("Default");
		User user = new User("Peter Griffin");
		game1.setCompleteStatus(true);
		user.addInProgressGame(game1);
		assertFalse(user.getInProgressGames().get(0).getCompletedStatus());
		
	}
	
	@Test
	void testCompletedStatusGameFalse(){
		Game game1 = new Game("Default");
		User user = new User("Peter Griffin");
		game1.setCompleteStatus(false);
		user.addInProgressGame(game1);
		assertFalse(user.getInProgressGames().get(0).getCompletedStatus());
		
	}
	
	@Test
	void testAddValidInCompleted(){
		Game game1 = new Game("Default");
		User user = new User("Peter Griffin");
		game1.setCompleteStatus(true);
		user.addCompletedGame(game1);
		assertTrue(user.getCompletedGames().get(0).getCompletedStatus());
		
	}
	
	@Test
	void testAddMultipleValidInProgressGame(){
		Game game1 = new Game("Default");
		Game game2= new Game("Game2");
		Game game3 = new Game("Game3");
		User user = new User("Peter Griffin");
		user.addCompletedGame(game1);
		user.addCompletedGame(game2);
		user.addCompletedGame(game3);
		assertEquals(3, user.getCompletedGames().size());
		
	}

}
