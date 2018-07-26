package io.etherscan.etherscan.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.etherscan.etherscan.data.network.BalanceResponse;
import io.etherscan.etherscan.data.network.DashboardResponse;
import io.etherscan.etherscan.data.network.RestClient;
import io.etherscan.etherscan.data.network.TransactionsResponse;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DashboardViewModel extends ViewModel {


    private MutableLiveData<DashboardResponse> dashboardResponseData;

    public LiveData<DashboardResponse> getTokenTransactions(String address) {
        if (dashboardResponseData == null) {
            dashboardResponseData = new MutableLiveData<>();
            loadDashboardResponse(address);
        }
        return dashboardResponseData;
    }

    private void loadDashboardResponse(String address) {


        Observable<TransactionsResponse> transactions = RestClient.getClient().getTransactions(address);
        Observable<BalanceResponse>      balance      = RestClient.getClient().getBalance(address);

        Observable.zip(transactions, balance, (transactionsResponse, balanceResponse) -> {

            DashboardResponse dashboardResponse = new DashboardResponse();
            dashboardResponse.setAdressBalance(balanceResponse);
            dashboardResponse.setTokenTransactions(transactionsResponse.getResult());
            return dashboardResponse;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DashboardResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DashboardResponse dashboardResponse) {
                        dashboardResponseData.setValue(dashboardResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        DashboardResponse dashboardResponse = new DashboardResponse();
                        dashboardResponse.setError(true);
                        dashboardResponseData.setValue(dashboardResponse);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
