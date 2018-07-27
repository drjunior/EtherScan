package io.etherscan.etherscan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import io.etherscan.etherscan.ui.dashboard.DashboardFragment;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DashboardFragment.newInstance())
                    .commitNow();
        }
    }
}
