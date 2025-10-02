import java.util.ArrayList;
import java.util.List;

class SharedList implements Runnable{
    private static List<Integer> listaCompartilhada = new ArrayList<>();

    @Override
    public void run(){
        Thread t1 = new Thread(this::popular);
        Thread t2 = new Thread(this::consumir);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public void popular(){ 
        for(int i = 0; i < 10; i++){
            try {
                synchronized(listaCompartilhada){
                    listaCompartilhada.add(i);
                    listaCompartilhada.notify(); //diz para T2 que um novo número foi colocado na lista
                }
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumir(){
        for(int i = 0; i < 10; i++){
            try {
                synchronized(listaCompartilhada){
                    while(listaCompartilhada.isEmpty()){
                    listaCompartilhada.wait();
                    }

                    int n = listaCompartilhada.remove(0); //consome o primeiro valor da lista
                    System.out.printf("Número %d foi consumido\n", n);
                }

                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}