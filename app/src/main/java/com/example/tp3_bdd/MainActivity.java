package com.example.tp3_bdd;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SportsDatabaseHelper databaseHelper;
    private SportsAdapter adapter;
    private List<Sports> sportsList;
    private EditText nameInput;
    private EditText categoryInput;
    private EditText numberOfPlayersInput;
    private Button addSportsButton, modifySportsButton, deleteSportsButton;
    private ListView sportsListView;
    private Sports selectedSport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.name_input);
        categoryInput = findViewById(R.id.category_input);
        numberOfPlayersInput = findViewById(R.id.number_of_players_input);
        addSportsButton = findViewById(R.id.add_sports_button);
        modifySportsButton = findViewById(R.id.modify_sports_button);
        deleteSportsButton = findViewById(R.id.delete_sports_button);
        sportsListView = findViewById(R.id.sports_list_view);

        databaseHelper = new SportsDatabaseHelper(this);
        loadSports();

        addSportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSports();
            }
        });

        modifySportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifySports();
            }
        });

        deleteSportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSports();
            }
        });

        sportsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSport = sportsList.get(position);
                nameInput.setText(selectedSport.getName());
                categoryInput.setText(selectedSport.getCategory());
                numberOfPlayersInput.setText(String.valueOf(selectedSport.getNumberOfPlayers()));
            }
        });
    }

    private void addSports() {
        String name = nameInput.getText().toString().trim();
        String category = categoryInput.getText().toString().trim();
        String numberOfPlayersText = numberOfPlayersInput.getText().toString().trim();

        if (name.isEmpty() || category.isEmpty() || numberOfPlayersText.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        int numberOfPlayers = Integer.parseInt(numberOfPlayersText);
        Sports sport = new Sports(-1, name, category, numberOfPlayers);
        databaseHelper.addSports(sport);
        loadSports();
        clearInputs();
    }

    private void modifySports() {
        if (selectedSport != null) {
            String name = nameInput.getText().toString().trim();
            String category = categoryInput.getText().toString().trim();
            String numberOfPlayersText = numberOfPlayersInput.getText().toString().trim();

            if (name.isEmpty() || category.isEmpty() || numberOfPlayersText.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            int numberOfPlayers = Integer.parseInt(numberOfPlayersText);
            selectedSport.setName(name);
            selectedSport.setCategory(category);
            selectedSport.setNumberOfPlayers(numberOfPlayers);
            databaseHelper.updateSports(selectedSport);
            loadSports();
            clearInputs();
        } else {
            Toast.makeText(this, "No sport selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteSports() {
        if (selectedSport != null) {
            databaseHelper.deleteSports(selectedSport.getId());
            loadSports();
            clearInputs();
        } else {
            Toast.makeText(this, "No sport selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSports() {
        sportsList = databaseHelper.getAllSports();
        adapter = new SportsAdapter(this, sportsList);
        sportsListView.setAdapter(adapter);
    }

    private void clearInputs() {
        nameInput.setText("");
        categoryInput.setText("");
        numberOfPlayersInput.setText("");
        selectedSport = null;
    }
}
