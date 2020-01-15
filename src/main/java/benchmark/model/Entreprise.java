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

import java.util.List;

public class Entreprise {

    private Long id;

    private String nom;

    private List<Personne> dirigeants;

    private Personne pdg;

    public Entreprise(Long id, String nom, List<Personne> dirigeants, Personne pdg) {
        this.id = id;
        this.nom = nom;
        this.dirigeants = dirigeants;
        this.pdg = pdg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Personne> getDirigeants() {
        return dirigeants;
    }

    public void setDirigeants(List<Personne> dirigeants) {
        this.dirigeants = dirigeants;
    }

    public Personne getPdg() {
        return pdg;
    }

    public void setPdg(Personne pdg) {
        this.pdg = pdg;
    }
}// benchmark.model.Entreprise
