package io.etherscan.etherscan.ui.contract;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.etherscan.etherscan.R;

public class ContractFragment extends Fragment {

    private ContractViewModel mViewModel;

    public static ContractFragment newInstance() {
        return new ContractFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contract_fragment, container, false);
    }

    TextView textview;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        textview = view.findViewById(R.id.message);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();
    }


    private void initViewModel() {

        String address = "0x082d3e0f04664b65127876e9A05e2183451c792a";

        mViewModel = ViewModelProviders.of(this).get(ContractViewModel.class);
        mViewModel.getTokenTransactions(address).observe(this, dashResponse -> {

            textview.setText(dashResponse.getAdressBalance().getResult());
        });

    }

}
