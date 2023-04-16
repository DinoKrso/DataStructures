package task1;



public class Stack<Item> {
    private Queue<Item> q1;
    private Queue<Item> q2;
    private Item peek;

    public Stack() {
        q1 = new Queue<>();
        q2 = new Queue<>();
        peek = null;
    }

    public void push(Item data) {
        if(q1.size() == 0) {
            peek = data;
        } else {
            while(q1.size() > 0) {
                q2.enqueue(q1.dequeue());
            }
            q1 = q2;
            q2 = new Queue<>();
            peek = data;
        }
        q1.enqueue(data);
    }


    public Item pop() {
        //return q1.dequeue() ;  //Doesn't work for some strange reason
        int size = q1.size() - 1;
        for(int i = 0; i < size; i++) {
            q2.enqueue(q1.dequeue());
        }
        Item item = q1.dequeue();
        q1 = q2;
        q2 = new Queue<>();
        return item;
    }


    public Item peek() {
        return peek;
    }

    public int size() {
        return q1.size();
    }

    public boolean isEmpty() {
        if(q1.size() == 0) {
            return true ;
        } else return false ;
    }
}
