package TDA;
/**
 *
 * @author YUSTIN
 * @param <T>
 */
public class Cola<T> {
    private Nodo<T> frente; // apunta al primer item de la cola
    private Nodo<T> ultimo; // apunta al ultimo item de la cola
    
    // Constructor para crear un objeto del tipo Cola
    // Este método o cosntructro sirve para crear una cola vacia
    public Cola(){
        frente = null;
        ultimo = null;        
    }
    // Metodos u operaciones de la cola
    /*
    esVacia(): Determinar si la cola esta vacio o tiene elementos
    */
    public boolean esVacia(){
        return frente==null;
    }
    /*
    encolar: Sirve para agregar un elemento en la cola. La unica
    forma de agregar un elemento a la cola es al final
    Parametros: se debe indicar el item a agregar a la cola
    */
    public void encolar(T item){
        Nodo<T> nuevoNodo = new Nodo(item,null);
        if (esVacia()){
            frente = nuevoNodo;
            ultimo = nuevoNodo;
        }else{
            ultimo.setSgteNodo(nuevoNodo);
            ultimo = nuevoNodo;
        }
    }
    /*
    * desencolar: eliminar un elemento de la cola. Pero este 
    elemento a eliminar debe estar al frente de la cola necesariamente.
    */
    public T desencolar(){
        if (esVacia())
        {
            throw new RuntimeException("La cola esta vacia!!");
        }else{
            // Conocer el valor del item que sera atendido
            T itemAux = frente.getItem();
            // eliminar de la lista o colar el primer nodo
            frente = frente.getSgteNodo();
            return itemAux;
        }            
    }
    
    
}
