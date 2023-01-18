package de.fh_zwickau.oose.zuul04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.fh_zwickau.oose.zuul04.model.Hebel;

class HebelTest {

Hebel testhebel;


	@BeforeEach
	void setUp() {
		
	testhebel=new Hebel();

	}	

	@Test
	@DisplayName("Test für den Zustand des Hebels")
	public void SchalterZustandTest() {
		
	assertFalse(testhebel.getZustand());

	}
	
	@Test
	@DisplayName("Test für das Umlegen des Hebels")
	public void SchalterUmlegenTest() {
		
	testhebel.einschalten();	
	assertTrue(testhebel.getZustand());

	}
	
}
