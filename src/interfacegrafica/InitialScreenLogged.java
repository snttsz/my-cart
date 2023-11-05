package interfacegrafica;

public class InitialScreenLogged extends Screen
{
    public InitialScreenLogged(String tituloJanela, String fxmlname, String userName) 
    {
        super(tituloJanela + " - " + userName, fxmlname);
    }
}
