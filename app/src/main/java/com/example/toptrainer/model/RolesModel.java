package com.example.toptrainer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// questo modello si occupa di definire il ruolo del giocatore come unione dei ruoli selezionati.
// whiteMergedAbilities: contiene l'unione delle abilità bianche di ogni ruolo selezionato dall'utente.

public class RolesModel {

    private List<String> whiteOrGrayMergedAbilities;

    List<Map<String, List<String>>> rolesMap;

    public RolesModel(List<String> keyOfSelectedRoles, String whiteOrGray) {
        WhiteAndGrayModel whiteAndGrayModel = new WhiteAndGrayModel(keyOfSelectedRoles);
        rolesMap = initRolesMap(whiteAndGrayModel, keyOfSelectedRoles);
        if (rolesMap.size() == 1) {
            whiteOrGrayMergedAbilities = rolesMap.get(0).get(whiteOrGray);
        } else {
            for (int i=0; i<keyOfSelectedRoles.size()-1; i++) {
                whiteOrGrayMergedAbilities = WhiteAndGrayModel.getMergedWhiteOrGrayAbilities(whiteOrGray, rolesMap.get(i), rolesMap.get(i+1)).get(whiteOrGray);
                // TODO questa logica non va. DEVE ESSERE SCRITTA MEGLIO. DEVE ESSERE UNA ASSEGNAZIONE ITERATIVA.
            }
        }
    }

    private List<Map<String, List<String>>> initRolesMap(WhiteAndGrayModel whiteAndGrayModel, List<String> keyRole) {
        List<Map<String, List<String>>> resultList = new ArrayList<>();
        if (keyRole.contains("GK_VALUE")) {
            resultList.add(whiteAndGrayModel.getGKWhiteGrayAbilities());
        }
        if (keyRole.contains("DC_VALUE")) {
            resultList.add(whiteAndGrayModel.getDCWhiteGrayAbilities());
        }
        if (keyRole.contains("DR_VALUE")) {
            resultList.add(whiteAndGrayModel.getDLDRWhiteGrayAbilities());
        }
        if (keyRole.contains("DL_VALUE")) {
            resultList.add(whiteAndGrayModel.getDLDRWhiteGrayAbilities());
        }
        if (keyRole.contains("DMC_VALUE")) {
            resultList.add(whiteAndGrayModel.getDMCWhiteGrayAbilities());
        }
        if (keyRole.contains("MC_VALUE")) {
            resultList.add(whiteAndGrayModel.getMCWhiteGrayAbilities());
        }
        if (keyRole.contains("MR_VALUE")) {
            resultList.add(whiteAndGrayModel.getMLMRWhiteGrayAbilities());
        }
        if (keyRole.contains("ML_VALUE")) {
            resultList.add(whiteAndGrayModel.getMLMRWhiteGrayAbilities());
        }
        if (keyRole.contains("AMC_VALUE")) {
            resultList.add(whiteAndGrayModel.getAMCWhiteGrayAbilities());
        }
        if (keyRole.contains("AMR_VALUE")) {
            resultList.add(whiteAndGrayModel.getAMLAMRWhiteGrayAbilities());
        }
        if (keyRole.contains("AML_VALUE")) {
            resultList.add(whiteAndGrayModel.getAMLAMRWhiteGrayAbilities());
        }
        if (keyRole.contains("ST_VALUE")) {
            resultList.add(whiteAndGrayModel.getSTWhiteGrayAbilities());
        }
        return resultList;
    }

    public List<String> getWhiteOrGrayMergedAbilities() {
        return whiteOrGrayMergedAbilities;
    }

    public void setWhiteOrGrayMergedAbilities(List<String> whiteOrGrayMergedAbilities) {
        this.whiteOrGrayMergedAbilities = whiteOrGrayMergedAbilities;
    }
}
