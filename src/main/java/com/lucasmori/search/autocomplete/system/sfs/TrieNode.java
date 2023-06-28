package com.lucasmori.search.autocomplete.system.sfs;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children;
    private char c;
    private int frequency;
    private boolean isWord;

    public TrieNode(char c, int frequency) {
        this.c = c;
        this.frequency = frequency;
        this.children = new HashMap<>();
    }

    public TrieNode(char c) {
        this.c = c;
        this.children = new HashMap<>();
    }

    public TrieNode() {
        this.children = new HashMap<>();
    }

    public void insert(String word) {
        if (word == null || word.isEmpty()) return;
        char firstChar = word.charAt(0);
        TrieNode child = children.get(firstChar);
        if (child == null) {
            child = new TrieNode(firstChar);
            children.put(firstChar, child);
        }

        if (word.length() > 1) {
            child.insert(word.substring(1));
        } else {
            child.isWord = true;
        }
    }
    public void insert(String word, int frequency) {
        if (word == null || word.isEmpty()) return;
        char firstChar = word.charAt(0);
        TrieNode child = children.get(firstChar);
        if (child == null) {
            child = new TrieNode(firstChar, frequency);
            children.put(firstChar, child);
        }

        if (word.length() > 1) {
            child.insert(word.substring(1));
        } else {
            child.isWord = true;
        }
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isWord() {
        return isWord;
    }

    public char getC() {
        return c;
    }

    public int getFrequency() {
        return frequency;
    }
}
