/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
/**
 *
 * @author cli
 */
public class ProfileScolaire {
    ArrayList <Note> notes;
}

class Note{
    private String module;
    private int note;
    private boolean valide;
    
    public Note(String module, int note) {
        this.module = module;
        this.note = note;
        this.valide = (note>10);
    }
}