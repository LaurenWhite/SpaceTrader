package edu.gatech.macpack.spacetrader.entity;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * Creates an interactor class for the database
 */
public class DatabaseInteractor {

    public static DatabaseInteractor dbInteractor = new DatabaseInteractor();

    private FirebaseFirestore database;
    public Game game;

    private DatabaseInteractor() {
        // Access a Cloud Firestore instance from your Activity
        database = FirebaseFirestore.getInstance();
    }

    /**
     * Creates a new game object with generated values
     */
    public void createGame() {
        game = new Game(true);
    }

    /**
     * Loads game information from the Firebase Cloud Storage
     *
     * @param username key to the game data in the database
     */
    public void loadGame(String username) {
        DocumentReference docRef = database.collection("user_saves").document(username);

        //Source source = Source.CACHE;


        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    // Document found in the offline cache
                    DocumentSnapshot document = task.getResult();
                    Log.d(TAG, "Cached document data: " + document.getData());

                    game = new Game(false);

                    Object systems = document.get("solar_systems");
                    @SuppressWarnings("unchecked")
                    ArrayList<Object> solarSystemData = (ArrayList<Object>) systems;
                    List<SolarSystem> solarSystemList = loadSolarSystems(Objects.requireNonNull(solarSystemData));
                    game.setSolarSystems(solarSystemList);


                    Object player = document.get("player");
                    @SuppressWarnings("unchecked") HashMap<String, Object> playerData = (HashMap<String, Object>) player;
                    Player playerObj = loadPlayer(Objects.requireNonNull(playerData));
                    game.setPlayer(playerObj);

                    Object difficultyData = document.get("difficulty");
                    DifficultyType difficulty = DifficultyType.valueOf((String) difficultyData);
                    game.setDifficulty(difficulty);

                    System.out.println(game.getPlayer());
                    System.out.println(game.getSolarSystems());
                    System.out.println(game);
                } else {
                    Log.d(TAG, "Cached get failed: ", task.getException());
                }
            }
        });

//        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                //game = documentSnapshot.toObject(Game.class);
//
//                game = new Game(false);
//
//                Object systems = documentSnapshot.get("solar_systems");
//                @SuppressWarnings("unchecked")
//                ArrayList<Object> solarSystemData = (ArrayList<Object>) systems;
//                List<SolarSystem> solarSystemList = loadSolarSystems(Objects.requireNonNull(solarSystemData));
//                game.setSolarSystems(solarSystemList);
//
//
//                Object player = documentSnapshot.get("player");
//                @SuppressWarnings("unchecked") HashMap<String, Object> playerData = (HashMap<String, Object>) player;
//                Player playerObj = loadPlayer(Objects.requireNonNull(playerData));
//                game.setPlayer(playerObj);
//
//                Object difficultyData = documentSnapshot.get("difficulty");
//                DifficultyType difficulty = DifficultyType.valueOf((String) difficultyData);
//                game.setDifficulty(difficulty);
//
//                System.out.println(game.getPlayer());
//                System.out.println(game.getSolarSystems());
//                System.out.println(game);
//            }
//        });

