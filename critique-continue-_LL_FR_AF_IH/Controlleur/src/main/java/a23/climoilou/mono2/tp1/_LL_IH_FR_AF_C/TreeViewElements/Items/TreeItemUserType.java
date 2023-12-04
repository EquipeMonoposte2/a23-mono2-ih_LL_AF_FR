package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.TreeItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurParType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class TreeItemUserType implements TreeItemI {

    private Utilisateur innerUser;

    private UtilisateurParType typeUser;

    private String afficheRoot;


    @Autowired
    public void setTypeUser(UtilisateurParType typeUser) {
        this.typeUser = typeUser;
    }

    public TreeItemUserType(String afficheRoot) {
        this.afficheRoot = afficheRoot;
    }






    @Override
    public String toString() {
//        String innerUserShow = this.innerUser.toString().isEmpty() ? "Aucun Utilisateur avec ce type" : this.innerUser.toString();
        return  this.afficheRoot;
    }


    @Override
    public String affiche() {
        return "";
    }
}
