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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class DatabaseInteractor {

    private FirebaseFirestore database;

    public DatabaseInteractor() {
        // Access a Cloud Firestore instance from your Activity
        database = FirebaseFirestore.getInstance();
    }


    public void saveGame(String username, Game game) {
        // Create a save entry that stores the game
        Map<String, Object> userData = new HashMap<>();
        userData.put("game_save", game.getGameInstance());


        // Add a new document with a username as ID
        database.collection("user_saves").document(username)
                .set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing user save ", e);
                    }
                });
    }

    public void loadGame(String username) {
        DocumentReference docRef = database.collection("user_saves").document(username);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Game game = documentSnapshot.toObject(Game.class);
                new Game(game);
            }
        });


    }

}
