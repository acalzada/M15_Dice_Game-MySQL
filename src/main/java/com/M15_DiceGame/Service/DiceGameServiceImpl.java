package com.M15_DiceGame.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.M15_DiceGame.DAO.GameDAO;
import com.M15_DiceGame.DAO.UserDAO;
import com.M15_DiceGame.DTO.UserDTO;
import com.M15_DiceGame.DTO.GameDTO;
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
		List<UserDTO> usersDTO = new ArrayList();
		users.forEach((user) -> usersDTO.add(new UserDTO(user)));
		
		return usersDTO;
	}

	/*
	@Override
	public float getAverageWinningScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserDTO> getLastRanking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getFirstRanking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.M15_DiceGame.Service.UserDTO addNewUser(com.M15_DiceGame.Service.UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.M15_DiceGame.Service.UserDTO updateUser(com.M15_DiceGame.Service.UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameDTO saveNewGame(GameDTO game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.M15_DiceGame.Service.UserDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.M15_DiceGame.Service.UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.M15_DiceGame.Service.UserDTO> getLastRanking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.M15_DiceGame.Service.UserDTO> getFirstRanking() {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 */
	
}
