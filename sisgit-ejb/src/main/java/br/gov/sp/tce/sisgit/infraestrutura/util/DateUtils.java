package br.gov.sp.tce.sisgit.infraestrutura.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * 
 */
public class DateUtils {

    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    public static DateFormat getDateFormat() {
        return df;
    }
    
    public static Calendar getCalendar(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar;
    }

    public static int getHoraComoMinutos(Date data) {
        Calendar calendar = getCalendar(data);

        return calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
    }

    public static Date truncateMinutos(Date data) {
        Calendar calendar = getCalendar(data);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date truncateDias(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(truncateMinutos(data));
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }

    public static Date truncateMes(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(truncateDias(data));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date addDays(Date data, int valor) {
        Calendar calendar = getCalendar(data);
        calendar.add(Calendar.DAY_OF_YEAR, valor);
        return calendar.getTime();
    }

    public static Date addMinutes(Date data, int valor) {
        Calendar calendar = getCalendar(data);
        calendar.add(Calendar.MINUTE, valor);
        return calendar.getTime();
    }

    public static Date addMonths(Date data, int valor) {
        Calendar calendar = getCalendar(data);
        calendar.add(Calendar.MONTH, valor);
        return calendar.getTime();
    }

    public static Date addSeconds(Date data, int valor) {
        Calendar calendar = getCalendar(data);
        calendar.add(Calendar.SECOND, valor);
        return calendar.getTime();
    }

    public static int getYear(Date data) {
        Calendar calendar = getCalendar(data);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date data) {
        Calendar calendar = getCalendar(data);
        return calendar.get(Calendar.MONTH);
    }

    public static Date setMonth(Date data, int mes) {
        Calendar calendar = getCalendar(data);
        calendar.set(Calendar.MONTH, mes);
        return calendar.getTime();
    }

    public static Date setYear(Date data, int ano) {
        Calendar calendar = getCalendar(data);
        calendar.set(Calendar.YEAR, ano);
        return calendar.getTime();
    }

    public static Date setMonthYear(Date data,int mes, int ano) {
        Calendar calendar = getCalendar(data);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);        
        return calendar.getTime();
    }
    
    public static int getDiaDaSemanaPrimeiroDia(int mes, int ano) {
        Calendar calendar = getCalendar(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);
        return calendar.get(Calendar.DAY_OF_WEEK);        
    }
    
    public static int getDiasNoMes(int mes, int ano) {
        Calendar calendar = getCalendar(new Date());
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public static Date getPrimeiroDiaMes(Date data) {
        Calendar calendar = getCalendar(data);
        calendar.setTime(truncateDias(data));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
    
    public static Date getUltimoDiaMes(Date data) {
        Calendar calendar = getCalendar(data);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    
    public static Date getData(int dia, int mes, int ano) {
        Calendar calendar = Calendar.getInstance();        
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }
    
    public static String formataTempoEmHorasMinutos(Integer minutos) {
        if(minutos == null) {
            return null;
        }
        boolean negativo = minutos < 0 ? true : false;
        int min = minutos % 60;
        int hh = (minutos - min) / 60;
        String tempoFormatado = strzero(hh) + ":" + strzero(min);
        if(negativo) {
            tempoFormatado = tempoFormatado.replace("-", "");
            tempoFormatado = "-" + tempoFormatado;
        }
        return tempoFormatado;
    }
    
    public static String formataTempoEmHorasMinutos(BigDecimal minutos) {
        return minutos == null ? null : 
                formataTempoEmHorasMinutos(minutos.toBigInteger().intValue());
    }

    private static String strzero(int n) {
        if ((n < 10 && n >= 0) || (n > -10 && n <= 0)) {
            return "0" + String.valueOf(n);
        }
        return String.valueOf(n);
    }
}
