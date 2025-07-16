package dev.monogon.cue.lang.psi.impl;

import dev.monogon.cue.CueLightTest;
import dev.monogon.cue.lang.psi.CueLabelExpr;
import org.junit.Test;

public class CueLabelExprImplTest extends CueLightTest {
    @Test
    public void constraintOptional() {
        createCueFile("""
                          #Person: {
                              <caret>name!: string
                              age?:  int
                          }""");

        var field = findTypedElement(CueLabelExpr.class);
        assertTrue(field.isRequiredFieldConstraint());
        assertFalse(field.isOptionalFieldConstraint());
    }

    @Test
    public void constraintRequired() {
        createCueFile("""
                          #Person: {
                              name!: string
                              <caret>age?:  int
                          }""");

        var field = findTypedElement(CueLabelExpr.class);
        assertFalse(field.isRequiredFieldConstraint());
        assertTrue(field.isOptionalFieldConstraint());
    }
}