package TDA;
/**
 *
 * @author YUSTIN
 * @param <T>
 */
public class ListaCircularSimple<T> {

    private Nodo<T> cabeza;
    private Nodo<T> ultimo;

    public ListaCircularSimple() {
        cabeza = null;
        ultimo = null;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    public void agregar(T item) {
        Nodo<T> nuevoNodo = new Nodo<>(item, null);
        if (esVacia()) {
            cabeza = nuevoNodo;
            ultimo = nuevoNodo;
            ultimo.setSgteNodo(cabeza);
        } else {
            ultimo.setSgteNodo(nuevoNodo);
            ultimo = nuevoNodo;
            ultimo.setSgteNodo(cabeza);
        }
    }

    public void mostrar() {
        if (esVacia()) {
            return;
        }
        Nodo<T> aux = cabeza;
        do {
            System.out.print(aux.getItem() + "\t");
            aux = aux.getSgteNodo();
        } while (aux != cabeza);
        
        System.out.println("");
    }

    public int longitud() {
        if (esVacia()) {
            return 0;
        }
        int cont = 0;
        Nodo<T> aux = cabeza;
        do {
            cont++;
            aux = aux.getSgteNodo();
        } while (aux != cabeza);
        return cont;
    }

    public int ubicacion(T item) {
        if (esVacia()) {
            return -1;
        }
        int pos = 1;
        Nodo<T> aux = cabeza;
        do {
            if (aux.getItem().equals(item)) {
                return pos;
            }
            aux = aux.getSgteNodo();
            pos++;
        } while (aux != cabeza);
        return -1;
    }

    public T iesimo(int pos) {
        int len = longitud();
        if (pos >= 1 && pos <= len) {
            Nodo<T> aux = cabeza;
            for (int i = 1; i < pos; i++) {
                aux = aux.getSgteNodo();
            }
            return aux.getItem();
        }
        return null;
    }

    public void eliminar(int pos) {
        int len = longitud();
        if (pos < 1 || pos > len) {
            System.err.println("Posición no válida.");
            return;
        }
        if (pos == 1) {
            if (cabeza == ultimo) {
                cabeza = null;
                ultimo = null;
            } else {
                cabeza = cabeza.getSgteNodo();
                ultimo.setSgteNodo(cabeza);
            }
        } else {
            Nodo<T> aux = cabeza;
            for (int i = 1; i < pos - 1; i++) {
                aux = aux.getSgteNodo();
            }
            Nodo<T> eliminado = aux.getSgteNodo();
            aux.setSgteNodo(eliminado.getSgteNodo());
            if (eliminado == ultimo) {
                ultimo = aux;
            }
        }
    }

    public void insertar(T item, int pos) {
        int len = longitud();
        if (pos < 1 || pos > len + 1) {
            System.out.println("Posición inválida.");
            return;
        }
        Nodo<T> nuevoNodo = new Nodo<>(item, null);
        if (pos == 1) {
            if (esVacia()) {
                cabeza = nuevoNodo;
                ultimo = nuevoNodo;
                ultimo.setSgteNodo(cabeza);
            } else {
                nuevoNodo.setSgteNodo(cabeza);
                cabeza = nuevoNodo;
                ultimo.setSgteNodo(cabeza);
            }
        } else {
            Nodo<T> aux = cabeza;
            for (int i = 1; i < pos - 1; i++) {
                aux = aux.getSgteNodo();
            }
            nuevoNodo.setSgteNodo(aux.getSgteNodo());
            aux.setSgteNodo(nuevoNodo);
            if (aux == ultimo) {
                ultimo = nuevoNodo;
                ultimo.setSgteNodo(cabeza);
            }
        }
    }
    
    public void eliminarDesde(int pos, int cantidad) {
        if (cantidad <= 0) return;
        
        if (cabeza == ultimo) {
            cabeza = ultimo = null;
            return;
        }
        
        Nodo prev = ultimo;
        Nodo ptr = cabeza;
        
        int count = 1;
        
        while (count != pos) {
            count++;
            prev = ptr;
            ptr = ptr.getSgteNodo();
        }
        
        boolean vuelta = false;
        boolean total = false;
        
        while (cantidad != 0) {
            ptr = ptr.getSgteNodo();
            cantidad--;
            
            if (ptr == cabeza) vuelta = true;
            if (ptr == prev.getSgteNodo()) total = true;
        }
        
        if (total) {
            cabeza = ultimo = null;
            return;
        }
        
        prev.setSgteNodo(ptr);
        
        if (vuelta) {
            cabeza = ptr;
            ultimo = prev;
        }
    }
}
