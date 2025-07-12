package TDA;
/**
 *
 * @author YUSTIN
 * @param <T>
 */
public class Nodo<T> {
    // ATRIBUTOS
    private T item; // Datos
    private Nodo sgteNodo;       // Puntero
    // METODOS
    public Nodo(){
        this.item = null;
        this.sgteNodo = null;
    }
    public Nodo(T item, Nodo sgteNodo){
        this.item = item;
        this.sgteNodo = sgteNodo;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Nodo getSgteNodo() {
        return sgteNodo;
    }

    public void setSgteNodo(Nodo sgteNodo) {
        this.sgteNodo = sgteNodo;
    }
    
    
}
