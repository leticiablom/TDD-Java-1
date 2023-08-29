package com.example;

import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe Relogio
 */

class Relogio {

    private int horas;
    private int minutos;
    private int segundos;

    private int horasInicio;
    private int minutosInicio;
    private int segundosInicio;

    private int horasTermino;
    private int minutosTermino;
    private int segundosTermino;

    private DateTimeFormatter formato24h = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DateTimeFormatter formatoAMPM = DateTimeFormatter.ofPattern("hh:mm a");

    // certo
    public void definirHorario(int horas, int minutos, int segundos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    // certo
    public void reiniciarMeiaNoite() {
        this.horas = 0;
        this.minutos = 0;
        this.segundos = 0;
    }

    public String cronometro() {
        int totalSegundosInicio = this.horasInicio * 3600 + this.minutosInicio * 60 + this.segundosInicio;
        int totalSegundosTermino = this.horasTermino * 3600 + this.minutosTermino * 60 + this.segundosTermino;

        int segundosDecorridos = totalSegundosTermino - totalSegundosInicio;
        int horasDecorridas = segundosDecorridos / 3600;
        int minutosDecorridos = (segundosDecorridos % 3600) / 60;
        int segundosRestantes = segundosDecorridos % 60;

        return String.format("%02d:%02d:%02d", horasDecorridas, minutosDecorridos, segundosRestantes);
    }

    public void marcarInicioIntervalo(int hora, int minutos, int segundos) {
        this.horasInicio = hora;
        this.minutosInicio = minutos;
        this.segundosInicio = segundos;
    }

    public void marcarFimIntervalo(int hora, int minutos, int segundos) {
        this.horasTermino = hora;
        this.minutosTermino = minutos;
        this.segundosTermino = segundos;
    }

    // certo
    public String imprimirFormato24h() {
        LocalTime localTime = LocalTime.of(horas, minutos, segundos);
        return formato24h.format(localTime);
    }

    // certo
    public String imprimirFormatoAMPM() {
        LocalTime localTime = LocalTime.of(horas, minutos, segundos);
        return formatoAMPM.format(localTime);
    }

    // certo
    public String atualizarHorario24h() {
        LocalTime localTime = LocalTime.now();
        this.horas = localTime.getHour();
        this.minutos = localTime.getMinute();
        this.segundos = localTime.getSecond();
        return formato24h.format(localTime);
    }

    // certo
    public String atualizarHorarioAMPM() {
        LocalTime localTime = LocalTime.now();
        this.horas = localTime.getHour();
        this.minutos = localTime.getMinute();
        this.segundos = localTime.getSecond();
        return formatoAMPM.format(localTime);
    }

    public static void main(String[] args) {
        Relogio relogio = new Relogio();
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("Opções------------------------------------");
            System.out.println("[1] - Definir horário");
            System.out.println("[2] - Reiniciar para meia-noite");
            System.out.println("[3] - Cronômetro");
            System.out.println("[4] - Imprimir horário no formato 24h");
            System.out.println("[5] - Imprimir horário no formato AM/PM");
            System.out.println("[6] - Atualizar horário");
            System.out.println("[0] - Sair");
            System.out.println("Escolha uma opção---------------------------");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Informe a hora: ");
                    int horas = relogio.lerValorInteiro(scanner, 0, 23); // Verificação de valor válido
                    System.out.print("Informe os minutos: ");
                    int minutos = relogio.lerValorInteiro(scanner, 0, 59); // Verificação de valor válido
                    System.out.print("Informe os segundos: ");
                    int segundos = relogio.lerValorInteiro(scanner, 0, 59); // Verificação de valor válido
                    relogio.definirHorario(horas, minutos, segundos);
                    break;
                case 2:
                    relogio.reiniciarMeiaNoite();
                    break;
                case 3:
                    System.out.println("Informe a hora de início: ");
                    int horaInicio = relogio.lerValorInteiro(scanner, 0, 23);
                    System.out.print("Informe os minutos: ");
                    int minutosInicio = relogio.lerValorInteiro(scanner, 0, 59);
                    System.out.print("Informe os segundos: ");
                    int segundosInicio = relogio.lerValorInteiro(scanner, 0, 59);

                    System.out.println("Informe a hora de término: ");
                    int horaTermino = relogio.lerValorInteiro(scanner, 0, 23);
                    System.out.print("Informe os minutos: ");
                    int minutosTermino = relogio.lerValorInteiro(scanner, 0, 59);
                    System.out.print("Informe os segundos: ");
                    int segundosTermino = relogio.lerValorInteiro(scanner, 0, 59);

                    relogio.marcarInicioIntervalo(horaInicio, minutosInicio, segundosInicio);
                    relogio.marcarFimIntervalo(horaTermino, minutosTermino, segundosTermino);

                    String tempoDecorrido = relogio.cronometro();
                    System.out.println("Tempo decorrido: " + tempoDecorrido);
                    break;
                case 4:
                    System.out.println("Horário no formato 24h: " + relogio.imprimirFormato24h());
                    break;
                case 5:
                    System.out.println("Horário no formato AM/PM: " + relogio.imprimirFormatoAMPM());
                    break;
                case 6:
                    System.out.println("Digite 1 para imprimir hora atualizada em formato 24h: ");
                    System.out.println("Digite 2 para imprimir hora atualizada em formato AM/PM: ");
                    int resposta = scanner.nextInt();
                    if (resposta == 1) {
                        System.out.println("Horário atualizada : " + relogio.atualizarHorario24h());
                    } else if (resposta == 2) {
                        System.out.println("Horário atualizada : " + relogio.atualizarHorarioAMPM());
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        scanner.close();
    }

    private int lerValorInteiro(Scanner scanner, int limiteInferior, int limiteSuperior) {
        int valor;
        do {
            valor = scanner.nextInt();
            if (valor < limiteInferior || valor > limiteSuperior) {
                System.out.println("Valor inválido. Digite novamente.");
            }
        } while (valor < limiteInferior || valor > limiteSuperior);
        return valor;
    }
}