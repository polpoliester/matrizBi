
package org.itson.domaincomponent.dtos;

public class PlayerPickTileDTO {
    
    
    String playerName;
    
    public PlayerPickTileDTO(String playerName){
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
