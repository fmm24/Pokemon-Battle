package Battle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Battle extends Application {


    public static final String separator = "--------------------------------------\n";

    public static void main(String[] args) {
        launch(args);
    }


    private static List<Pokemon> pokemons = new ArrayList<>();
    private static Pokemon first_pokemon = null;
    private static Pokemon second_pokemon = null;

    public static void load()
    {
        try{
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\miljo\\Desktop\\Faks\\Pokemon Battle\\src\\pokemon.txt"));

            for(String line:lines)
            {
                String[] tokens = line.split(",");
                System.out.println(Arrays.toString(tokens));
                String type = tokens[0].trim();
                String name = tokens[1].trim();
                int level =Integer.parseInt(tokens[2].trim());
                String first_spell = tokens[3].trim();
                int first_spell_strength = Integer.parseInt(tokens[4].trim());
                String second_spell = tokens[5].trim();
                int second_spell_strength = Integer.parseInt(tokens[6].trim());
                if (type.equals("p"))
                {
                    pokemons.add(new Player(name,level,new Spell(first_spell_strength,first_spell),new Spell(second_spell_strength,second_spell)));
                }else if(type.equals("e"))
                {
                    pokemons.add(new CpuPokemon(name,level,new Spell(first_spell_strength,first_spell),new Spell(second_spell_strength,second_spell)));
                }else
                {
                    System.out.println("Wrong type inserted!");
                }
            }

        } catch (IOException e) {
            System.out.println("File failed to load");
        }


    }



    public void start(Stage primaryStage) throws Exception
    {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10,10,10,10));
        Button btLoad  = new Button("Load");
        TextArea taPokemon = new TextArea();
        TextArea taBattle = new TextArea();
        taBattle.setPrefHeight(400);

        RadioButton rbFirstSpell = new RadioButton("First Spell");
        rbFirstSpell.setSelected(true);
        RadioButton rbSecondSpell = new RadioButton("Second Spell");
        Button btChooseSpell = new Button("Choose Spell");
        ToggleGroup tg = new ToggleGroup();
        rbFirstSpell.setToggleGroup(tg);
        rbSecondSpell.setToggleGroup(tg);
        Button btSimulate = new Button("Simulate");

        HBox hbBottom = new HBox(10);
        hbBottom.getChildren().addAll(btChooseSpell, btSimulate);


        btLoad.setOnAction(e ->{

            load();
            Collections.sort(pokemons);
            for(Pokemon p : pokemons)
                taPokemon.appendText(p + "\n");


            first_pokemon = pokemons.get(0);

            for(Pokemon p : pokemons)
            {
                if (p instanceof CpuPokemon)
                {
                    second_pokemon =p;
                    break;
                }
            }

            if (second_pokemon  == null)
            {
                System.out.println("Error");
            }

            rbFirstSpell.setText(first_pokemon.getFirst_spell().toString());
            rbSecondSpell.setText(first_pokemon.getSecond_spell().toString());

            taPokemon.appendText("\n Chosen pokemon : \n" + first_pokemon + "\n");
            taPokemon.appendText(second_pokemon + "\n");


        });

        btChooseSpell.setOnAction(e -> {

            if(rbFirstSpell.isSelected())
            {
                ((Player)first_pokemon).setSelected_spell(1);
                taBattle.appendText("[Player] has chosen the spell : " + first_pokemon.getFirst_spell()+"\n");
                taBattle.appendText(separator);
            }else if(rbSecondSpell.isSelected())
            {
                ((Player)first_pokemon).setSelected_spell(2);
                taBattle.appendText("[Player] has chose the spell : " + first_pokemon.getSecond_spell()+"\n");
                taBattle.appendText(separator);
            }else{
                System.out.println("Error");
            }



        });

        btSimulate.setOnAction(e -> {

            if(first_pokemon.getLife_points() <= 0 )
            {
                taBattle.appendText("The CPU has won the battle!\n");
            }else if(second_pokemon.getLife_points()<= 0)
            {
                taBattle.appendText("The player has won the battle!\n");
            }else{

                first_pokemon.castSpell(second_pokemon);
                second_pokemon.castSpell(first_pokemon);
                taBattle.appendText(" " + first_pokemon + " has cast " + first_pokemon.getLast_cast_spell() + "\n");
                taBattle.appendText(" " + second_pokemon + " has cast " + second_pokemon.getLast_cast_spell() + "\n");
                taBattle.appendText("The player has " + first_pokemon.getLife_points() + " life points remaining\n");
                taBattle.appendText("The CPU has " + second_pokemon.getLife_points()+ " life points remaining\n");
                taBattle.appendText(separator);
            }
        });

        root.getChildren().addAll(btLoad,taPokemon,taBattle,rbFirstSpell,rbSecondSpell,hbBottom);


        Scene scene = new Scene(root,600,900);
        primaryStage.setTitle("Pokemon Battle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
