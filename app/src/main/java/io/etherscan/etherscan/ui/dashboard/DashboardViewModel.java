package io.etherscan.etherscan.ui.dashboard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.etherscan.etherscan.data.model.Token;
import io.etherscan.etherscan.data.model.TokenTransaction;
import io.etherscan.etherscan.data.network.BalanceResponse;
import io.etherscan.etherscan.data.network.DashboardResponse;
import io.etherscan.etherscan.data.network.RestClient;
import io.etherscan.etherscan.data.network.TransactionsResponse;
import io.etherscan.etherscan.util.Rates;
import io.etherscan.etherscan.util.Transactions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DashboardViewModel extends ViewModel {


    private DashboardResponse                  mDashboardResponse;
    private MutableLiveData<DashboardResponse> mDashboardResponseData;
    private MutableLiveData<DashboardResponse> mErc20TokensListResponseData;

    public LiveData<DashboardResponse> getTokenTransactions(String address) {
        if (mDashboardResponseData == null) {
            mDashboardResponseData = new MutableLiveData<>();
            loadDashboardResponse(address);
        }

        return mDashboardResponseData;
    }

    private void loadDashboardResponse(String address) {


        // get balance and transactions list
        Observable<TransactionsResponse> transactions = RestClient.getClient().getTransactions(address);
        Observable<BalanceResponse>      balance      = RestClient.getClient().getBalance(address);

        Observable.zip(transactions, balance, (transactionsResponse, balanceResponse) -> {

            // combine both request's results into dashboard response
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

                        Set<String> tokenSymbols = new HashSet<>();

                        for (TokenTransaction tokenTransaction : dashboardResponse.getTokenTransactions()) {
                            tokenSymbols.add(tokenTransaction.getTokenSymbol());
                        }

                        Observable<Map<String, Map<String, String>>> rates = RestClient.getClient().getRates(RestClient.createRatesUrl(tokenSymbols));

                        rates.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<Map<String, Map<String, String>>>() {

                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Map<String, Map<String, String>> rates) {


                                        // calculate the sum of each token (in and out transactions sum)
                                        HashMap<Token, Double> erc20TokensBalances = Transactions.getErc20TokenBalanceTransactions(address, dashboardResponse.getTokenTransactions());
                                        dashboardResponse.setErc20tokenBalances(erc20TokensBalances);

                                        Double erc20tokensBalance = Rates.getSumOfTokensInETH(erc20TokensBalances, rates);
                                        dashboardResponse.setErc20TokensBalance(erc20tokensBalance);
                                        dashboardResponse.setRates(rates);

                                        //set total (eth balance + erc20tokens)
                                        Double totalAccountBalance = Double.parseDouble(dashboardResponse.getAdressBalance().getResult()) + erc20tokensBalance;
                                        dashboardResponse.setTotalBalance(totalAccountBalance);

                                        mDashboardResponseData.setValue(dashboardResponse);

                                        mDashboardResponse = dashboardResponse;
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        DashboardResponse dashboardResponse = new DashboardResponse();
                                        dashboardResponse.setError(true);
                                        mDashboardResponseData.setValue(dashboardResponse);

                                        //set it to null so that it can be reinitialized in case user retries the operation
                                        mDashboardResponseData = null;
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });


                    }

                    @Override
                    public void onError(Throwable e) {
                        DashboardResponse dashboardResponse = new DashboardResponse();
                        dashboardResponse.setError(true);
                        mDashboardResponseData.setValue(dashboardResponse);

                        //set it to null so that it can be reinitialized in case user retries the operation
                        mDashboardResponseData = null;
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public LiveData<DashboardResponse> getErc20TokenListData() {
        if (mErc20TokensListResponseData == null) {
            mErc20TokensListResponseData = new MutableLiveData<>();

            mErc20TokensListResponseData.setValue(mDashboardResponse);
        }

        return mErc20TokensListResponseData;
    }

}
