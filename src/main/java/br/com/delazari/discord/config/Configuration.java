package br.com.delazari.discord.config;

public enum Configuration {

    TOKEN(1, "ODgwNjQ1Mjg4NzQwNjU1MTE0.YShS1g.bPQQ26GNVBL7loG_DHme0lkLcfc");

    private int indice;
    private String descricao;

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private Configuration(int indice, String descricao) {
        this.indice = indice;
        this.descricao = descricao;
    }

}
