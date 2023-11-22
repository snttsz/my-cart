package interfacegrafica.models;

public class PaginaLoja 
{
    public PaginaLoja(PainelLoja loja2, PainelLoja loja1) 
    {
        this.loja2 = loja2;
        this.loja1 = loja1;
    }

    public PainelLoja getLoja1() 
    {
        return loja1;
    }

    public void setLoja1(PainelLoja loja1) 
    {
        this.loja1 = loja1;
    }

    public PainelLoja getLoja2() 
    {
        return loja2;
    }

    public void setLoja2(PainelLoja loja2) 
    {
        this.loja2 = loja2;
    }

    private PainelLoja loja2;
    private PainelLoja loja1;
}
