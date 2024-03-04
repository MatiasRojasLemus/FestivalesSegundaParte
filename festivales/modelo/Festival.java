package festivales.modelo;

import festivales.io.FestivalesIO;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;

/**
 * Un objeto de esta clase almacena los datos de un
 * festival.
 * Todo festival tiene un nombre, se celebra en un lugar
 * en una determinada fecha, dura una serie de d�as y
 * se engloba en un conjunto determinado de estilos
 *
 * @author Matias Rojas Lemus
 */
public class Festival {
    private final String nombre;
    private final String lugar;
    private final LocalDate fechaInicio;
    private final int duracion;
    private final HashSet<Estilo> estilos;
    
    
    public Festival(String nombre, String lugar, LocalDate fechaInicio, int duracion, HashSet<Estilo> estilos) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.estilos = estilos;
        
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getLugar() {
        return lugar;
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    
    public int getDuracion() {
        return duracion;
    }
    
    public HashSet<Estilo> getEstilos() {
        return estilos;
    }
    
    public void addEstilo(Estilo estilo) {
        this.estilos.add(estilo);
    }

    /**
     * devuelve el mes de celebraci�n del festival, como
     * valor enumerado
     */
    public Mes getMes() {
        switch(this.fechaInicio.getMonthValue()){
            case 1:
                return Mes.ENERO;
            case 2:
                return Mes.FEBRERO;

            case 3:
                return Mes.MARZO;

            case 4:
                return Mes.ABRIL;

            case 5:
                return Mes.MAYO;

            case 6:
                return Mes.JUNIO;

            case 7:
                return Mes.JULIO;

            case 8:
                return Mes.AGOSTO;

            case 9:
                return Mes.SEPTIEMBRE;

            case 10:
                return Mes.OCTUBRE;

            case 11:
                return Mes.NOVIEMBRE;

            case 12:
                return Mes.DICIEMBRE;
        }
        return null;
    }

    /**
     *
     * @param otro
     * @return true si el festival actual empieza
     * en un fecha anterior a otro
     */
    public boolean empiezaAntesQue(Festival otro) {
        return this.fechaInicio.isBefore(otro.getFechaInicio());
    }

    /**
     *
     * @param otro
     * @return true si el festival actual empieza
     * en un fecha posteior a otro
     */
    public boolean empiezaDespuesQue(Festival otro) {
        return this.fechaInicio.isAfter(otro.getFechaInicio());
    }

    /**
     *
     * @return true si el festival ya ha concluido
     */
    public boolean haConcluido() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaFinalFestival = this.fechaInicio.plusDays(this.duracion);
        return fechaActual.isAfter(fechaFinalFestival);
    }

    /**
     * Representaci�n textual del festival, exactamente
     * como se indica en el enunciado
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNombre()).append("\t\t\t").append(getEstilos()).append("\n");
        sb.append(getLugar()).append("\t").append("\n");



        if (this.getDuracion() == 1){
            sb.append(this.getFechaInicio()).append("\t");
        }
        else {
            sb.append(this.getFechaInicio()).append(" - ").append(this.getFechaInicio().plusDays(this.getDuracion()));
        }


        if (this.haConcluido()){
            sb.append(" (concluido)\n");
        }
        else {
            if (LocalDate.now().isAfter(this.fechaInicio) && LocalDate.now().isBefore(this.fechaInicio.plusDays(this.duracion))){
                sb.append(" (ON)\n");
            }
            else {
                LocalDate fechaActual = LocalDate.now();
                int numeroDiaActual = fechaActual.getDayOfMonth();
                Month mesActual = fechaActual.getMonth();
                int anyoActual = fechaActual.getYear();
                int tiempoTardado = LocalDate.of(anyoActual,mesActual,numeroDiaActual).getDayOfYear() - this.getFechaInicio().getDayOfYear();
                sb.append(" (Quedan ").append(tiempoTardado).append(" dias)\n");
            }
        }

        sb.append("------------------------------------------------------------").append("\n");
        return sb.toString();
    }

    /**
     * C�digo para probar la clase festivales.modelo.Festival
     *
     */
    public static void main(String[] args) {
        System.out.println("Probando clase festivales.modelo.Festival");
        String datosFestival = "Gazpatxo Rock : " +  "valencia: 28-02-2022  :1  :rock" +  ":punk " +  ": hiphop ";
        Festival f1 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f1);
        
        datosFestival = "black sound fest:badajoz:05-02-2022:  21" + ":rock" + ":  blues";
        Festival f2 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f2);
    
        datosFestival = "guitar bcn:barcelona: 28-01-2022 :  170" +  ":indie" + ":pop:fusion";
        Festival f3 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f3);
    
        datosFestival = "  benidorm fest:benidorm:26-01-2022:3" + ":indie" + ": pop  :rock";
        Festival f4 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f4);
      
        
        System.out.println("\nProbando empiezaAntesQue() empiezaDespuesQue()" + "\n");
        if (f1.empiezaAntesQue(f2)) {
            System.out.println(f1.getNombre() + " empieza antes que " + f2.getNombre());
        } else if (f1.empiezaDespuesQue(f2)) {
            System.out.println(f1.getNombre() + " empieza despu�s que " + f2.getNombre());
        } else {
            System.out.println(f1.getNombre() + " empieza el mismo d�a que " + f2.getNombre());
        }

        System.out.println("\nProbando haConcluido()\n");
        System.out.println(f4);
        System.out.println(f4.getNombre() + " ha concluido? " + f4.haConcluido());
        System.out.println(f1);
        System.out.println(f1.getNombre() + " ha concluido? " + f1.haConcluido());



    }
}
