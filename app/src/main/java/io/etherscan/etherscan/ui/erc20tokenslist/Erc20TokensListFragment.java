package io.etherscan.etherscan.ui.erc20tokenslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.etherscan.etherscan.R;
import io.etherscan.etherscan.data.network.DashboardResponse;
import io.etherscan.etherscan.ui.dashboard.DashboardViewModel;

public class Erc20TokensListFragment extends Fragment {


    private static final String address = "0x082d3e0f04664b65127876e9A05e2183451c792a";

    private DashboardViewModel mViewModel;

    /**
     * VIEWS
     */
    private RecyclerView mRecyclerView;


    public static Erc20TokensListFragment newInstance() {
        return new Erc20TokensListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.erc20tokenslist_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mRecyclerView = view.findViewById(R.id.recyclerview_erc20tokenslist);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRecyclerview();
    }


    private void initRecyclerview() {

        mViewModel = ViewModelProviders.of(getActivity()).get(DashboardViewModel.class);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        mViewModel.getErc20TokenListData().observe(getActivity(), new Observer<DashboardResponse>() {
            @Override
            public void onChanged(DashboardResponse dashboardResponse) {

                Erc20TokensListAdapter mAdapter = new Erc20TokensListAdapter(dashboardResponse.getErc20tokenBalances(), dashboardResponse.getRates());
                mRecyclerView.setAdapter(mAdapter);
            }
        });


    }

}
