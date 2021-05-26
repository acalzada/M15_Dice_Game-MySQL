package com.M15_DiceGame.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.M15_DiceGame.DAO.GameDAO;
import com.M15_DiceGame.DAO.UserDAO;
import com.M15_DiceGame.DTO.UserDTO;
import com.M15_DiceGame.Domain.Game;
import com.M15_DiceGame.Domain.User;

@Service
public class DiceGameServiceImpl implements DiceGameService{

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	GameDAO gameDAO;
	
	
	@Override
	public UserDTO addNewUser(UserDTO userDTO) {
		User user = new User(userDTO.getName());
		user = userDAO.save(user);
		return new UserDTO(user);
	}

	
	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		User user = userDAO.findById(userDTO.getId()).get();
		user.setName(userDTO.getName());
		user.setMeanScore(userDTO.getMeanScore());
		user.setGames(userDTO.getGames());
		user = userDAO.save(user);
		return new UserDTO(user);
	}


	@Override
	public Game saveNewGame(Game game) {
		return gameDAO.save(game);
	}
	
	@Override
	public UserDTO findById(Long id) {
		User user = userDAO.findById(id).get();
		return new UserDTO(user);
	}

	
	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userDAO.getAllUsersIdAndNameAndMeanScore();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		users.forEach((user) -> usersDTO.add(new UserDTO(user.getId(), user.getName(), user.getMeanScore())));
		
		return usersDTO;
	}
	
	public List<UserDTO> getLastRanking() {
		float min_mean_score = userDAO.getMinMeanScore();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		userDAO.findByMeanScore(min_mean_score).forEach((user)-> usersDTO.add(new UserDTO(user)));
		return usersDTO;
	}
	
	@Override
	public List<UserDTO> getFirstRanking() {
		float max_mean_score = userDAO.getMaxMeanScore();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		userDAO.findByMeanScore(max_mean_score).forEach((user)->usersDTO.add(new UserDTO(user)));
		return usersDTO;
	}
	 
	 
	public float getAverageWinningScore() {
		return userDAO.getAverageMeanScore();
	}
	
}
