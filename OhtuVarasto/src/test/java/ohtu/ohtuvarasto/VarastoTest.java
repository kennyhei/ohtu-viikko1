
package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
 
public class VarastoTest {
 
    Varasto varasto;
    double vertailuTarkkuus = 0.0001;
 
    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
 
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuusEiVoiOllaNollaaPienempi() {
        varasto = new Varasto(-2);
        Varasto tokaVarasto = new Varasto(-2, 5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, tokaVarasto.getTilavuus(), vertailuTarkkuus);
    }
 
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        Varasto tokaVarasto = new Varasto(10, 0);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, tokaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoEiVoiOllaNollaaPienempi() {
        varasto = new Varasto(10, -5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoEiVoiOllaTilavuuttaSuurempi() {
        varasto = new Varasto(10, 15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);
 
        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);
 
        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataYliTilavuuden() {
        varasto.lisaaVarastoon(20);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(-4);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
 
        double saatuMaara = varasto.otaVarastosta(2);
 
        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
 
    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);
 
        varasto.otaVarastosta(2);
 
        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaEnemmanKuinVarastossaOn() {
        varasto.lisaaVarastoon(4);
        
        double maara = varasto.otaVarastosta(6);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(4, maara, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivistaMaaraaVarastosta() {
        varasto.lisaaVarastoon(5);
        
        double maara = varasto.otaVarastosta(-3);
        assertEquals(0, maara, vertailuTarkkuus);
    }
    
    @Test
    public void toStringPalauttaaOikeanMerkkijonoEsityksen() {
        varasto.lisaaVarastoon(5);
        
        String mjono = varasto.toString();
        
        assertEquals("saldo = 5.0" + ", vielä tilaa 5.0", mjono);
    }
}