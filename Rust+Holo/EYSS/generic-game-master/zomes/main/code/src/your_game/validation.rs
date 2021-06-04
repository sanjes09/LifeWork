
use crate::game::Game;
use crate::game_move::Move;
use crate::your_game::MoveType;
use hdk::holochain_persistence_api::cas::content::Address;
use super::{
    GameState,
};
use crate::your_game::state::Piece;



/**
 *
 * To implement your own custom rule validation all you need to do is re-implement the function `is_valid` on `Move`
 * 
 * This function  takes the current game and the game state (which includes all the existing moves) 
 * and determines if a new candidate move is valid. Typically this will involve first matching on the move type
 * and then determining if the move is valid.
 * 
 * It function must return Ok(()) if a move is valid and Err("Some error string") for an invalid move.
 * It is useful to provide descriptive error strings as these can be visible to the end user.
 *
 */


impl Move {
    pub fn is_valid(&self, game: Game, game_state: GameState) -> Result<(), String> {
        // <<DEVCAMP-TODO>> Check if a move is valid given the current game and its state
        is_player_turn(self.author.clone(), &game, &game_state)?;
        match self.move_type{
            MoveType::Place{x,y} => {
                let pos = Piece{x,y};
                pos.is_in_bounds()?;
                pos.is_empty(&game_state)?;
                Ok(())
            }
        }
    }
}

fn is_player_turn(player: Address, game: &Game, game_state: &GameState) -> Result<(), String>{
    let moves = &game_state.moves;
    match moves.last(){
        Some(last_move) =>{
            if last_move.author == player{
                Err("it is not this player turn".into())
            }else{
                Ok(())
            }
        },
        None => {
            if game.player_2 == player{
                Ok(())
            }else{
                Err("Player 2 must make the frst move".into())
            }
        }
    }   
}

impl PartialEq for Piece{
    fn eq(&self, other: &Self) -> bool{
        self.x == other.x && self.y == other.y
    }
}

const BOARD_SIZE: usize = 3;

impl Piece{
    pub fn is_in_bounds(&self) -> Result<(), String>{
        if self.x < BOARD_SIZE && self.y < BOARD_SIZE{
            Ok(())
        }else{
            Err("Piece is not in bounds".into())
        }
    }

    pub fn is_empty(&self, game_state: &GameState) -> Result<(), String>{
        if game_state.player_1_moves.contains(self) || game_state.player_2_moves.contains(self){
            Err("location is not empty".into())
        }else{
            Ok(())
        }
    }
}
