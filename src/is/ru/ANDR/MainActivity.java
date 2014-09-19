package is.ru.ANDR;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Global global = Global.getSingleInstance();

        try{
            global.setPacks( readPacks( getAssets().open("packs/packs.xml") ) );

            for(Pack pack : global.getPacks()){
                Log.v("Packs", pack.toString());
            }


        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void continueFromLast(View view){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void selectBoard(View view){
        Intent intent = new Intent(this, SelectBoardActivity.class);
        startActivity(intent);
    }

    public void selectBoardTimeTrial(View view){

    }

    public void settings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private List<Pack> readPacks(InputStream inputStream){
        List<Pack> packs =new ArrayList<Pack>();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            NodeList nodeList = document.getElementsByTagName("pack");
            for ( int i = 0; i < nodeList.getLength(); ++i){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element eNode = (Element) node;
                    String name = eNode.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
                    String description = eNode.getElementsByTagName("description").item(0).getFirstChild().getNodeValue();
                    String fileName = eNode.getElementsByTagName("file").item(0).getFirstChild().getNodeValue();
                    int id = Integer.parseInt(eNode.getAttribute("id"));

                    Pack pack = new Pack(id, name, description, fileName, readChallenges(getAssets().open(fileName)));

                    packs.add(pack);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        }

        return packs;
    }

    private List<Challenge> readChallenges(InputStream inputStream){
        List<Challenge> challenges = new ArrayList<Challenge>();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            NodeList nodeList = document.getElementsByTagName("challenge");
            for ( int i = 0; i < nodeList.getLength(); ++i){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element eNode = (Element) node;

                    int id = Integer.parseInt(eNode.getAttribute("id"));
                    String name = eNode.getAttribute("name");
                    Challenge challenge = new Challenge(id, name);

                    NodeList nodeListPuzzle = eNode.getElementsByTagName("puzzle");
                    for ( int j = 0; j < nodeListPuzzle.getLength(); ++j) {
                        Node puzzleNode = nodeListPuzzle.item(j);
                        if(puzzleNode.getNodeType() == Node.ELEMENT_NODE){
                            Element ePuzzleNode = (Element) puzzleNode;
                            int puzzleId = Integer.parseInt(ePuzzleNode.getAttribute("id"));
                            int puzzleSize = Integer.parseInt( ePuzzleNode.getElementsByTagName("size").item(0).getFirstChild().getNodeValue() );

                            String flows = ePuzzleNode.getElementsByTagName("flows").item(0).getFirstChild().getNodeValue();

                            challenge.addPuzzle(new Puzzle(puzzleId, puzzleSize, parseFlows(flows)));
                        }
                    }

                    challenges.add(challenge);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        }

        return challenges;
    }

    private List<Flow> parseFlows(String flows){
        List <Flow> result = new ArrayList<Flow>();

        String[] flowArr = flows.replace(" ", "").split(",");

        for(int i = 0 ; i < flowArr.length; i++){
            Coordinate a = new Coordinate();
            Coordinate b = new Coordinate();

            a.setCol(Integer.parseInt(flowArr[i].substring(1,2)));
            a.setRow(Integer.parseInt(flowArr[i].substring(2,3)));
            b.setCol(Integer.parseInt(flowArr[i].substring(3,4)));
            b.setRow(Integer.parseInt(flowArr[i].substring(4,5)));


            result.add(new Flow(a, b));
        }

        return result;
    }

}
