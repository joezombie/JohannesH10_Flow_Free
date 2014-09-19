package is.ru.ANDR;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by joezombie on 19.9.2014.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}