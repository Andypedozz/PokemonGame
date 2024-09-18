package test.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import interfaces.menu.Lobby;
import model.menu.LobbyImpl;

public class TestLobby {

    private Lobby lobby;
    
    @Test
    public void testSelectPokemon() {
        lobby = new LobbyImpl();
        System.out.println("********** TEST SELECT POKEMON **********");
        assertNull(lobby.getSelected());
        lobby.selectPokemon("Swampert");
        assertNotNull(lobby.getSelected());
        System.out.println();
    }
    
    @Test
    public void testGetSelected() {
    	lobby = new LobbyImpl();
    	System.out.println("********** TEST GET SELECTED **********");
        lobby.selectPokemon("Swampert");
        assertNotNull(lobby.getSelected());
        lobby.selectPokemon("Metagross");
        assertNotNull(lobby.getSelected());
        lobby.selectPokemon("Salamence");
        assertNotNull(lobby.getSelected());
        lobby.selectPokemon("Luxray");
        assertNotNull(lobby.getSelected());
        lobby.selectPokemon("Infername");
        assertNull(lobby.getSelected());
        lobby.selectPokemon("Lucario");
        assertNotNull(lobby.getSelected());
        lobby.selectPokemon("Blissey");
        assertNotNull(lobby.getSelected());
        System.out.println();
    }
    
    @Test
    public void testRemovePokemon() {
    	lobby = new LobbyImpl();
        System.out.println("********** TEST REMOVE POKEMON **********");
        lobby.selectPokemon("Salamence");
        lobby.addPokemon(0);
        lobby.selectPokemon("Infernape");
        lobby.addPokemon(0);
        lobby.selectPokemon("Metagross");
        lobby.addPokemon(0);
        assertEquals(lobby.removePokemon(0), 2);
        assertEquals(lobby.removePokemon(0), 1);
        lobby.selectPokemon("Infernape");
        lobby.addPokemon(0);
        assertEquals(lobby.removePokemon(0), 1);
        assertEquals(lobby.removePokemon(0), 0);
        assertEquals(lobby.removePokemon(0), -1);
        assertEquals(lobby.removePokemon(1), -1);
        System.out.println();
    }
    
    @Test
    public void testSelectFromTeam() {
    	lobby = new LobbyImpl();
    	System.out.println("********** TEST SELECT FROM TEAM **********");
        assertEquals(lobby.selectFromTeam(1, 0), false);
        assertEquals(lobby.selectFromTeam(1, 1), false);
        assertEquals(lobby.selectFromTeam(1, 2), false);
        assertEquals(lobby.selectFromTeam(2, 1), false);
        assertEquals(lobby.selectFromTeam(3, 1), false);
        assertEquals(lobby.selectFromTeam(-1, 1), false);
        assertEquals(lobby.selectFromTeam(1, -1), false);
        lobby.selectPokemon("Salamence");
        lobby.addPokemon(0);
        lobby.selectPokemon("Infernape");
        lobby.addPokemon(0);
        lobby.selectPokemon("Metagross");
        lobby.addPokemon(0);
        assertEquals(lobby.selectFromTeam(0, 0), true);
        assertEquals(lobby.selectFromTeam(0, 1), true);
        assertEquals(lobby.selectFromTeam(0, 2), true);
        assertEquals(lobby.removePokemon(0), 2);
        assertEquals(lobby.removePokemon(0), 1);
        assertEquals(lobby.removePokemon(0), 0);
        assertEquals(lobby.selectFromTeam(0, 0), false);
        assertEquals(lobby.selectFromTeam(0, 1), false);
        assertEquals(lobby.selectFromTeam(0, 2), false);
        System.out.println();
    }
    
    @Test
    public void testAddPokemon() {
    	lobby = new LobbyImpl();
    	System.out.println("********** TEST ADD POKEMON **********");
        lobby.selectPokemon("Swampert");
        assertEquals(lobby.getSelected(),lobby.getPokedex().getPokemon("Swampert"));
        assertEquals(lobby.addPokemon(0),0);
        lobby.selectPokemon("Blaziken");
        assertEquals(lobby.getSelected(),lobby.getPokedex().getPokemon("Blaziken"));
        assertEquals(lobby.addPokemon(0),1);
        lobby.selectPokemon("Salamence");
        assertEquals(lobby.getSelected(),lobby.getPokedex().getPokemon("Salamence"));
        assertEquals(lobby.addPokemon(0),2);
        lobby.selectPokemon("Metagross");
        assertEquals(lobby.getSelected(),lobby.getPokedex().getPokemon("Metagross"));
        assertEquals(lobby.addPokemon(0),-2);
        lobby.deselect();
        assertEquals(lobby.addPokemon(1),-1);
        lobby.selectPokemon("Blaziken");
        lobby.removePokemon(0);
        assertEquals(lobby.addPokemon(0),-3);
        System.out.println();
    }

    @Test
    public void testDeselect() {
    	lobby = new LobbyImpl();
    	System.out.println("********** TEST DESELECT **********");
        assertNotNull(lobby.getSelected());
        lobby.deselect();
        assertNull(lobby.getSelected());
        System.out.println();
    }

    


}
