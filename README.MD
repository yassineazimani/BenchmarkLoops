## Introduction

Benchmark réalisé avec l'outil JMH d'OpenJDK. Cet outil nous permet
de prendre en compte le système d'exploitation en cours d'exécution (perturbations...),
une exécution de nos algorithmes en monothread ou en environnement multithreadé et bien entendu l'exécution
du code sous une JVM utilisant du bytecode optimisé ou à froid (non optimisé).

Les tests connaissent plusieurs itérations en mode warmup pour exécuter les tests à froid, sans optimisations
de la JVM avant d'être éprouvés par des itérations en mode measurement où les tests sont à chaud et optimisés
par la JVM. Plus il y'a d'itérations et plus le résultat sera proche de la réalité.

Les tests sont réalisés avec un jeu de données contenant 20 millions d'objets représentant des personnes
ayant chacun une entreprise (disposant de dirigeants), d'animaux (< 10) et
de voitures (< 10).

## Configuration matérielle utilisée pour les tests

Intel Core i5-6600 (6th generation) @3.30Ghz
16Go RAM
Windows 7
Disque dur SSD

## Résultats

Les résultats du benchmark se trouvent dans le fichier results.txt