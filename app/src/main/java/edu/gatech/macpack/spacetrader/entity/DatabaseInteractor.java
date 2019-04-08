package edu.gatech.macpack.spacetrader.entity;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Space;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Creates an interactor class for the database
 */
public class DatabaseInteractor {

    public static DatabaseInteractor dbInteractor = new DatabaseInteractor();

    private FirebaseFirestore database;
    public Game game;

    public DatabaseInteractor() {
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
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //game = documentSnapshot.toObject(Game.class);

                game = new Game(false);

                Object systems = documentSnapshot.get("solar_systems");
                ArrayList<Object> solarSystemData = (ArrayList<Object>) systems;
                List<SolarSystem> solarSystemList = loadSolarSystems(solarSystemData);
                game.setSolarSystems(solarSystemList);


                Object player = documentSnapshot.get("player");
                HashMap<String, Object> playerData = (HashMap<String, Object>) player;
                Player playerObj = loadPlayer(playerData);
                game.setPlayer(playerObj);

                Object difficultyData = documentSnapshot.get("difficulty");
                DifficultyType difficulty = DifficultyType.valueOf((String) difficultyData);
                game.setDifficulty(difficulty);

                System.out.println(playerObj);
                System.out.println(systems);
                System.out.println(game);
            }
        });
    }

    private List<SolarSystem> loadSolarSystems(ArrayList<Object> solarSystemData) {
        List<SolarSystem> systems = new ArrayList<>();

        for (int i = 0; i < solarSystemData.size(); i++) {

            HashMap<String, Object> data = (HashMap<String, Object>) solarSystemData.get(i);
            String name = (String) data.get("name");

            ArrayList<Object> locationData = (ArrayList<Object>) data.get("location");
            Long coord1 = (Long) locationData.get(0);
            Long coord2 = (Long) locationData.get(1);

            List<Integer> location = new ArrayList<>();
            location.add(coord1.intValue());
            location.add(coord2.intValue());

            List<Planet> planets = new ArrayList<>();

            // TODO: actually load planets
            SolarSystem newSystem = new SolarSystem(name, location, planets);
            planets.add(new Planet(newSystem));
            planets.add(new Planet(newSystem));
            newSystem.setPlanets(planets);

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
        int credits = ((Long) playerData.get("engineerPoints")).intValue();

        HashMap<String, Object> spaceshipData = (HashMap<String, Object>) playerData.get("spaceShip");
        SpaceShip shipObj = loadSpaceShip(spaceshipData);

        Player player = new Player(name,
                availableSkillPoints,
                pilotPoints, fighterPoints, traderPoints, engineerPoints,
                shipObj);
        return player;
    }

    private SpaceShip loadSpaceShip(HashMap<String, Object> shipData) {
        SpaceShipType shipType = SpaceShipType.valueOf((String) shipData.get("shipType"));
        //TODO: Really load cargo
        Map<String, CargoItem> cargo = new HashMap<>();
        HashMap<String, Object> cargoData = (HashMap<String, Object>) shipData.get("cargo");

        for (String key : cargoData.keySet()) {
            HashMap<String, Object> data = (HashMap<String, Object>) cargoData.get(key);
            int quanitity = ((Long) data.get("quantity")).intValue();
            int price = ((Long) data.get("price")).intValue();
            TradeGood good = TradeGood.valueOf(key);
            cargo.put(key, new CargoItem(good, quanitity, price));
        }


        int weight = ((Long) shipData.get("weight")).intValue();
        int fuel = ((Long) shipData.get("fuel")).intValue();
        //TODO: get planet from solar systems
        Planet location = game.getStartingLocation();

        return new SpaceShip(shipType, cargo, weight, location, fuel);
    }

    //private Planet loadPlanet() {}

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


//    public void saveGame(String username, Game game) {
//        // Create a save entry that stores the game
//        Map<String, Object> userData = new HashMap<>();
//        userData.put("game_save", game);
//        //userData.put("player", game.getPlayer());
//
//
//        // Add a new document with a username as ID
//        database.collection("user_saves").document(username)
//                .set(userData)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "Game save successfully written!");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error writing user save ", e);
//                    }
//                });
//    }

}
