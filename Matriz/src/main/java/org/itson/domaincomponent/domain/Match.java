/**
 * Match.java
 * Oct 12, 2023 5:59:39 PM
 */
package org.itson.domaincomponent.domain;

import org.itson.domaincomponent.dtos.PlayerPickTileDTO;
import org.itson.domaincomponent.exceptions.GameException;
import org.itson.domaincomponent.exceptions.MatchException;
import org.itson.domaincomponent.exceptions.PlayerException;
import org.itson.domaincomponent.exceptions.PoolException;
import org.itson.domaincomponent.interfaces.Game;

/**
 * @author Daniel Armando Peña Garcia ID:229185
 * @author Santiago Bojórquez Leyva ID:228475
 * @author Paul Alejandro Vazquez Cervantes ID:241400
 * @author Jose Eduardo Hinojosa Romero ID: 2356666
 */
public class Match implements Game {

    /**
     * An array of Player objects representing the participants in the match.
     */
    private Player[] players;

    /**
     * The game board where the match takes place.
     */
    private Board board;

    /**
     * The pool of game pieces or tiles used in the match.
     */
    private Pool pool;

    private final int defaultTilesAmount;

    /**
     * Indicates whether the game is currently in progress or not.
     *
     * If this variable is set to true, it means that the game is actively being
     * played. If it's set to false, the game is not currently in progress.
     */
    private boolean inGame = false;

    /**
     * Initializes a new Match with the specified players, board, and pool.
     *
     * @param players An array of Player objects representing the participants
     * in the match.
     * @param board The game board where the match takes place.
     * @param pool The pool of game pieces or tiles used in the match.
     * @param defaultTilesAmount The configuration of the Tiles amount.
     */
    public Match(Player[] players, Board board, Pool pool, int defaultTilesAmount) {
        this.players = players;
        this.board = board;
        this.pool = pool;
        this.defaultTilesAmount = defaultTilesAmount;
    }

    /**
     * This method starts the match by setting this match into the game
     * elements.
     *
     * @throws MatchException if there is an issue with setting the game
     * elements or if the match is already in progress.
     */
    public void startMatch() throws MatchException {
        if (isInGame()) {
            throw new MatchException("The match is already in progress and cannot be started again.");
        }

        this.setGameIntoGameElements();
        this.setInGame(true);
    }

    /**
     * Sets the current game instance into the game elements, including players
     * and the board.
     *
     * @throws MatchException if there is an issue with setting the game
     * elements.
     */
    private void setGameIntoGameElements() throws MatchException {
        setGameIntoPlayers();
        setGameIntoBoard();
        setGameIntoPool();
    }

    /**
     * Sets the current game instance for each player in the player list.
     *
     * @throws MatchException if there is an issue with setting the game for
     * players.
     */
    private void setGameIntoPlayers() throws MatchException {

        if (this.players == null || this.players.length < 1) {
            throw new MatchException("No players found in the match.");
        }

        for (Player player : players) {

            if (player == null) {
                throw new MatchException("Null player found in the match.");
            }

            player.setGame(this);
        }
    }

    public boolean verifyConfiguration() {
        return !(defaultTilesAmount <= 2 || defaultTilesAmount >= 7);
    }

    public int getDefaultTilesAmount() {
        return defaultTilesAmount;
    }

    /**
     * Sets the current game instance for the game board.
     *
     * @throws MatchException if there is an issue with setting the game for the
     * board.
     */
    private void setGameIntoBoard() throws MatchException {

        if (this.board == null) {
            throw new MatchException("No game board found in the match.");
        }

        board.setGame(this);
    }

    /**
     * Sets the current game instance for the game pool (if applicable).
     *
     * @throws MatchException if there is an issue with setting the game for the
     * pool.
     */
    private void setGameIntoPool() throws MatchException {

        if (this.pool == null) {
            throw new MatchException("No game pool found in the match.");
        }

        pool.setGame(this);
    }

