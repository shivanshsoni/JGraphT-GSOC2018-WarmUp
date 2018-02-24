package org.jgrapht.GSOC2018.warmup;

import org.jgrapht.Graph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.alg.NaiveLcaFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.io.*;

import java.io.File;
import java.util.Set;

public final class Main {


//Created By Shivansh Soni as a part of GSOC 2018 warmup Exercise
    private Main() {

    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Please provide these 3 inputs:" + " 1. Path of .dot file" +
                " 2. Name of person 1" + " 3. Name of person 2"
            );
        }
        File inputFile = new File(args[0]);
        String person1 = args[1];
        String person2 = args[2];

        Graph<String, DefaultEdge> GOTFamilyGraph = new SimpleDirectedGraph<>(DefaultEdge.class);
        importDOTGraph(GOTFamilyGraph, inputFile);

        if (!GOTFamilyGraph.containsVertex(person1)) {
            System.out.println(person1 + " is is not present in the input graph");
            return;
        }
        if (!GOTFamilyGraph.containsVertex(person2)) {
            System.out.println(person2 + " is is not present in the input graph");
            return;
        }

        CycleDetector<String, DefaultEdge> cd = new CycleDetector<>(GOTFamilyGraph);
        if (cd.detectCycles()) {
            System.out.println("The input graph is not an acyclic graph");
            return;
        }

        NaiveLcaFinder<String, DefaultEdge> lcaFinder = new NaiveLcaFinder<>(GOTFamilyGraph);
        Set<String> lcas = lcaFinder.findLcas(person1, person2);
        System.out.println(lcas.toString());

    }

    private static void importDOTGraph(Graph<String, DefaultEdge> graph, File inputFile) {
        VertexProvider<String> vp = (label, attributes) -> label;
        EdgeProvider<String, DefaultEdge> ep = (from, to, label, attributes) -> new DefaultEdge();
        GraphImporter<String, DefaultEdge> importer = new DOTImporter<>(vp, ep);
        try {
            importer.importGraph(graph, inputFile);
        } catch (ImportException e) {
            e.printStackTrace();
        }
    }
}


//End of my main program :-)
//Hope it is correct
