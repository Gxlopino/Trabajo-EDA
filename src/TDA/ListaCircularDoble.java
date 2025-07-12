package TDA;
/**
 *
 * @author YUSTIN
 * @param <T>
 */
public class ListaCircularDoble<T> {

    private NodoDoble<T> cabeza;
    private NodoDoble<T> ultimo;

    public ListaCircularDoble() {
        cabeza = null;
        ultimo = null;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    public void agregar(T item) {
        NodoDoble<T> nuevo = new NodoDoble<>(item, null, null);
        if (esVacia()) {
            cabeza = nuevo;
            ultimo = nuevo;
            cabeza.setSgteNodo(cabeza);
            cabeza.setAntNodo(cabeza);
        } else {
            nuevo.setAntNodo(ultimo);
            nuevo.setSgteNodo(cabeza);
            ultimo.setSgteNodo(nuevo);
            cabeza.setAntNodo(nuevo);
            ultimo = nuevo;
        }
    }

    public void mostrar() {
        if (!esVacia()) {
            NodoDoble<T> aux = cabeza;
            do {
                System.out.print(aux.getItem() + "\t");
                aux = aux.getSgteNodo();
            } while (aux != cabeza);
            System.out.println();
        }
    }

    public void mostrarInverso() {
        if (!esVacia()) {
            NodoDoble<T> aux = ultimo;
            do {
                System.out.print(aux.getItem() + "\t");
                aux = aux.getAntNodo();
            } while (aux != ultimo);
            System.out.println();
        }
    }

    public int longitud() {
        if (esVacia()) return 0;
        int cont = 0;
        NodoDoble<T> aux = cabeza;
        do {
            cont++;
            aux = aux.getSgteNodo();
        } while (aux != cabeza);
        return cont;
    }

    public int ubicacion(T item) {
        if (esVacia()) return -1;
        int pos = 1;
        NodoDoble<T> aux = cabeza;
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
        if (pos >= 1 && pos <= longitud()) {
            NodoDoble<T> aux = cabeza;
            for (int i = 1; i < pos; i++) {
                aux = aux.getSgteNodo();
            }
            return aux.getItem();
        }
        return null;
    }

    public void eliminar(int pos) {
        int len = longitud();
        if (pos >= 1 && pos <= len) {
            if (len == 1) {
                cabeza = null;
                ultimo = null;
            } else if (pos == 1) {
                cabeza = cabeza.getSgteNodo();
                cabeza.setAntNodo(ultimo);
                ultimo.setSgteNodo(cabeza);
            } else {
                NodoDoble<T> aux = cabeza;
                for (int i = 1; i < pos; i++) {
                    aux = aux.getSgteNodo();
                }
                NodoDoble<T> ant = aux.getAntNodo();
                NodoDoble<T> sig = aux.getSgteNodo();
                ant.setSgteNodo(sig);
                sig.setAntNodo(ant);
                if (aux == ultimo) {
                    ultimo = ant;
                }
            }
        } else {
            System.err.println("La posición no es válida o la lista está vacía");
        }
    }

    public void insertar(T item, int pos) {
        int len = longitud();
        if (pos >= 1 && pos <= len + 1) {
            NodoDoble<T> nuevo = new NodoDoble<>(item, null, null);
            if (esVacia()) {
                cabeza = nuevo;
                ultimo = nuevo;
                cabeza.setSgteNodo(cabeza);
                cabeza.setAntNodo(cabeza);
            } else if (pos == 1) {
                nuevo.setSgteNodo(cabeza);
                nuevo.setAntNodo(ultimo);
                cabeza.setAntNodo(nuevo);
                ultimo.setSgteNodo(nuevo);
                cabeza = nuevo;
            } else {
                NodoDoble<T> aux = cabeza;
                for (int i = 1; i < pos - 1; i++) {
                    aux = aux.getSgteNodo();
                }
                NodoDoble<T> sig = aux.getSgteNodo();
                nuevo.setSgteNodo(sig);
                nuevo.setAntNodo(aux);
                aux.setSgteNodo(nuevo);
                sig.setAntNodo(nuevo);
                if (aux == ultimo) {
                    ultimo = nuevo;
                }
            }
        } else {
            System.out.println("No es posible insertar porque la posición no es válida");
        }
    }
}
