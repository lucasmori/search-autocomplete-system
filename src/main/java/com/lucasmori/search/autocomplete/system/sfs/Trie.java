package com.lucasmori.search.autocomplete.system.sfs;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root;

    public Trie(List<String> words) {
        this.root = new TrieNode();

        for (String word : words) {
            String[] parts = word.split("=");
            String w = parts[0];
            int frequency = Integer.parseInt(parts[1]);
            this.root.insert(w, frequency);
        }
    }

    public boolean find(String prefix, boolean exact) {
        TrieNode lastNode = root;
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.getChildren().get(c);
            if (lastNode == null) {
                return false;
            }
        }
        return !exact || lastNode.isWord();
    }

    public boolean find(String prefix) {
        return find(prefix, false);
    }

    public void suggestHelper(TrieNode root, List<String> list, StringBuffer curr) {
        if (root.isWord()) {
            list.add(curr.toString());
        }

        if (root.getChildren() == null || root.getChildren().isEmpty()) {
            return;
        }

        for (TrieNode child : root.getChildren().values()) {
            suggestHelper(child, list, curr.append(child.getC()));
            curr.setLength(curr.length() - 1);
        }
    }

    public List<String> suggest(String prefix) {
        List<String> list = new ArrayList<>();
        TrieNode lastNode = root;
        StringBuffer curr = new StringBuffer();
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.getChildren().get(c);
            if (lastNode == null)
                return list;
            curr.append(c);
        }
        suggestHelper(lastNode, list, curr);
        return list;
    }
}
