package com.dummy.myerp.model.bean.comptabilite;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EcritureComptableTest {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void isEquilibree(){

        EcritureComptable ecriture = Mockito.mock(EcritureComptable.class);

        Mockito.doCallRealMethod().when(ecriture).isEquilibree();

        // totalCredit = 100, totalDebit = 100
        Mockito.when(ecriture.getTotalCredit()).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(ecriture.getTotalDebit()).thenReturn(BigDecimal.valueOf(100));
        Assert.assertTrue(ecriture.isEquilibree());

        // totalCredit = 100, totalDebit = 10
        Mockito.when(ecriture.getTotalDebit()).thenReturn(BigDecimal.valueOf(10));
        Assert.assertFalse(ecriture.isEquilibree());

        // Negative values, totalCredit = -100, totalDebit = -100
        Mockito.when(ecriture.getTotalCredit()).thenReturn(BigDecimal.valueOf(-100));
        Mockito.when(ecriture.getTotalDebit()).thenReturn(BigDecimal.valueOf(-100));
        Assert.assertTrue(ecriture.isEquilibree());

        // Negative values, totalCredit = -100, totalDebit = -10
        Mockito.when(ecriture.getTotalDebit()).thenReturn(BigDecimal.valueOf(-10));
        Assert.assertFalse(ecriture.isEquilibree());
    }

}
