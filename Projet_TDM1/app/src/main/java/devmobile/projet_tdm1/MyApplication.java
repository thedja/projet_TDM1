package devmobile.projet_tdm1;

import android.app.Application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

import devmobile.projet_tdm1.model.Chaine;
import devmobile.projet_tdm1.model.JSONController;
import devmobile.projet_tdm1.model.ProgrammeTele;

/**
 * Created by THEDJA on 08/04/2015.
 */
public class MyApplication extends Application {

    private HashMap<Chaine, ArrayList<ProgrammeTele>> programmesParChaine;
    private HashMap<String, ArrayList<ProgrammeTele>> programmesParThematique;
    private HashMap<String, ArrayList<ProgrammeTele>> programmesParTrancheHoraire;
    private ArrayList<ProgrammeTele> programmesFavoris;

    @Override
    public void onCreate() {
        super.onCreate();

        programmesParChaine = JSONController.loadData(getResources());
        makeProgrammesParTrancheHoraire();
        makeProgrammesParThematique();
        makeProgrammesFavoris();
        programmesFavoris = new ArrayList<>();
    }

    private void makeProgrammesParTrancheHoraire(){
        Set<Chaine> chaines = programmesParChaine.keySet();
        ArrayList<ProgrammeTele> matinee = new ArrayList<>(), apresMidi = new ArrayList<>(), soiree = new ArrayList<>();

        for(Chaine chaine : chaines){ // pour chaque chaine 'ch'
            for(ProgrammeTele program : programmesParChaine.get(chaine)){ // chaque programme de la chaine 'ch'
                switch (program.getTrancheHoraire()){
                    case "Matinee":
                        matinee.add(program);
                        break;
                    case "Apres-midi":
                        apresMidi.add(program);
                        break;
                    case "Soiree":
                        soiree.add(program);
                        break;
                }
            }
        }

        programmesParTrancheHoraire = new HashMap<>();
        programmesParTrancheHoraire.put("Matinee", matinee);
        programmesParTrancheHoraire.put("Apres-Midi", apresMidi);
        programmesParTrancheHoraire.put("Soiree", soiree);
    }

    private void makeProgrammesParThematique(){
        Set<Chaine> chaines = programmesParChaine.keySet();
        programmesParThematique = new HashMap<>();

        for(Chaine chaine : chaines){ // pour chaque chaine 'ch'
            for(ProgrammeTele program : programmesParChaine.get(chaine)){ // chaque programme de la chaine 'ch'
                String thematique = program.getThematique();
                if(programmesParThematique.containsKey(thematique)){
                    programmesParThematique.get(thematique).add(program);
                }else{
                    ArrayList newArrayList = new ArrayList<ProgrammeTele>();
                    newArrayList.add(program);
                    programmesParThematique.put(thematique, newArrayList);
                }
            }
        }
    }

    private void makeProgrammesFavoris(){

        Set<Chaine> chaines = programmesParChaine.keySet();
        programmesFavoris = new ArrayList<ProgrammeTele>();

        for(Chaine chaine : chaines){ // pour chaque chaine 'ch'
            for(ProgrammeTele program : programmesParChaine.get(chaine)){ // chaque programme de la chaine 'ch'
                if(program.isFavoris())
                    programmesFavoris.add(program);
            }
        }
    }

    public HashMap<Chaine, ArrayList<ProgrammeTele>> getProgrammesParChaines(){
        return programmesParChaine;
    }

    public HashMap<String, ArrayList<ProgrammeTele>> getProgrammesParTrancheHoraire(){
        return programmesParTrancheHoraire;
    }

    public HashMap<String, ArrayList<ProgrammeTele>> getProgrammesParThematique(){
        return programmesParThematique;
    }

    public ArrayList<ProgrammeTele> getProgrammesFavoris(){

        makeProgrammesFavoris();
        return programmesFavoris;
    }
}
