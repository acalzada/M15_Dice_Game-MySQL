package com.M15_DiceGame.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.M15_DiceGame.Domain.Game;

public interface GameDAO extends JpaRepository<Game, Long>{

}