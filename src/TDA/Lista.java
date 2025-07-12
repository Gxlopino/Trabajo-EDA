package TDA;
/**
 *
 * @author YUSTIN
 * @param <T>
 */
public class Lista<T> {
    // Atributos
    private Nodo<T> cabeza;
    private Nodo<T> ultimo;
    
    // Metodos
    public Lista(){
        cabeza = null;
        ultimo = null;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo<T> cabeza) {
        this.cabeza = cabeza;
    }

    public Nodo<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo<T> ultimo) {
        this.ultimo = ultimo;
    }
    
    /*
    * esVacia: determinar si una lista esta vacia
    */
    public boolean esVacia(){
        return cabeza==null;
    }
    /*
    * Agregar un elemento al final de la lista
    */
    public void agregar(T item){
        Nodo<T> nuevoNodo = new Nodo(item,null);
        // Caso 1: cuando la lista vacia
        if (esVacia()){
            cabeza = nuevoNodo;
            ultimo = nuevoNodo;
        }else{ // la lista tiene elementos
            // Ubicarnos en el ultimo nodo
            ultimo.setSgteNodo(nuevoNodo);
            ultimo = nuevoNodo;            
        }
    }
    /*
    * mostra los elementos de la lista
    */
    public void mostrar(){
        Nodo<T> aux = this.cabeza;
        // Imgrese al bucle siempre que tenga elementos
        while (aux!=null){ // La lista no esta vacia
            System.out.print(aux.getItem()+"\t");
            aux = aux.getSgteNodo();
        }
        
        System.out.println("");
    }
    /*
    * Longitud, devuelve la cantidad de nodos en la lista
    */
    public int longitud(){
        Nodo<T> aux = cabeza;
        int cont=0;
        while (aux !=null){
            cont++;
            aux = aux.getSgteNodo(); // Avanza al siguiente nodo
        }
        return cont;
    }
    /*
    * Ubicacion
    */
    public int ubicacion(T item){
        int pos=0;
        Nodo<T> aux = cabeza;
        while (aux!=null){
            pos++;
            // verificar si el item esta en el nodo actual
            if (aux.getItem().equals(item)){
                return pos;
            }
            aux = aux.getSgteNodo();
        }
        return -1; // Error
    }
    /*
    * Iesimo: devuelve el item de la iesima posicion
    */
    public T iesimo(int pos){
        if (pos>=1&&pos<=longitud()){
            int i=1;
            Nodo<T> aux = cabeza;
            while (i<pos){
                i++;
                aux=aux.getSgteNodo();
            }
            return aux.getItem();
        }else{
            return null;
        }
        
    }
    /*
    * Eliminar un nodo de una determinada posicion
    * recibe como parametro la pos del elemento a eliminar
    */
    public void eliminar(int pos){
        if (pos>=1 && pos<=longitud()){
            if (pos==1){ // se quiere eliminar el primer nodo
                Nodo<T> aux = cabeza;
                cabeza = aux.getSgteNodo();
            }else{ //>1 && <= longitud()
                int i = 1;
                Nodo<T> aux = cabeza;
                while (i<pos-1){
                    aux = aux.getSgteNodo();
                    i++;
                }
                Nodo<T> aux2 = aux.getSgteNodo();
                aux.setSgteNodo(aux2.getSgteNodo());
                if (aux2.equals(ultimo)){
                    ultimo = aux;
                }                                
            }            
        }else{
            System.err.println("La posicion no es valida o la lista esta vacia");
        }
    }
    /*
    * insertar: Insertar un item en la lista e una determinada posicion
    */
    public void insertar(T item, int pos){
        if (pos>=1 && pos<=longitud()+1){
            Nodo<T> nuevoNodo = new Nodo(item,null);
            if (pos == 1){ // el nodo debe insertarse en la primera posicion
                nuevoNodo.setSgteNodo(cabeza);
                cabeza = nuevoNodo;
            }else{ // pos>1
                // Ubicarse en el nodo anterior
                Nodo<T> aux = cabeza;
                int i=1;
                while (i<pos-1){
                    aux = aux.getSgteNodo();
                    i++;
                }
                nuevoNodo.setSgteNodo(aux.getSgteNodo());
                aux.setSgteNodo(nuevoNodo);
            }
        }else{
            System.out.println("No es posible insertar por que la posicion no es valida");
        }
        
    }
    
    public void contarDuplicados(T item) {
        Nodo ptr = cabeza;
        int count = 0;
        
        while (ptr != null) {
            if (ptr.getItem().equals(item)) count++;
            
            ptr = ptr.getSgteNodo();
        }
        
        System.out.println("Instancias de " + item.toString() + ": " + count);
    }
    public void intercambiar(int pos1, int pos2){ 
     Nodo<T> aux=this.cabeza;
     Nodo<T> aux2=this.cabeza;
     int i1=1;
       while (i1 < pos1) {
                aux = aux.getSgteNodo();
                i1++;
            }
       i1=1;
              while (i1 < pos2) {
                aux2 = aux2.getSgteNodo();
                i1++;
            }
             T item=aux.getItem();
             aux.setItem(aux2.getItem());
             aux2.setItem(item);
    }
    public void agregarSR(T[] item) {
       int i=0;
       while(i<item.length){
        T num=item[i];
        if(this.ubicacion(num)==-1){
            this.agregar(num);
        }
        i++;
       }
      this.mostrar();
    }

}
