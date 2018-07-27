package io.etherscan.etherscan.ui.erc20tokenslist;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.RecyclerView;

public class Erc20TokensListAdapter extends RecyclerView.Adapter<Erc20TokensListAdapter.TokenViewHolder> {

    private HashMap<String, Double>          erc20tokenBalances;
    private Map<String, Map<String, String>> rates;


    public Erc20TokensListAdapter(HashMap<String, Double> erc20tokenBalances, Map<String, Map<String, String>> rates) {

        this.rates = rates;
        this.erc20tokenBalances = erc20tokenBalances;
    }

    class TokenViewHolder extends RecyclerView.ViewHolder {
        TextView tokenName, tokenValue;

        public TokenViewHolder(View view) {
            super(view);
            tokenName = (TextView) view.findViewById(R.id.textview_token_erc20tokenslist);
            tokenValue = (TextView) view.findViewById(R.id.textview_token_erc20tokenslist);
        }
    }


    @Override
    public TokenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.erc, parent, false);

        return new TokenViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TokenViewHolder holder, int position) {

        String token = (String) erc20tokenBalances.keySet().toArray()[position];
        holder.tokenName.setText(token);
        holder.tokenValue.setText(String.valueOf(erc20tokenBalances.get(token)));
    }

    @Override
    public int getItemCount() {
        return erc20tokenBalances.size();
    }
}