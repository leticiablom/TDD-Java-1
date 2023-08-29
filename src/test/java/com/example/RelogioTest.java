package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Testes Unit para a classe Relogio
 */

class RelogioTest {

    Relogio relogio = new Relogio();

    @Test
    void testDefinirHorario() {

        relogio.definirHorario(10, 30, 0);
        int result = relogio.obterHorario();
        assertEquals("10:30:00", result);
    }

    @Test
    void testReiniciarMeiaNoite() {

        // setando horario diferente de meia noite
        relogio.definirHorario(15, 45, 30);

        relogio.reiniciarMeiaNoite();
        int result = relogio.obterHorario();
        assertEquals("00:00:00", result);
    }

    @Test
    void testMarcarIntervaloTempo() {

        // setando horario
        relogio.definirHorario(8, 0, 0);
        relogio.marcarInicioIntervalo();

        //
        relogio.definirHorario(8, 30, 0);
        relogio.marcarFimIntervalo();
        int result = relogio.obterTempoDecorrido();
        assertEquals("00:30:00", result);
    }

    @Test
    void testImprimirFormato24h() {

        relogio.definirHorario(18, 15, 0);
        int result = relogio.imprimirFormato24h();
        assertEquals("18:15:00", result);
    }

    @Test
    void testImprimirFormatoAMPM() {

        relogio.definirHorario(10, 45, 0);
        int result = relogio.imprimirFormatoAMPM();
        assertEquals("10:45 AM", result);
    }

    @Test
    void testAtualizarHorario() {

        relogio.definirHorario(23, 59, 0);
        // Simula passagem de tempo...
        relogio.atualizarHorario();
        int result = relogio.obterHorario();
        assertEquals("00:00:00", result);
    }

}