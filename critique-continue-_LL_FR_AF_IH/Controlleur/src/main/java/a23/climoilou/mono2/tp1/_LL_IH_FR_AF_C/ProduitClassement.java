package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.CustomCells.IProduitClassement;

public class ProduitClassement implements IProduitClassement
{

    private String nom;
    private String difference;
    private boolean estNeutre;

    public ProduitClassement(String nom, String difference, boolean estNeutre)
    {
        this.nom = nom;
        this.difference = difference;
        this.estNeutre = estNeutre;
    }

    @Override
    public String getNom()
    {
        return this.nom;
    }

    @Override
    public String getDifference()
    {
        return this.difference;
    }

    @Override
    public boolean estNeutre()
    {
        return this.estNeutre;
    }
}
