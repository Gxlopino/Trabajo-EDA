package TDA;
/**
 * @author YUSTIN
 * @param <T>
 */
public class NodoDoble<T> {
    // ATRIBUTOS
    private T item; // Datos
    private NodoDoble<T> sgteNodo;       // Puntero
    private NodoDoble<T> antNodo;
    // METODOS
    public NodoDoble(){
        this.item = null;
        this.sgteNodo = null;
        this.antNodo = null;
    }
    public NodoDoble(T item, NodoDoble<T> sgteNodo, NodoDoble<T> antNodo){
        this.item = item;
        this.sgteNodo = sgteNodo;
        this.antNodo = antNodo;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public NodoDoble getSgteNodo() {
        return sgteNodo;
    }

    public void setSgteNodo(NodoDoble<T> sgteNodo) {
        this.sgteNodo = sgteNodo;
    }

    public NodoDoble<T> getAntNodo() {
        return antNodo;
    }

    public void setAntNodo(NodoDoble<T> antNodo) {
        this.antNodo = antNodo;
    }
    
    
}
