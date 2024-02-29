
import java.security.Key;
import java.time.Month;
import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 *
 * La agenda guardalos festivales en una colección map
 * La clave del map es el mes (un enumerado festivales.modelo.Mes)
 * Cada mes tiene asociados en una colección ArrayList
 * los festivales  de ese mes
 *
 * Solo aparecen los meses que incluyen algún festival
 *
 * Las claves se recuperan en orden alfabéico
 *
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;
    
    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * añade un nuevo festival a la agenda
     *
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se creará una nueva entrada
     * con dicha clave y la colección formada por ese único festival
     *
     * Si la clave (el mes) ya existe se añade el nuevo festival
     * a la lista de festivales que ya existe ese mes
     * insertándolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el método de ayuda
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
            festivales.add(obtenerPosicionDeInsercion(festivales,festival),festival);
        }
        
    }

    /**
     *
     * @param festivales una lista de festivales
     * @param festival
     * @return la posición en la que debería ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales,
                                           Festival festival) {
        int indice = 0;

        TreeSet<Festival> TSAux = new TreeSet<>(festivales);
        TSAux.add(festival);
        for (Festival fest: TSAux){
            if (fest == festival){
                return indice;
            }
            indice++;
        }
        return 0;
    }

    /**
     * Representación textual del festival
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
     * Cada estilo que aparece en la agenda tiene asociada una colección
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     *
     * Identifica el tipo exacto del valor de retorno
     */
    public TreeMap<Estilo, TreeSet<Festival>>  festivalesPorEstilo() {
       //TODO
        Set<Mes> conjuntoClavesAgenda = this.agenda.keySet();

        //Se crea la estructura de datos que devolvera el metodo:
        TreeMap<Estilo, TreeSet<Festival>> festivalesPorEstilo = new TreeMap<>();

        //Se anyaden todas sus claves (estilos), con valores nulos que luego seran anyadidos
        for (Estilo estilo: Estilo.values()){
            festivalesPorEstilo.put(estilo,null);
        }
        ArrayList<Festival> festivales;
        for (Mes mes: conjuntoClavesAgenda){
            festivales = this.agenda.get(mes);
            for (Festival festival: festivales){
                HashSet<Estilo> conjuntoEstilos = festival.getEstilos();
                for (Estilo estilo: conjuntoEstilos){
                    TreeSet<Festival> valorFestivalesPorEstilo = festivalesPorEstilo.get(estilo);
                    valorFestivalesPorEstilo.add(festival);
                }
            }

        }


        return null;
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
        
        return 0;
    }
}
