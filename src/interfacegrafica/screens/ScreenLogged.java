package interfacegrafica.screens;

public class ScreenLogged extends Screen
{
    public ScreenLogged(String tituloJanela, String fxmlname, String userName) 
    {
        super(tituloJanela + " - " + userName, fxmlname);
    }
}
