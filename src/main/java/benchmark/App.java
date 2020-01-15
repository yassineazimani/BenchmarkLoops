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
package benchmark;

import benchmark.model.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class App {

    private static Random random = new Random();

    private static List<Personne> personnes;

    @Setup
    public static void setup() {
        personnes = getPersonnes();
    }// setup()

    private static List<Personne> getPersonnes(){
        List<Voiture> voitures = VoiturePool.getVoitures(10);
        List<Animal> animaux = AnimalPool.getAnimals(10);
        List<Entreprise> entreprises = EntreprisePool.getEntreprises(100);
        List<Personne> personnes = PersonnePool.getPersonnes(20000000,
                entreprises.get(random.ints(0, entreprises.size()).findFirst().getAsInt()),
                animaux.subList(random.ints(0, animaux.size()-1).findFirst().getAsInt(), animaux.size()),
                voitures.subList(random.ints(0, voitures.size()-1).findFirst().getAsInt(), voitures.size()));
        return personnes;
    }// getPersonnes()

    @Benchmark
    public static void traitementsAvecStreams(){
        personnes.stream().filter(p -> p.getAge() > 15
                && p.getVoitures().stream().anyMatch(v -> v.getCouleur().equals("blanc")))
                .collect(Collectors.toList());
            personnes.stream().forEach(p -> {
                p.getAnimaux().stream().forEach(a -> {});
                p.getVoitures().stream().forEach(v -> {});
                p.getEntreprise().getDirigeants().forEach(d -> {});
            });
    }// traitementsAvecStreams()

    @Benchmark
    public static void traitementsAvecParallelsStreams(){
        personnes.parallelStream().filter(p -> p.getAge() > 15
                && p.getVoitures().stream().anyMatch(v -> v.getCouleur().equals("blanc")))
                .collect(Collectors.toList());
        personnes.parallelStream().forEach(p -> {
            p.getAnimaux().stream().forEach(a -> {});
            p.getVoitures().stream().forEach(v -> {});
            p.getEntreprise().getDirigeants().forEach(d -> {});
        });
    }// traitementsAvecParallelsStreams()

    @Benchmark
    public static void traitementsAvecIterateurs(){
        List<Personne> personnesFiltered = new ArrayList<>();
        for(Personne p : personnes){
            if(p.getAge() > 15 && possedeVoitureAvecCouleur(p.getVoitures(), "blanc")){
                personnesFiltered.add(p);
            }
        }
        for(Personne p : personnes){
            for(Animal a : p.getAnimaux()){}
            for(Voiture v : p.getVoitures()){}
            for(Personne d : p.getEntreprise().getDirigeants()){}
        }
    }// traitementsAvecIterateurs()

    @Benchmark
    public static void traitementsAvecForClassique(){
        List<Personne> personnesFiltered = new ArrayList<>();
        for(int i = 0; i < personnes.size(); ++i){
            Personne p = personnes.get(i);
            if(p.getAge() > 15 && possedeVoitureAvecCouleur_loop_classic(p.getVoitures(), "blanc")){
                personnesFiltered.add(p);
            }
        }

        for(int i = 0; i < personnes.size(); ++i){
            Personne p = personnes.get(i);
            for(int a = 0; a < p.getAnimaux().size(); ++a){}
            for(int v = 0; v < p.getVoitures().size(); ++v){}
            for(int d = 0; d < p.getEntreprise().getDirigeants().size(); ++d){}
        }
    }// traitementsAvecForClassique()

    private static boolean possedeVoitureAvecCouleur(List<Voiture> voitures, String couleur){
        for(Voiture voiture : voitures){
            if(voiture.getCouleur().equals(couleur))
                return true;
        }
        return false;
    }// possedeVoitureAvecCouleur()

    private static boolean possedeVoitureAvecCouleur_loop_classic(List<Voiture> voitures, String couleur){
        for(int i = 0; i < voitures.size(); ++i){
            if(voitures.get(i).getCouleur().equals(couleur))
                return true;
        }
        return false;
    }// possedeVoitureAvecCouleur_loop_classic()

    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }// main()
}// benchmark.App
