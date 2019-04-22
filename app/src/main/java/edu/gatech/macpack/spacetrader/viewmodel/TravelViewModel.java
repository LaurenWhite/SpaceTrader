package edu.gatech.macpack.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.macpack.spacetrader.entity.DatabaseInteractor;
import edu.gatech.macpack.spacetrader.entity.Game;
import edu.gatech.macpack.spacetrader.entity.Planet;
import edu.gatech.macpack.spacetrader.entity.Player;
import edu.gatech.macpack.spacetrader.entity.SolarSystem;
import edu.gatech.macpack.spacetrader.entity.SpaceShip;
import edu.gatech.macpack.spacetrader.entity.Traveler;

/**
 * Travel view model class
 */
public class TravelViewModel extends AndroidViewModel {

    private final Game game = DatabaseInteractor.dbInteractor.game;
    private final Player player = game.getPlayer();
    private final SpaceShip ship = player.getSpaceShip();

    private Planet currentPlanet;
    private List<SolarSystem> systems;
    private List<String> solarSystemNames;

    private List<Planet> planets;
    private List<String> planetNames;
    private Planet selectedPlanet;
    private SolarSystem chosenSystem;

    private Traveler traveler;


    /**
     * This constructor creates an instance of the TravelViewModel class
     * @param application param to pass to the constructor of AndroidViewModel (super)
     */
    public TravelViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Intializes all of the first use variables
     */
    public void initializeFields() {

        traveler = new Traveler(ship);
        systems = traveler.systemsInRange();

        currentPlanet = ship.getLocation();

        solarSystemNames = new ArrayList<>();
        planetNames = new ArrayList<>();
    }

    /**
     * Updates list of system to those only in fuel range
     */
    public void updateSystemsInRange() {
        traveler = new Traveler(ship);
        systems = traveler.systemsInRange();

        solarSystemNames.clear();
        for (SolarSystem system : systems) {
            solarSystemNames.add(system.getName());
        }
    }

    /**
     * @return solar system names
     */
    public List<String> getSolarSystemNames() {
        return solarSystemNames;
    }

    /**
     * Updates planet list to contain those of the current system
     * @param systemIdx index of the current system in array
     */
    public void updatePlanetList(int systemIdx) {
        planetNames.clear();

        chosenSystem = systems.get(systemIdx);
        for (Planet planet : chosenSystem.getPlanets()) {
            planetNames.add(planet.getName());
        }
    }

    /**
     * @return planet names
     */
    public List<String> getPlanetNames() {
        return planetNames;
    }

    /**
     * Update selected planet to be the one chosen by user in table
     * @param planetIdx index of planet in planet list
     */
    public void updateSelectedPlanet(int planetIdx) {
        planets = chosenSystem.getPlanets();
        selectedPlanet = planets.get(planetIdx);
    }

    /**
     * @return current planet
     */
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    /**
     * @return selected planet
     */
    public Planet getSelectedPlanet() {
        return selectedPlanet;
    }

    /**
     * Move the ship from one location to another
     */
    public void travel() {
        traveler = new Traveler(ship, currentPlanet, selectedPlanet);
        traveler.travel();
        currentPlanet = selectedPlanet;
    }

    /**
     * Determine based on chances if an event will occur during travel
     * @return if the event occurred or not
     */
    public boolean eventOccurred() {
        return currentPlanet.getTraderEventChance() >= 2
                && currentPlanet.getTraderEventChance() <= 5;
    }

    /**
     * @return ship fuel
     */
    public int getFuel() {
        return ship.getFuel();
    }
}
