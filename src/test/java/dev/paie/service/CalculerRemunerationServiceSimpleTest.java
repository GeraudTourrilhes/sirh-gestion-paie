package dev.paie.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.*;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class, JeuxDeDonneesConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test

@RunWith(SpringRunner.class)
public class CalculerRemunerationServiceSimpleTest {
	
	@Autowired private CalculerRemunerationService remunerationService;
	
	@Autowired private BulletinSalaire bulletin;

    @Test
    public void test_calculer() {
        ResultatCalculRemuneration resultat = remunerationService.calculer(bulletin);
        assertThat(resultat.getSalaireBrut()).isEqualTo("2683.30");
        assertThat(resultat.getTotalRetenueSalarial()).isEqualTo("517.08");
        assertThat(resultat.getTotalCotisationsPatronales()).isEqualTo("1096.13");
        assertThat(resultat.getNetImposable()).isEqualTo("2166.22");
        assertThat(resultat.getNetAPayer()).isEqualTo("2088.41");
    }
}
