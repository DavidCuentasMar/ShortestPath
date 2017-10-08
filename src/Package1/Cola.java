package Package1;

public class Cola {
    nodo ptr;
    nodo ultimo;

    public Cola() {

    }
    
    public void addCola(nodo p){
        if (ptr==null) {
            ptr=p;          
        }else{
            ultimo.link=p;            
        }
        ultimo=p;  
        
    
    }

    void show() {
        nodo p = this.ptr;
        while(p!=null){
            System.out.println("("+p.name+","+p.peso+")");
            p=p.link;
        }
    }
    
    public nodo getMinimo(){     
        nodo p = ptr;
        nodo minimo = new nodo(0,Integer.MAX_VALUE);
        nodo antp=null;
        while(p!=null){
            if (minimo.peso>p.peso) {
                minimo.peso=p.peso;
                minimo.name=p.name;
            }
            p=p.link;
        }
        p = ptr;
        while(p.name!=minimo.name){
            antp=p;
            p=p.link;            
        }
        if (p==ptr) {           
            this.ptr=p.link;
            p.link=null;            
        }else{
            antp.link=p.link;
            p.link=null;
        }

        return minimo;
    }
    
    
}