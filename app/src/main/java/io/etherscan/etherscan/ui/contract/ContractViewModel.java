package io.etherscan.etherscan.ui.contract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.etherscan.etherscan.data.model.DashboardResponse;
import io.etherscan.etherscan.data.network.BalanceResponse;
import io.etherscan.etherscan.data.network.RestClient;
import io.etherscan.etherscan.data.network.TransactionsResponse;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class ContractViewModel extends ViewModel {


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

        Observable.zip(transactions, balance, new BiFunction<TransactionsResponse, BalanceResponse, DashboardResponse>() {
            @Override
            public DashboardResponse apply(TransactionsResponse transactionsResponse, BalanceResponse balanceResponse) throws Exception {

                DashboardResponse dashboardResponse = new DashboardResponse();
                dashboardResponse.setAdressBalance(balanceResponse);
                dashboardResponse.setAdressBalance(balanceResponse);
                return dashboardResponse;
            }
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
