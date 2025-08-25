package uk.ac.tees.cis2001.pocketbeasts.patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable for game state.
 */
public class GameObservable {
    private final List<GameObserver> observers = new ArrayList<>();

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.onGameStateChanged();
        }
    }
}