package is.ru.ANDR;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
}
