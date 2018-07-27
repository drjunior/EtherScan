package io.etherscan.etherscan.data.model;

public class Token {

    private String tokenName;
    private String tokenSymbol;
    private int    decimals;

    public Token(String tokenName, String tokenSymbol, int decimals) {
        this.tokenName = tokenName;
        this.tokenSymbol = tokenSymbol;
        this.decimals = decimals;
    }

    public String getTokenName() {
        return tokenName;
    }

    public Token setTokenName(String tokenName) {
        this.tokenName = tokenName;
        return this;
    }

    public String getTokenSymbol() {
        return tokenSymbol;
    }

    public Token setTokenSymbol(String tokenSymbol) {
        this.tokenSymbol = tokenSymbol;
        return this;
    }

    public int getDecimals() {
        return decimals;
    }

    public Token setDecimals(int decimals) {
        this.decimals = decimals;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (decimals != token.decimals) return false;
        if (tokenName != null ? !tokenName.equals(token.tokenName) : token.tokenName != null)
            return false;
        return tokenSymbol != null ? tokenSymbol.equals(token.tokenSymbol) : token.tokenSymbol == null;
    }

    @Override
    public int hashCode() {
        int result = tokenName != null ? tokenName.hashCode() : 0;
        result = 31 * result + (tokenSymbol != null ? tokenSymbol.hashCode() : 0);
        result = 31 * result + decimals;
        return result;
    }
}
