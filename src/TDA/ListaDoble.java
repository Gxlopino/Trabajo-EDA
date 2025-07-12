package TDA;
/**
 *
 * @author YUSTIN
 * @param <T>
 */
public class ListaDoble<T> {

    private NodoDoble<T> cabeza;
    private NodoDoble<T> ultimo;

    public ListaDoble() {
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
        } else {
            ultimo.setSgteNodo(nuevo);
            nuevo.setAntNodo(ultimo);
            ultimo = nuevo;
        }
    }

    public NodoDoble<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoDoble<T> cabeza) {
        this.cabeza = cabeza;
    }

    public NodoDoble<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoDoble<T> ultimo) {
        this.ultimo = ultimo;
    }

    public void mostrar() {
        NodoDoble<T> aux = cabeza;
        while (aux != null) {
            System.out.print(aux.getItem() + "\t");
            aux = aux.getSgteNodo();
        }
        System.out.println();
    }

    public void mostrarInverso() {
        NodoDoble<T> aux = ultimo;
        while (aux != null) {
            System.out.print(aux.getItem() + "\t");
            aux = aux.getAntNodo();
        }
        System.out.println();
    }

    public int longitud() {
        int cont = 0;
        NodoDoble<T> aux = cabeza;
        while (aux != null) {
            cont++;
            aux = aux.getSgteNodo();
        }
        return cont;
    }

    public int ubicacion(T item) {
        int pos = 1;
        NodoDoble<T> aux = cabeza;
        while (aux != null) {
            if (aux.getItem().equals(item)) {
                return pos;
            }
            aux = aux.getSgteNodo();
            pos++;
        }
        return -1;
    }

    public T iesimo(int pos) {
        if (pos >= 1 && pos <= longitud()) {
            int i = 1;
            NodoDoble<T> aux = cabeza;
            while (i < pos) {
                i++;
                aux = aux.getSgteNodo();
            }
            return aux.getItem();
        } else {
            return null;
        }
    }

    public void eliminar(int pos) {
        if (pos >= 1 && pos <= longitud()) {
            if (pos == 1) { // Eliminar el primer nodo
                NodoDoble<T> aux = cabeza;
                cabeza = aux.getSgteNodo();
                if (cabeza != null) {
                    cabeza.setAntNodo(null);
                } else {
                    // Si la lista queda vacía
                    ultimo = null;
                }
            } else {
                int i = 1;
                NodoDoble<T> aux = cabeza;
                while (i < pos) {
                    aux = aux.getSgteNodo();
                    i++;
                }
                NodoDoble<T> anterior = aux.getAntNodo();
                NodoDoble<T> siguiente = aux.getSgteNodo();

                if (anterior != null) {
                    anterior.setSgteNodo(siguiente);
                }
                if (siguiente != null) {
                    siguiente.setAntNodo(anterior);
                } else {
                    // Si eliminamos el último nodo
                    ultimo = anterior;
                }
            }
        } else {
            System.err.println("La posición no es válida o la lista está vacía");
        }
    }

    public void insertar(T item, int pos) {
        if (pos >= 1 && pos <= longitud() + 1) {
            NodoDoble<T> nuevoNodo = new NodoDoble<>(item, null, null);
            if (pos == 1) { // Insertar al inicio
                nuevoNodo.setSgteNodo(cabeza);
                if (cabeza != null) {
                    cabeza.setAntNodo(nuevoNodo);
                }
                cabeza = nuevoNodo;
                if (ultimo == null) {
                    ultimo = nuevoNodo;
                }
            } else {
                NodoDoble<T> aux = cabeza;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getSgteNodo();
                    i++;
                }

                NodoDoble<T> siguiente = aux.getSgteNodo();
                nuevoNodo.setSgteNodo(siguiente);
                nuevoNodo.setAntNodo(aux);
                aux.setSgteNodo(nuevoNodo);
                if (siguiente != null) {
                    siguiente.setAntNodo(nuevoNodo);
                } else {
                    // Se insertó al final
                    ultimo = nuevoNodo;
                }
            }
        } else {
            System.out.println("No es posible insertar porque la posición no es válida");
        }
    }
    
    // Ejercicio 3
    public static ListaDoble<Integer> procedure(ListaDoble<Integer> list0, ListaDoble<Integer> list1) {
        ListaDoble<Integer> res = new ListaDoble<>();
        
        NodoDoble<Integer> ptr = list0.cabeza;
        
        while (ptr != null) {
            res.agregar(ptr.getItem());
            ptr = ptr.getSgteNodo();
        }
        
        ptr = list1.cabeza;
        
        while (ptr != null) {
            res.agregar(ptr.getItem());
            ptr = ptr.getSgteNodo();
        }
        
        res.mostrar();
        ptr = res.cabeza;
        
        while (ptr != null) {
            if (ptr.getItem() == 0) {
                ptr.setItem(0);
            }
            else if (ptr.getItem() > 0) {
                ptr.setItem(1);
            }
            else {
                ptr.setItem(-1);
            }
            
            ptr = ptr.getSgteNodo();
        }
        
        return res;
    }
}
