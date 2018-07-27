package io.etherscan.etherscan.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.etherscan.etherscan.R;
import io.etherscan.etherscan.util.Converter;

public class DashboardFragment extends Fragment {


    private static final String address = "0x082d3e0f04664b65127876e9A05e2183451c792a";

    private DashboardViewModel mViewModel;

    /**
     * VIEWS
     */
    private TextView                  mTextViewValueAccount;
    private TextView                  mTextViewValueErc20;
    private ConstraintLayout          mConstraint;
    private ContentLoadingProgressBar mContentLoadingProgressBar;
    private CardView                  mCardViewAccount;
    private CardView                  mCardViewErc20;


    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mTextViewValueAccount = view.findViewById(R.id.textview_valueaccount_dashboard);
        mTextViewValueErc20 = view.findViewById(R.id.textview_valueerc20_dashboard);
        mConstraint = view.findViewById(R.id.constraint_container_dashboard);
        mContentLoadingProgressBar = view.findViewById(R.id.progressbar_main_dashboard);
        mCardViewAccount = view.findViewById(R.id.cardview_account_dashboard);
        mCardViewErc20 = view.findViewById(R.id.cardview_erc20_dashboard);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initDashboard();
    }


    private void initDashboard() {

        showLoading();
        hideCardviews();

        mViewModel = ViewModelProviders.of(getActivity()).get(DashboardViewModel.class);
        mViewModel.getTokenTransactions(address).observe(this, dashResponse -> {


            // check if api response is not error and show info
            if (!dashResponse.isError()) {

                showCardviews();
                mTextViewValueAccount.setText(getString(R.string.value_accountbalance_dashboard, Converter.showEthereum(dashResponse.getTotalBalance())));
                mTextViewValueErc20.setText(getString(R.string.value_erc20balance_dashboard,
                        Converter.showEthereum(dashResponse.getErc20TokensBalance())));
            } else {

                // show snackbar with error message in case an API error happened and allow the user to retry
                Snackbar snackbar = Snackbar.make(mConstraint, R.string.error_apiproblem_dashboard, Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(R.string.snackbar_retry_dashboard, view -> initDashboard());
                snackbar.show();
            }

            hideLoading();

        });

    }

    private void showLoading() {
        mContentLoadingProgressBar.show();
    }

    private void hideLoading() {
        mContentLoadingProgressBar.hide();
    }

    private void showCardviews() {

        mCardViewAccount.setVisibility(View.VISIBLE);
        mCardViewErc20.setVisibility(View.VISIBLE);
    }

    private void hideCardviews() {
        mCardViewAccount.setVisibility(View.GONE);
        mCardViewErc20.setVisibility(View.GONE);

    }

}
