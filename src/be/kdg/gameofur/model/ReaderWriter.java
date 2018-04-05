package be.kdg.gameofur.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * This class reads and writers the text files for saving purposes
 *
 * @author William Van Bouwel
 * @author Anouk As.
 */
public class ReaderWriter {
    private static final String fileHighscores = "Highscores.txt";
    private static final String fileBoard = "Board.txt";

    public void write(boolean isHighscore, String naam, String naam2, int moves, int turnsPlayer) {
        String fileName = getFile(isHighscore);
        if (fileName.equals(fileHighscores)) {
            try (BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileHighscores)))) {
                bf.write(naam + ";" + moves);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try (FileOutputStream out = new FileOutputStream(fileBoard)){
                Properties prop = System.getProperties();
                prop.setProperty("PieceP1", naam);
                prop.setProperty("PieceP2", naam2);
                prop.setProperty("moves", String.valueOf(moves));
                prop.setProperty("turns", String.valueOf(turnsPlayer));
                prop.store(out,"Board positions");
            } catch (IOException e){
                e.getMessage();
            }

        }
    }

    public String[] readHighscore(){
        String[] strings = new String[5];
        try (BufferedReader bf = Files.newBufferedReader(Paths.get(fileHighscores))){
            String regel = bf.readLine();
            while (regel != null){
                for (int i = 0; i < strings.length; i++){
                    strings[i] = regel;
                    regel = bf.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    String[] readBoard(String keuze){
        try (FileInputStream in = new FileInputStream(fileBoard)){
            Properties prop = new Properties();
            prop.load(in);
            //for player 1
            switch (keuze) {
                case "Player1": {
                    return prop.getProperty("PieceP1").split(";");
                }
                //for player 2
                case "Player2": {
                    return prop.getProperty("PieceP2").split(";");
                }
                //for turns
                case "Moves": {
                    return prop.getProperty("moves").split(";");
                }
                //for whose turn it is
                case "Turns": {
                    return prop.getProperty("turns").split(";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }

    private String getFile(boolean isHighscore) {
        if (isHighscore){
            return fileHighscores;
        } else {
            return fileBoard;
        }
    }
}
