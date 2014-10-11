/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Neil
 */
public class Person implements Serializable {
    
    private final long id;
    /**
     * First Name
     */
    private String firstname;
    private String middlename;
    private String lastname;
    private String suffix;
    private Person.Gender gender;
    private String notes;
    
    private PropertyChangeSupport propChangeSupport = null;
    
    // Property names
    public static final String PROP_FIRST = "firstname";
    public static final String PROP_MIDDLE = "middlename";
    public static final String PROP_LAST = "lastname";
    public static final String PROP_SUFFIX = "suffix";
    public static final String PROP_GENDER = "gender";
    public static final String PROP_NOTES = "notes";

    public Person(Person p){
        this.firstname = p.getFirstname();
        this.middlename = p.getMiddlename();
        this.lastname = p.getLastname();
        this.suffix = p.getSuffix();
        this.gender = p.getGender();
        this.notes = p.getNotes();
        this.id = p.getId();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!firstname.isEmpty()){
            sb.append(firstname);
        }
        if (!middlename.isEmpty()){
            sb.append(" ").append(middlename);
        }
        if (!lastname.isEmpty()){
            sb.append(" ").append(lastname);
        }
        if (!suffix.isEmpty()){
            sb.append(" ").append(suffix);
        }
        return sb.toString();
        //return "Person{" + "id=" + id + ", firstname=" + firstname + ", middlename=" + middlename + ", lastname=" + lastname + ", suffix=" + suffix + ", gender=" + gender + ", notes=" + notes + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        String oldFirstname = this.firstname;
        this.firstname = firstname;
        this.getPropertyChangeSupport().firePropertyChange(PROP_FIRST, oldFirstname, firstname);
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        String oldMiddlename = this.middlename;
        this.middlename = middlename;
        getPropertyChangeSupport().firePropertyChange(PROP_MIDDLE, oldMiddlename, middlename);
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        String oldLastname = this.lastname;
        this.lastname = lastname;
        getPropertyChangeSupport().firePropertyChange(PROP_LAST, oldLastname, lastname);
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        String oldSuffix = this.suffix;
        this.suffix = suffix;
        getPropertyChangeSupport().firePropertyChange(PROP_SUFFIX, oldSuffix, suffix);
       
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        Gender oldGender = this.gender;
        this.gender = gender;
        getPropertyChangeSupport().firePropertyChange(PROP_GENDER, oldGender, gender);
   }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        String oldNotes = this.notes;
        this.notes = notes;
        getPropertyChangeSupport().firePropertyChange(PROP_NOTES, oldNotes, notes);

    }

    
    private static long count = 0;

    public long getId() {
        return id;
    }
    
    public enum Gender {
        MALE, FEMALE, UNKNOWN;
    }
    
    public Person(){
        this("", "", Gender.UNKNOWN);
    }
    
    public Person(String first, String last, Person.Gender gender){
        this.firstname = first;
        this.middlename = "";
        this.lastname = last;
        this.suffix="";
        this.gender = gender;
        this.id = count++;
    }
    
    // Convenience method to make sure object is fully constructed
    // before passing to PropertyChangeSupport constructor
    private PropertyChangeSupport getPropertyChangeSupport(){
        if (this.propChangeSupport == null){
            this.propChangeSupport = new PropertyChangeSupport(this);
        }
        return this.propChangeSupport;
    }
    public void addPropertyChangeListener(PropertyChangeListener listener){
        getPropertyChangeSupport().addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener){
        getPropertyChangeSupport().removePropertyChangeListener(listener);
    }
}
