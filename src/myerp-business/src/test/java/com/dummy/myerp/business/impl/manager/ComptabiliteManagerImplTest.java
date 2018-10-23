package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.util.Date;

import com.dummy.myerp.model.bean.comptabilite.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import com.dummy.myerp.technical.exception.FunctionalException;

public class ComptabiliteManagerImplTest {

    private Logger logger = LogManager.getLogger(this.getClass());

    private EcritureComptable vEcritureComptable;
    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

    @Before
    public void setUp(){

        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
    }

    @Test
    public void addReference(){

    }

    @Test()
    public void checkEcritureComptableUnit() throws Exception {

        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123), null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null, new BigDecimal(123)));

        manager.checkEcritureComptableUnit(vEcritureComptable);

        vEcritureComptable.getListLigneEcriture().clear();
    }


    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitViolation() throws Exception {

        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2() throws Exception {

        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                        null, new BigDecimal(123), null));

        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                        null, null, new BigDecimal(1234)));

        manager.checkEcritureComptableUnit(vEcritureComptable);

        vEcritureComptable.getListLigneEcriture().clear();
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3() throws Exception {

        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123), null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123), null));

        manager.checkEcritureComptableUnit(vEcritureComptable);

        vEcritureComptable.getListLigneEcriture().clear();
    }
}
