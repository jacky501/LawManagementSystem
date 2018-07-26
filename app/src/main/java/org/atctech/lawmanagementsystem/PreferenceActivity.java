package org.atctech.lawmanagementsystem;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class PreferenceActivity extends android.preference.PreferenceActivity {
    SharedPreferences sp;
    Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        sp = getSharedPreferences("GCM", MODE_PRIVATE);
        edit = sp.edit();

    }
}
