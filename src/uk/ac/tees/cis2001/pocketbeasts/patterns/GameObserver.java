package uk.ac.tees.cis2001.pocketbeasts.patterns;

/**
 * Observer interface for game events.
 */
public interface GameObserver {
    void onGameStateChanged();
}