package is.ru.ANDR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joezombie on 19.9.2014.
 */
public class Pack {
    protected int id;
    protected String name;
    protected String description;
    protected String fileName;
    protected List<Challenge> challenges;

    public Pack(int id, String name, String description, String fileName){
        this.id = id;
        this.name = name;
        this.description = description;
        this.fileName = fileName;
        this.challenges = new ArrayList<Challenge>();
    }

    public Pack(int id, String name, String description, String fileName, List<Challenge> challenges){
        this.id = id;
        this.name = name;
        this.description = description;
        this.fileName = fileName;
        this.challenges = challenges;
    }

    public void addChallenge(Challenge challenge){
        challenges.add(challenge);
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileName;
    }

    public String toString(){
        String result = "";
        result += "ID: " + Integer.toString(id) + "\n";
        result += "Name: " + name + "\n";
        result += "Description: " + description + "\n";
        result += "File Name: " + fileName + "\n";
        result += "Challenges:\n";
        for(Challenge challenge : challenges){
            result += challenge.toString();
        }

        return result;
    }

}