//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                //game = documentSnapshot.toObject(Game.class);
//
//                DocumentSnapshot document = task.getResult();
//
//                game = new Game(false);
//
//                Object systems = document.get("solar_systems");
//                @SuppressWarnings("unchecked")
//                ArrayList<Object> solarSystemData = (ArrayList<Object>) systems;
//                List<SolarSystem> solarSystemList = loadSolarSystems(Objects.requireNonNull(solarSystemData));
//                game.setSolarSystems(solarSystemList);
//
//
//                Object player = document.get("player");
//                @SuppressWarnings("unchecked") HashMap<String, Object> playerData = (HashMap<String, Object>) player;
//                Player playerObj = loadPlayer(Objects.requireNonNull(playerData));
//                game.setPlayer(playerObj);
//
//                Object difficultyData = document.get("difficulty");
//                DifficultyType difficulty = DifficultyType.valueOf((String) difficultyData);
//                game.setDifficulty(difficulty);
//
//                System.out.println(playerObj);
//                System.out.println(systems);
//                System.out.println(game);
//            }
//        });
    }

    private List<SolarSystem> loadSolarSystems(ArrayList<Object> solarSystemData) {
        List<SolarSystem> systems = new ArrayList<>();

        for (int i = 0; i < solarSystemData.size(); i++) {

            @SuppressWarnings("unchecked") HashMap<String, Object> data = (HashMap<String, Object>) solarSystemData.get(i);
            String name = (String) data.get("name");

            ArrayList<Object> locationData = (ArrayList<Object>) data.get("location");
            Long xCoordinate = (Long) locationData.get(0);
            Long yCoordinate = (Long) locationData.get(1);

            List<Integer> location = new ArrayList<>();
            location.add(xCoordinate.intValue());
            location.add(yCoordinate.intValue());

            List<Planet> planets = new ArrayList<>();
            ArrayList<Object> planetData = (ArrayList<Object>) data.get("planets");
            for(int j = 0; j < planetData.size(); j++) {
                HashMap<String, Object> planetDataMap = (HashMap<String, Object>) planetData.get(j);
                Planet planetObj = loadPlanet(planetDataMap);
                planets.add(planetObj);
            }

            SolarSystem newSystem = new SolarSystem(name, location, planets);
            //planets.add(new Planet(newSystem));
            //planets.add(new Planet(newSystem));
            //newSystem.setPlanets(planets);

            systems.add(newSystem);
        }

        return systems;
    }

    private Player loadPlayer(HashMap<String, Object> playerData) {
        String name = (String) playerData.get("name");

        int availableSkillPoints = ((Long) playerData.get("availableSkillPoints")).intValue();
        int pilotPoints = ((Long) playerData.get("pilotPoints")).intValue();
        int fighterPoints = ((Long) playerData.get("fighterPoints")).intValue();
        int traderPoints = ((Long) playerData.get("traderPoints")).intValue();
        int engineerPoints = ((Long) playerData.get("engineerPoints")).intValue();
        int credits = ((Long) playerData.get("credits")).intValue();

        @SuppressWarnings("unchecked") HashMap<String, Object> spaceshipData = (HashMap<String, Object>) playerData.get("spaceShip");
        SpaceShip shipObj = loadSpaceShip(spaceshipData);

        @SuppressWarnings("UnnecessaryLocalVariable") Player player = new Player(name,
                credits, availableSkillPoints,
                pilotPoints, fighterPoints, traderPoints, engineerPoints,
                shipObj);
        return player;
    }

    private SpaceShip loadSpaceShip(HashMap<String, Object> shipData) {
        SpaceShipType shipType = SpaceShipType.valueOf((String) shipData.get("shipType"));

        Map<String, CargoItem> cargo = new HashMap<>();
        @SuppressWarnings("unchecked") HashMap<String, Object> cargoData = (HashMap<String, Object>) shipData.get("cargo");

        for (String key : cargoData.keySet()) {
            @SuppressWarnings("unchecked") HashMap<String, Object> data = (HashMap<String, Object>) cargoData.get(key);
            int quanitity = ((Long) data.get("quantity")).intValue();
            int price = ((Long) data.get("price")).intValue();
            TradeGood good = TradeGood.valueOf(key);
            cargo.put(key, new CargoItem(good, quanitity, price));
        }


        int weight = ((Long) shipData.get("weight")).intValue();
        int fuel = ((Long) shipData.get("fuel")).intValue();
        Planet location = game.getStartingLocation();

        return new SpaceShip(shipType, cargo, weight, location, fuel);
    }

    private Planet loadPlanet(HashMap<String, Object> planetData) {

        String name = (String) planetData.get("name");

        ArrayList<Object> locationData = (ArrayList<Object>) planetData.get("location");
        Long xCoordinate = (Long) locationData.get(0);
        Long yCoordinate = (Long) locationData.get(1);

        List<Integer> location = new ArrayList<>();
        location.add(xCoordinate.intValue());
        location.add(yCoordinate.intValue());

        Object techLevelVal = planetData.get("techLevel");
        TechLevel tl = TechLevel.valueOf((String) techLevelVal);

        Object resourceVal = planetData.get("resource");
        ResourceType resource = ResourceType.valueOf((String) resourceVal);

        int traderEventChance = ((Long) planetData.get("traderEventChance")).intValue();

        Map<String, MarketItem> market = new HashMap<>();
        HashMap<String, Object> marketData = (HashMap<String, Object>) planetData.get("market");

        for (String key : marketData.keySet()) {
            HashMap<String, Object> data = (HashMap<String, Object>) marketData.get(key);
            int quantity = ((Long) data.get("quantity")).intValue();
            int price = ((Long) data.get("price")).intValue();
            TradeGood good = TradeGood.valueOf(key);
            market.put(key, new MarketItem(good, quantity, price));
        }

        String parentName = (String) planetData.get("parentName");

        ArrayList<Object> parentLocationData = (ArrayList<Object>) planetData.get("parentLocation");
        Long pxCoordinate = (Long) parentLocationData.get(0);
        Long pyCoordinate = (Long) parentLocationData.get(1);

        List<Integer> parentLocation = new ArrayList<>();
        location.add(pxCoordinate.intValue());
        location.add(pyCoordinate.intValue());

        return new Planet(name, location, tl, resource,
                traderEventChance, market, parentName, parentLocation);
    }

    /**
     * Writes game data to the Firebase Cloud Storage using username as a key
     *
     * @param username key to entry, user's unique name
     * @param game     data to write to Firebase entry
     */
    public void saveGame(String username, Game game) {
        // Create a save entry that stores the game
        Map<String, Object> userData = new HashMap<>();
        userData.put("solar_systems", game.getSolarSystems());
        userData.put("player", game.getPlayer());
        userData.put("difficulty", game.getDifficulty());
        userData.put("starting_location", game.getStartingLocation());


        // Add a new document with a username as ID
        database.collection("user_saves").document(username)
                .set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Game save successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing user save ", e);
                    }
                });
    }

}
