/*
 * Copyright 2020 Yassine AZIMANI
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package benchmark.model;

import java.util.ArrayList;
import java.util.List;

public class PersonnePool {

    public static List<Personne> getPersonnes(int max, Entreprise e, List<Animal> animaux, List<Voiture> voitures){
        List<Personne> personnes = new ArrayList<>();
        for(long i = 0; i < max; ++i){
            personnes.add(new Personne(i, "nom " + i, "prenom " + i, (int) i, e, animaux, voitures));
        }
        return personnes;
    }

    public static List<Personne> getDirigeants(int max, String prefix, Entreprise e, List<Animal> animaux, List<Voiture> voitures){
        List<Personne> personnes = new ArrayList<>();
        for(long i = 0; i < max; ++i){
            personnes.add(new Personne(i, prefix + " ndirigeant " + i, prefix + " pdirigeant " + i, (int) i, e, animaux, voitures));
        }
        return personnes;
    }

}// benchmark.model.PersonnePool
