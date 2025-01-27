package com.museum.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.museum.data.Artwork;
import com.museum.enumerator.ArtworkType;
import com.museum.enumerator.StealingToolType;

public class ArtworkTest {
    
    @Test
    public void testCanBeTaken()
    {
        //testo caso artwork = PAINTING
        Artwork artwork = new Artwork("prova", 0, 0, ArtworkType.PAINTING);
        
        assertTrue(artwork.canBeTaken(StealingToolType.KNIFE));
        assertFalse(artwork.canBeTaken(StealingToolType.HAMMER));
        assertFalse(artwork.canBeTaken(StealingToolType.LASER_CONTROLLER));

        //testo caso artwork = SCULPTURE
        artwork = new Artwork("prova", 0, 0, ArtworkType.SCULPTURE);
        
        assertFalse(artwork.canBeTaken(StealingToolType.KNIFE));
        assertTrue(artwork.canBeTaken(StealingToolType.HAMMER));
        assertFalse(artwork.canBeTaken(StealingToolType.LASER_CONTROLLER));

        //testo caso artwork = JEWELRY
        artwork = new Artwork("prova", 0, 0, ArtworkType.JEWELRY);
        
        assertFalse(artwork.canBeTaken(StealingToolType.KNIFE));
        assertFalse(artwork.canBeTaken(StealingToolType.HAMMER));
        assertTrue(artwork.canBeTaken(StealingToolType.LASER_CONTROLLER));
    }
}
