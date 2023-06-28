package com.lucasmori.search.autocomplete.system;

import com.lucasmori.search.autocomplete.system.sfs.Aggregator;
import com.lucasmori.search.autocomplete.system.sfs.Trie;
import com.lucasmori.search.autocomplete.system.util.CSVUtil;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<String> words = List.of("t=0", "to=10", "te=2", "tea=11", "ted=8", "ten=15");
        Trie trie = new Trie(words);

        System.out.println(trie.suggest("te"));

//        new Aggregator().execute();

    }
}