    /**
     * Retrieves the index of the player whose turn it currently is in the game.
     *
     * @return The index of the player whose turn it is, or -1 if no player is
     * currently in turn.
     */
    private int getPlayerIndexInTurn() {
        for (int i = 0; i < this.players.length; i++) {
            Player playerSelected = this.players[i];
            if (playerSelected != null && playerSelected.isTurn()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Passes the current player's turn to the next player in line.
     *
     * @throws MatchException if there is no player currently in turn,
     * indicating an invalid turn change.
     */
    public void passTurn() throws MatchException {
        int playerIndexInTurn = this.getPlayerIndexInTurn();
        int nextTurn;

        if (playerIndexInTurn == -1) {
            throw new MatchException("No player is currently in turn, cannot pass the turn.");
        }

        if (playerIndexInTurn == (this.players.length - 1)) {
            nextTurn = 0;
        } else {
            nextTurn = playerIndexInTurn + 1;
        }
    }

    /**
     * Adds a player to the match if there is space available. If the match
     * becomes full after adding the last player, the match starts
     * automatically.
     *
     * @param player The player to add to the match.
     * @throws MatchException if the player is null, if the maximum number of
     * players (4) in the match has been reached, or if the match is already in
     * progress.
     */
    public void addPlayer(Player player) throws MatchException {
        if (isInGame()) {
            throw new MatchException("The match is already in progress, and new players cannot be added.");
        }

        if (player == null) {
            throw new MatchException("Null player found.");
        }

        if (isMatchFull()) {
            throw new MatchException("The maximum number of players in the match (4) has been reached.");
        }

        addPlayerToMatch(player);

        if (isMatchFull()) {
            startMatch();
        }
    }

    /**
     * Checks if the match is full (all player slots are occupied).
     *
     * @return true if the match is full, false otherwise.
     */
    private boolean isMatchFull() {
        for (Player player : players) {
            if (player == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a player to the match in the first available slot.
     *
     * @param player The player to add.
     */
    private void addPlayerToMatch(Player player) {
        for (int i = 0; i < 4; i++) {
            if (players[i] == null) {
                players[i] = player;
                return;
            }
        }
    }

    /**
     * Removes a player from the match.
     *
     * @param player The player to remove from the match.
     * @throws MatchException if the player is not found in the match or if the
     * match is already in progress.
     */
    public void deletePlayer(Player player) throws MatchException {
        if (isInGame()) {
            throw new MatchException("The match is already in progress, and players cannot be removed.");
        }

        if (player == null) {
            throw new MatchException("Null player cannot be removed from the match.");
        }

        int foundIndex = findPlayerIndex(player);

        if (foundIndex != -1) {
            shiftPlayers(foundIndex);
        } else {
            throw new MatchException("Player not found in the match.");
        }
    }

    public void distributeTiles() throws MatchException, PoolException {

        if (players == null) {
            throw new MatchException("The Player's list recived was null.");
        }

        if (players.length == 0) {
            throw new MatchException("The Player's list recived was empty.");
        }

        for (Player player : players) {
            for (int j = 0; j < defaultTilesAmount; j++) {
                player.tiles.add(pool.getRandomTile());
            }
        }
    }

    /**
     * Finds the index of a player in the player array.
     *
     * @param player The player to search for.
     * @return The index of the player in the array, or -1 if not found.
     */
    private int findPlayerIndex(Player player) {
        for (int i = 0; i < 4; i++) {
            if (this.players[i] == player) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Shifts the remaining players to fill the gap created by removing a
     * player.
     *
     * @param index The index of the player to remove.
     */
    private void shiftPlayers(int index) {
        for (int i = index; i < 3; i++) {
            this.players[i] = this.players[i + 1];
        }
        this.players[3] = null;
    }

    /**
     * Gets the array of players currently participating in the game.
     *
     * @return An array of Player objects representing the players in the game.
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Sets the array of players for the game.
     *
     * @param players An array of Player objects to set as the players in the
     * game.
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Gets the game board.
     *
     * @return The Board object representing the game board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets the game board for the game.
     *
     * @param board The Board object to set as the game board.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Gets the game pool, if available.
     *
     * @return The Pool object representing the game's tile pool, or null if
     * there is no tile pool.
     */
    public Pool getPool() {
        return pool;
    }

    /**
     * Sets the game pool, if available.
     *
     * @param pool The Pool object to set as the game's tile pool, or null if
     * there is no tile pool.
     */
    public void setPool(Pool pool) {
        this.pool = pool;
    }

    /**
     * Checks whether the game is currently in progress.
     *
     * @return true if the game is actively being played, false if it is not in
     * progress.
     */
    public boolean isInGame() {
        return inGame;
    }

    /**
     * Sets the game's in-progress status.
     *
     * @param inGame A boolean value, where true indicates that the game is
     * actively being played, and false indicates that the game is not currently
     * in progress.
     */
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * Allows players to pick a tile from the game's pool if the match is not
     * yet in progress.
     *
     * @param playerDTO A data transfer object representing the player's choice.
     * @throws GameException if: - No game pool is found in the match. - The
     * match is already in progress, and players cannot pick tiles.
     */
    public void pickTileOfPool(PlayerPickTileDTO playerDTO) throws GameException {

        try {

            if (isInGame()) {
                throw new MatchException("The match is already in progress, and players cannot pick tiles.");
            }

            if (this.pool == null) {
                throw new MatchException("No game pool found in the match.");
            }

            for (Player player : this.players) {
                if (player != null && player.isTurn()) {
                    this.pool.isPoolEmpty();
                    Tile tilePicked = this.pool.getRandomTile();
                    player.addTile(tilePicked);
                    this.passTurn();
                    break;
                }
            }
        } catch (PoolException | PlayerException | MatchException e) {
            throw new GameException(e.getMessage(), e);
        }

    }
}
