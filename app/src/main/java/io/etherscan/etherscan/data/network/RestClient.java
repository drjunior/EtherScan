package io.etherscan.etherscan.data.network;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.etherscan.etherscan.BuildConfig;
import io.etherscan.etherscan.data.network.TransactionsResponse;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class RestClient {


    private static final String sBaseUrl = "https://api.etherscan.io/";
    private static final String sRatesUrl = "https://min-api.cryptocompare.com/data/pricemulti?";
    private static ApiInterface sApiInterface;

    public static ApiInterface getClient() {
        if (sApiInterface == null) {

            HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
            //noinspection ConstantConditions
            logger.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient okClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logger)
                    .build();


            Retrofit client = new Retrofit.Builder()
                    .baseUrl(sBaseUrl)
                    .client(okClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sApiInterface = client.create(ApiInterface.class);
        }
        return sApiInterface;
    }


    public interface ApiInterface {

        @GET("api?module=account&action=balance&tag=latest&apikey=E5QFXD7ZYRH7THQM5PIXB8JD4GY38SEJZ4")
        Observable<BalanceResponse> getBalance(@Query("address") String address);

        @GET("api?module=account&action=tokentx&sort=asc&apikey=E5QFXD7ZYRH7THQM5PIXB8JD4GY38SEJZ4")
        Observable<TransactionsResponse> getTransactions(@Query("address") String address);

        @GET
        Observable<Map<String, Map<String, String>>> getRates(@Url String url);


    }

    /**
     * Create an url to be used in the rates API request. Adds all tokensymbols to the url
     */
    public static String createRatesUrl(Set<String> tokenSymbols) {

        StringBuilder result = new StringBuilder(sRatesUrl + "fsyms=");

        for (String tokenSymbol : tokenSymbols) {
            result.append(tokenSymbol);
            result.append(",");
        }

        result.reverse().deleteCharAt(0).reverse();
        result.append("&tsyms=ETH");

        return result.toString();
    }
}