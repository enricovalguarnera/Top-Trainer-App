package com.example.toptrainer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WhiteAndGrayModel {

    private Map<String, List<String>> GKWhiteGrayAbilities;
    private Map<String, List<String>> DCWhiteGrayAbilities;
    private Map<String, List<String>> DLDRWhiteGrayAbilities;
    private Map<String, List<String>> DMCWhiteGrayAbilities;
    private Map<String, List<String>> MCWhiteGrayAbilities;
    private Map<String, List<String>> MLMRWhiteGrayAbilities;
    private Map<String, List<String>> AMCWhiteGrayAbilities;
    private Map<String, List<String>> AMLAMRWhiteGrayAbilities;
    private Map<String, List<String>> STWhiteGrayAbilities;

    public WhiteAndGrayModel(List<String> selectedRoleKey) {
        initSetMethod(selectedRoleKey);
    }

    public Map<String, List<String>> getGKWhiteGrayAbilities() {
        return GKWhiteGrayAbilities;
    }

    public void setGKWhiteGrayAbilities() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> whites = new ArrayList<>();
        whites.add("riflessi");
        whites.add("agilita");
        whites.add("anticipo");
        whites.add("scatto");
        whites.add("comunicazione");
        whites.add("lancio");
        whites.add("calcio");
        whites.add("pugni");
        whites.add("elevazione");
        whites.add("concentrazione");
        whites.add("forma");

        List<String> gray = new ArrayList<>();
        gray.add("forza");
        gray.add("aggressivita");
        gray.add("velocita");
        gray.add("creativita");

        data.put("white", whites);
        data.put("gray", gray);
        this.GKWhiteGrayAbilities = data;
    }

    public Map<String, List<String>> getDCWhiteGrayAbilities() {
        return DCWhiteGrayAbilities;
    }

    public void setDCWhiteGrayAbilities() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> whites = new ArrayList<>();
        whites.add("contrasto");
        whites.add("marcatura");
        whites.add("posizionamento");
        whites.add("colpo_di_testa");
        whites.add("coraggio");
        whites.add("forma");
        whites.add("forza");
        whites.add("aggressivita");

        List<String> gray = new ArrayList<>();
        gray.add("passaggio");
        gray.add("dribbling");
        gray.add("cross");
        gray.add("tiro");
        gray.add("finalizzazione");
        gray.add("velocita");
        gray.add("creativita");

        data.put("white", whites);
        data.put("gray", gray);
        this.DCWhiteGrayAbilities = data;
    }

    public Map<String, List<String>> getDLDRWhiteGrayAbilities() {
        return DLDRWhiteGrayAbilities;
    }

    public void setDLDRWhiteGrayAbilities() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> whites = new ArrayList<>();
        whites.add("contrasto");
        whites.add("marcatura");
        whites.add("posizionamento");
        whites.add("coraggio");
        whites.add("cross");
        whites.add("forma");
        whites.add("aggressivita");
        whites.add("velocita");

        List<String> gray = new ArrayList<>();
        gray.add("colpo_di_testa");
        gray.add("passaggio");
        gray.add("dribbling");
        gray.add("tiro");
        gray.add("finalizzazione");
        gray.add("forza");
        gray.add("creativita");

        data.put("white", whites);
        data.put("gray", gray);
        this.DLDRWhiteGrayAbilities = data;
    }


    public Map<String, List<String>> getDMCWhiteGrayAbilities() {
        return DMCWhiteGrayAbilities;
    }

    public void setDMCWhiteGrayAbilities() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> whites = new ArrayList<>();
        whites.add("contrasto");
        whites.add("marcatura");
        whites.add("posizionamento");
        whites.add("colpo_di_testa");
        whites.add("coraggio");
        whites.add("passaggio");
        whites.add("forma");
        whites.add("forza");
        whites.add("aggressivita");
        whites.add("creativita");

        List<String> gray = new ArrayList<>();
        gray.add("dribbling");
        gray.add("cross");
        gray.add("tiro");
        gray.add("finalizzazione");
        gray.add("velocita");

        data.put("white", whites);
        data.put("gray", gray);
        this.DMCWhiteGrayAbilities = data;
    }

    public Map<String, List<String>> getMCWhiteGrayAbilities() {
        return MCWhiteGrayAbilities;
    }

    public void setMCWhiteGrayAbilities() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> whites = new ArrayList<>();
        whites.add("contrasto");
        whites.add("marcatura");
        whites.add("posizionamento");
        whites.add("coraggio");
        whites.add("passaggio");
        whites.add("dribbling");
        whites.add("tiro");
        whites.add("forma");
        whites.add("velocita");
        whites.add("creativita");

        List<String> gray = new ArrayList<>();
        gray.add("colpo_di_testa");
        gray.add("cross");
        gray.add("finalizzazione");
        gray.add("forza");
        gray.add("aggressivita");

        data.put("white", whites);
        data.put("gray", gray);
        this.MCWhiteGrayAbilities = data;
    }


    public Map<String, List<String>> getMLMRWhiteGrayAbilities() {
        return MLMRWhiteGrayAbilities;
    }

    public void setMLMRWhiteGrayAbilities() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> whites = new ArrayList<>();
        whites.add("posizionamento");
        whites.add("passaggio");
        whites.add("dribbling");
        whites.add("cross");
        whites.add("forma");
        whites.add("velocita");
        whites.add("creativita");

        List<String> gray = new ArrayList<>();
        gray.add("contrasto");
        gray.add("marcatura");
        gray.add("colpo_di_testa");
        gray.add("coraggio");
        gray.add("tiro");
        gray.add("finalizzazione");
        gray.add("forza");
        gray.add("aggressivita");

        data.put("white", whites);
        data.put("gray", gray);
        this.MLMRWhiteGrayAbilities = data;
    }

    public Map<String, List<String>> getAMCWhiteGrayAbilities() {
        return AMCWhiteGrayAbilities;
    }

    public void setAMCWhiteGrayAbilities() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> whites = new ArrayList<>();
        whites.add("colpo_di_testa");
        whites.add("passaggio");
        whites.add("dribbling");
        whites.add("tiro");
        whites.add("finalizzazione");
        whites.add("forma");
        whites.add("velocita");
        whites.add("creativita");

        List<String> gray = new ArrayList<>();
        gray.add("contrasto");
        gray.add("marcatura");
        gray.add("posizionamento");
        gray.add("coraggio");
        gray.add("cross");
        gray.add("forza");
        gray.add("aggressivita");

        data.put("white", whites);
        data.put("gray", gray);
        this.AMCWhiteGrayAbilities = data;
    }

    public Map<String, List<String>> getAMLAMRWhiteGrayAbilities() {
        return AMLAMRWhiteGrayAbilities;
    }

    public void setAMLAMRWhiteGrayAbilities() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> whites = new ArrayList<>();
        whites.add("passaggio");
        whites.add("dribbling");
        whites.add("cross");
        whites.add("tiro");
        whites.add("finalizzazione");
        whites.add("forma");
        whites.add("velocita");
        whites.add("creativita");

        List<String> gray = new ArrayList<>();
        gray.add("contrasto");
        gray.add("marcatura");
        gray.add("posizionamento");
        gray.add("colpo_di_testa");
        gray.add("coraggio");
        gray.add("forza");
        gray.add("aggressivita");

        data.put("white", whites);
        data.put("gray", gray);
        this.AMLAMRWhiteGrayAbilities = data;
    }

    public Map<String, List<String>> getSTWhiteGrayAbilities() {
        return STWhiteGrayAbilities;
    }

    public void setSTWhiteGrayAbilities() {
        Map<String, List<String>> data = new HashMap<>();
        List<String> whites = new ArrayList<>();
        whites.add("posizionamento");
        whites.add("colpo_di_testa");
        whites.add("passaggio");
        whites.add("dribbling");
        whites.add("tiro");
        whites.add("finalizzazione");
        whites.add("forza");
        whites.add("velocita");
        whites.add("creativita");

        List<String> gray = new ArrayList<>();
        gray.add("contrasto");
        gray.add("marcatura");
        gray.add("coraggio");
        gray.add("cross");
        gray.add("forma");
        gray.add("aggressivita");

        data.put("white", whites);
        data.put("gray", gray);
        this.STWhiteGrayAbilities = data;
    }

    // prende in input le mappe contenenti le abilita bianche e grigie. Prende in input anche la chiave (white/gray)
    // restituisce una singola mappa con l'unione delle abilità bianche/grigie
    public static Map<String, List<String>> getMergedWhiteOrGrayAbilities (String key, Map<String, List<String>> o1, Map<String, List<String>> o2) {
        Map<String, List<String>> whiteMerged = new HashMap<>();     // inizializzo la mappa risultato con la prima in input
        if (o1 != null) {
            if (o2 == null) {
                return o1;
            }
            List<String> abilitiesO1 = o1.get(key);           // ottengo la lista delle abilita della prima mappa tramite la key
            List<String> abilitiesO2 = o2.get(key);           // ottengo la lista delle abilita della seconda mappa tramite la key
            for (int i=0; i<abilitiesO2.size(); i++) {
                if (!abilitiesO1.contains(abilitiesO2.get(i))) {
                    abilitiesO1.add(abilitiesO2.get(i));
                }
            }
            whiteMerged.put(key, abilitiesO1);
        }
        return whiteMerged;
    }

    private void initSetMethod(List<String> selectedRoleKey) {
        if (selectedRoleKey.contains("GK_VALUE")) {
            setGKWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("DC_VALUE")) {
            setDCWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("DR_VALUE")) {
            setDLDRWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("DL_VALUE")) {
            setDLDRWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("DMC_VALUE")) {
            setDMCWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("MC_VALUE")) {
            setMCWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("MR_VALUE")) {
            setMLMRWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("ML_VALUE")) {
            setMLMRWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("AMC_VALUE")) {
            setAMCWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("AMR_VALUE")) {
            setAMLAMRWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("AML_VALUE")) {
            setAMLAMRWhiteGrayAbilities();
        }
        if (selectedRoleKey.contains("ST_VALUE")) {
            setSTWhiteGrayAbilities();
        }
    }
}


