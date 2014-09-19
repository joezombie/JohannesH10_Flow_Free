package is.ru.ANDR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joezombie on 19.9.2014.
 */
public class Puzzle {
    int id;
    int size;
    List<Flow> flows;

    public Puzzle(int id, int size){
        this.id = id;
        this.size = size;
        this.flows = new ArrayList<Flow>();
    }

    public Puzzle(int id, int size, List<Flow> flows){
        this.id = id;
        this.size = size;
        this.flows = flows;
    }

    public void addFlow(Flow flow){
        flows.add(flow);
    }

    public List<Flow> getFlows(){
        return flows;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString(){
        String result = "";
        result += "ID: " + Integer.toString(id) + "\n";
        result += "Size: " + Integer.toString(size) + "\n";
        result += "Flows:\n";
        for(Flow flow : flows){
            result += flow.toString();
        }
        return result;
    }
}
