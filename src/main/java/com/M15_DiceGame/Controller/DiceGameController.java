package com.M15_DiceGame.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.M15_DiceGame.Service.DiceGameServiceImpl;
import com.M15_DiceGame.DTO.*;
import com.M15_DiceGame.Domain.Game;

@RestController
@RequestMapping("/")
public class DiceGameController {

	@Autowired
	DiceGameServiceImpl diceGameServiceImpl;
	
	
	@PostMapping({"/players","/players/"})
	public UserDTO createNewUser(@RequestBody UserDTO user) {
		return diceGameServiceImpl.addNewUser(user);
	}	
	
	
	@PutMapping({"/players","/players/"})
	public UserDTO changeUserName(@RequestBody UserDTO userHttp) {
		UserDTO user = diceGameServiceImpl.findById(userHttp.getId());
		user.setName(userHttp.getName());
		return diceGameServiceImpl.updateUser(user);
	}
	
	

	@PostMapping({"/players/{id}/games","/players/{id}/games/"})
	public Game throwDices(@PathVariable(name="id") Long id) {
		UserDTO user = diceGameServiceImpl.findById(id);
		Game game = user.play();
		diceGameServiceImpl.saveNewGame(game);
		diceGameServiceImpl.updateUser(user);
		// In order to have the new Game created together with its game_id,
		// We retrieve the list of all User's games from database and We'll return only the last one.
		List<Game> gamesList = user.getGames(); 
		return gamesList.get(gamesList.size()-1);  // Used to get the last User's Game  
		
	}
	
		
	@DeleteMapping({"/players/{id}/games","/players/{id}/games/"})
	public UserDTO deleteAllUserGames(@PathVariable(name="id") Long id) {
		UserDTO user = diceGameServiceImpl.findById(id);
		user.deleteAllGames();
		diceGameServiceImpl.updateUser(user);
		return user;
	}
	
	
	@GetMapping({"/players/","/players"})
	public List<UserDTO> getAllUsersWithGameStatistics() {
		return diceGameServiceImpl.getAllUsers();
	}
	

	@GetMapping({"/players/{id}/games","/players/{id}/games/"})
	public List<Game> getUserGames(@PathVariable(name="id") Long id) {
		UserDTO user = diceGameServiceImpl.findById(id);
		return user.getGames();
	}

	
	
	@GetMapping({"/players/ranking","/players/ranking/"})
	public float getAverageGameWinningStatistic() {
		return diceGameServiceImpl.getAverageWinningScore();
	}
	
	
	@GetMapping({"/players/ranking/loser","/players/ranking/loser/"})
	public List<UserDTO> getUserWithLowestScore() {
		return diceGameServiceImpl.getLastRanking();
	}	
	
	
	
	
	@GetMapping("/players/ranking/winner")
	public List<UserDTO> getUserWithHighestScore() {
		return diceGameServiceImpl.getFirstRanking();
	}
	
	
}

