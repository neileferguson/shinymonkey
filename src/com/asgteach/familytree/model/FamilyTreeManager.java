/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.model;

import java.util.*;
import java.beans.*;

/**
 *
 * @author Neil
 */
public class FamilyTreeManager {
    private final Map<Long, Person> personMap = new HashMap<>();
    private PropertyChangeSupport propChangeSupport = null;
    
    // FamilyTreeManager property change names
    
    public static final String PROP_PERSON_DESTROYED = "removePerson";
    public static final String PROP_PERSON_ADDED = "addPerson";
    public static final String PROP_PERSON_UPDATED = "updatePerson";
    
    private static FamilyTreeManager instance = null;
    
    protected FamilyTreeManager(){
        // exists only to defeat instantiation
    }
    
    public static FamilyTreeManager getInstance(){
        if (instance == null){
            instance = new FamilyTreeManager();
            instance.propChangeSupport = new PropertyChangeSupport(instance);
        }
        return instance;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propChangeSupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propChangeSupport.removePropertyChangeListener(listener);
    }
    
    public void addPerson(Person p){
        Person person = new Person(p);
        personMap.put(person.getId(), person);
        this.propChangeSupport.firePropertyChange(PROP_PERSON_ADDED, null, person);
    }
    
    public void updatePerson(Person p){
        Person person = new Person(p);
        personMap.put(person.getId(), person);
        this.propChangeSupport.firePropertyChange(PROP_PERSON_UPDATED, null, person);
    }
    
    public void deletePerson(Person p){
        Person person = personMap.remove(p.getId());
        if (person != null){
            this.propChangeSupport.firePropertyChange(PROP_PERSON_DESTROYED, null, person);
        }
    }
    
    public List<Person> getAllPeople() {
        List<Person> copyList = new ArrayList<>();
        for (Person p : personMap.values()) {
            // make a copy of Person
            copyList.add(new Person(p));
        }
        return copyList;
    }
}
