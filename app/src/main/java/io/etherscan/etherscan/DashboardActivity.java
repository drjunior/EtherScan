package io.etherscan.etherscan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.etherscan.etherscan.ui.dashboard.DashboardFragment;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DashboardFragment.newInstance())
                    .commitNow();
        }
    }
}
