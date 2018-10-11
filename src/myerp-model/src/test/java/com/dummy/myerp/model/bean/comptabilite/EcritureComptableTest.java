package com.dummy.myerp.model.bean.comptabilite;

import java.math.BigDecimal;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EcritureComptableTest {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);

        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();

        return new LigneEcritureComptable(
                new CompteComptable(pCompteComptableNumero),
                vLibelle,
                vDebit, vCredit);
    }

    @Test
    public void isEquilibree() {

        EcritureComptable vEcriture = new EcritureComptable();
        //EcritureComptable vEcriture = Mockito.mock(EcritureComptable.class);

        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));

        System.out.println("Equilibrée : " + vEcriture.getTotalCredit() + " / " + vEcriture.getTotalDebit());
        Assert.assertTrue(vEcriture.toString(), vEcriture.isEquilibree());

        vEcriture.getListLigneEcriture().clear();

        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "2", "20"));

        System.out.println("Non Equilibrée : " + vEcriture.getTotalCredit() + " / " + vEcriture.getTotalDebit());
        Assert.assertFalse(vEcriture.toString(), vEcriture.isEquilibree());
    }

}
