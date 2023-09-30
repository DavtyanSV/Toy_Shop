import java.io.EOFException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * program
 */
public class program {

    
}

/**
 * Shop
 */
class Shop {
    Comparator<Toy> comparator = new ProbabilityComparator();
    private PriorityQueue<Toy> toys = new PriorityQueue<Toy>();

    public void get(){
        if(toys.peek() == null){
            throw new RuntimeException("Нечего извлекать");
        }
        else{
            try{
                Files.write(Paths.get("/toys.txt"), toys.poll().toString().getBytes());

            }
            catch (Exception e) {
                throw new RuntimeException("Ошибка записи файла");
            }

        }

    }

    public void put(String...strings){
        for (int index = 0; index < strings.length; index++) {
            Toy toy = new Toy(strings[index]);
            toys.add(toy);
            
        }


    }

    
}

class ProbabilityComparator implements Comparator<Toy> {
    @Override
    public int compare(Toy x, Toy y) {
        
        if (x.getProbobility() < y.getProbobility()) {
            return -1;
        }
        if (x.getProbobility() > y.getProbobility()) {
            return 1;
        }
        return 0;
    }
}


/**
 * Toy
 */
class Toy {
    int id;
    int probobility;
    String description;

    public int getId() {
        return id;
    }

    public int getProbobility() {
        return probobility;
    }

    public String getDescription() {
        return description;
    }

    public Toy(String text){

        String[] array = text.split(" ");
        if(array.length<3 || array.length>3){
            throw new RuntimeException("Некорректное количество данных");
        }
        id = Integer.parseInt(array[0]);
        probobility = Integer.parseInt(array[1]);
        description = array[2];
    }

    public String toString(){
        return this.id + " " + this.probobility + " " + this.description;
    }
}