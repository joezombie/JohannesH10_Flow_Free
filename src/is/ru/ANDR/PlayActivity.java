package is.ru.ANDR;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class PlayActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.play);
    }
}