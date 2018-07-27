package io.etherscan.etherscan.ui.erc20tokenslist;


import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.RecyclerView;
import io.etherscan.etherscan.R;
import io.etherscan.etherscan.data.model.Token;
import io.etherscan.etherscan.util.Converter;
import io.etherscan.etherscan.util.Rates;

public class Erc20TokensListAdapter extends RecyclerView.Adapter<Erc20TokensListAdapter.TokenViewHolder> {

    private HashMap<Token, Double>           erc20tokenBalances;
    private Map<String, Map<String, String>> rates;


    public Erc20TokensListAdapter(HashMap<Token, Double> erc20tokenBalances, Map<String, Map<String, String>> rates) {

        this.rates = rates;
        this.erc20tokenBalances = erc20tokenBalances;
    }

    class TokenViewHolder extends RecyclerView.ViewHolder {
        TextView tokenName, tokenValue;

        public TokenViewHolder(View view) {
            super(view);
            tokenName = view.findViewById(R.id.textview_token_erc20tokenslist);
            tokenValue = view.findViewById(R.id.textview_tokenvalue_erc20tokenslist);
        }
    }


    @Override
    public TokenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.erc20tokenslist_tokenlist_item, parent, false);

        return new TokenViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TokenViewHolder holder, int position) {

        Token token = (Token) erc20tokenBalances.keySet().toArray()[position];
        Double tokenValue = erc20tokenBalances.get(token);
        String tokenText = holder.tokenName.getContext().getString(R.string.text_nametoken_erc20tokenslistitem,
                token.getTokenName(), token.getTokenSymbol(), Converter.showWithDecimals(tokenValue, token.getDecimals()));
        holder.tokenName.setText(Html.fromHtml(tokenText));


        Double valueOfTokenInEth = Rates.getValueOfTokenInEth(token, tokenValue, rates);

        holder.tokenValue.setText(holder.tokenName.getContext().getString(R.string.text_valueethtoken_erc20tokenslistitem,
                Converter.showEthereum3f(valueOfTokenInEth)));
    }

    @Override
    public int getItemCount() {
        return erc20tokenBalances.size();
    }
}