package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Testes Unit para a classe Relogio
 */
class RelogioTest {

    private Relogio relogio;
    private DateTimeFormatter formato24h = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DateTimeFormatter formatoAMPM = DateTimeFormatter.ofPattern("hh:mm a");

    @BeforeEach
    void setUp() {
        relogio = new Relogio();
    }

    @Test
    void testDefinirHorario() {
        relogio.definirHorario(10, 30, 0);
        assertEquals("10:30:00", relogio.imprimirFormato24h());
    }

    @Test
    void testReiniciarMeiaNoite() {
        relogio.definirHorario(15, 45, 30);
        relogio.reiniciarMeiaNoite();
        assertEquals("00:00:00", relogio.imprimirFormato24h());
    }

    @Test
    void testMarcarIntervaloTempo() {
        relogio.marcarInicioIntervalo(8, 0, 0);
        relogio.marcarFimIntervalo(8, 30, 0);
        assertEquals("00:30:00", relogio.cronometro());
    }

    @Test
    void testImprimirFormato24h() {
        relogio.definirHorario(18, 15, 0);
        assertEquals("18:15:00", relogio.imprimirFormato24h());
    }

    @Test
    void testImprimirFormatoAMPM() {
        relogio.definirHorario(10, 45, 0);
        assertEquals("10:45 AM", relogio.imprimirFormatoAMPM());
    }

    @Test
    void testAtualizarHorario() {
        LocalTime localTime = LocalTime.now();
        relogio.definirHorario(23, 59, 0);
        relogio.atualizarHorario24h();
        assertEquals(formato24h.format(localTime), relogio.imprimirFormato24h());

        relogio.definirHorario(23, 59, 0);
        relogio.atualizarHorarioAMPM();
        assertEquals(formatoAMPM.format(localTime), relogio.imprimirFormatoAMPM());
    }

    @Test
    void testCronometro() {
        relogio.marcarInicioIntervalo(10, 0, 0);
        relogio.marcarFimIntervalo(11, 30, 0);
        String tempoDecorrido = relogio.cronometro();
        assertEquals("01:30:00", tempoDecorrido);
    }
}
