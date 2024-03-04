package festivales.modelo;

import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 *
 * La agenda guardalos festivales en una colecci�n map
 * La clave del map es el mes (un enumerado festivales.festivales.modelo.festivales.modelo.Mes)
 * Cada mes tiene asociados en una colecci�n ArrayList
 * los festivales  de ese mes
 *
 * Solo aparecen los meses que incluyen alg�n festival
 *
 * Las claves se recuperan en orden alfab�ico
 *
 * @author Matias Rojas Lemus
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;
    
    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * a�ade un nuevo festival a la agenda
     *
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se crear� una nueva entrada
     * con dicha clave y la colecci�n formada por ese �nico festival
     *
     * Si la clave (el mes) ya existe se a�ade el nuevo festival
     * a la lista de festivales que ya existe ese mes
     * insert�ndolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el m�todo de ayuda
     * obtenerPosicionDeInsercion()
     *
     */
    public void addFestival(Festival festival) {
        //TODO
        if(!this.agenda.containsKey(festival.getMes())){
            ArrayList<Festival> valorNuevaClave = new ArrayList<>();
            valorNuevaClave.add(festival);
            this.agenda.put(festival.getMes(),valorNuevaClave);
        }
        else {
            ArrayList<Festival> festivales = this.agenda.get(festival.getMes());
            int dondeInsertar = obtenerPosicionDeInsercion(festivales,festival);
            festivales.add(dondeInsertar,festival);
        }
        
    }

    /**
     *
     * @param festivales una lista de festivales
     * @param festival
     * @return la posici�n en la que deber�a ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales, Festival festival) {

        int indice = 0;

        festivales.add(festival);
        TreeSet<String> TSAux = new TreeSet<>();
        for (Festival nombre: festivales){
            TSAux.add(nombre.getNombre());
        }
        TSAux.add(festival.getNombre());


        for (String fest: TSAux){
            if (fest.equals(festival.getNombre())){
                return indice;
            }
            indice++;
        }
        return 0;
    }

    /**
     * Representaci�n textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas para recorrer el map
     */
    @Override
    public String toString() {
        //TODO
        Set<Mes> conjuntoEntradas = this.agenda.keySet();
        ArrayList<Festival> festivales;
        StringBuilder sb = new StringBuilder();

        for (Mes entrada: conjuntoEntradas){
            festivales = this.agenda.get(entrada);
            sb.append(entrada).append(":").append(" ").append(festivales).append("\n");
        }
        return sb.toString();
    }

    /**
     *
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {
       if(this.agenda.containsKey(mes)){
           ArrayList<Festival> festivales = this.agenda.get(mes);
           return festivales.size();
       }
       return 0;
    }

    /**
     * Se trata de agrupar todos los festivales de la agenda
     * por estilo.
     * Cada estilo que aparece en la agenda tiene asociada una colecci�n
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     *
     * Identifica el tipo exacto del valor de retorno
     */
    public TreeMap<Estilo, TreeSet<String>>  festivalesPorEstilo() {

        Set<Mes> conjuntoClavesAgenda = this.agenda.keySet();

        //Se crea la estructura de datos que devolvera el metodo:
        TreeMap<Estilo, TreeSet<String>> festivalesPorEstilo = new TreeMap<>();

        //Se anyaden todas sus claves (estilos), con valores nulos que luego seran anyadidos
        for (Estilo estilo: Estilo.values()){
            festivalesPorEstilo.put(estilo,new TreeSet<String>());
        }
        ArrayList<Festival> festivales;

        //Se recorre cada mes de la agenda
        for (Mes mes: conjuntoClavesAgenda){
            festivales = this.agenda.get(mes);
            //De cada mes se recorre todos los festivales

            for (Festival festival: festivales){
                HashSet<Estilo> conjuntoEstilosDelFestival = festival.getEstilos();

                //De cada festival se recorren sus estilos en Hashset
                for (Estilo estilo: conjuntoEstilosDelFestival){
                    //En cada uno de dichos estilos, las claves/estilo del TreeMap festivalesPorEstilo que coincidan con estos, a su respectivo valor se le anyade el festivales.modelo.Festival que estuviesemos recorriendo actualmente.
                    TreeSet<String> valorFestivalesPorEstilo = festivalesPorEstilo.get(estilo);
                    valorFestivalesPorEstilo.add(festival.getNombre());
                }
            }

        }

        return festivalesPorEstilo;
    }

    /**
     * Se cancelan todos los festivales organizados en alguno de los
     * lugares que indica el conjunto en el mes indicado. Los festivales
     * concluidos o que no empezados no se tienen en cuenta
     * Hay que borrarlos de la agenda
     * Si el mes no existe se devuelve -1
     *
     * Si al borrar de un mes los festivales el mes queda con 0 festivales
     * se borra la entrada completa del map
     */
    public int cancelarFestivales(HashSet<String> lugares, Mes mes) {
       //TODO
        if (this.agenda.containsKey(mes)){
            ArrayList<Festival> festivalesEnMes = this.agenda.get(mes);
            //Esta siguiente estructura fue sugerida por el propio IntelliJ:
            festivalesEnMes.removeIf(festival -> lugares.contains(festival.getLugar()));
            /**
             * Si bien dicha estructura parece funcionar cuando probamos el metodo Main de festivales.test.TestAgendaFestivales,
             * la estructura original que iba a poner era la siguiente:
             *      for (festivales.modelo.Festival festival: festivalesEnMes){
             *          if (lugares.contains(festival.getLugar())){
             *              festivalesEnMes.remove(festival);
             *          }
             *      }
             */
            if (festivalesEnMes.isEmpty()){
                this.agenda.remove(mes);
            }
            return 1;
        }
        return -1;
    }
}
